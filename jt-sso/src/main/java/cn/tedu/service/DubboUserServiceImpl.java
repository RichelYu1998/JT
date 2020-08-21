package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.User;
import cn.tedu.util.ObjectMapperUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class DubboUserServiceImpl implements DubboUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    JedisCluster jedisCluster;

    /*
     * md5加密
     * 邮箱由手机号码代替
     * */
    @Override
    public void saveUser(User user) {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password).setEmail(user.getPhone());
        userMapper.insert(user);
    }

    /**
     * 步骤:
     * 1.校验用户信息是否正确
     * 2.如果正确则利用redis保存到   key/value
     * 3.返回用户秘钥
     */
    @Override
    public String doLogin(User user) {

        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        //查询数据库检查是否正确   根据对象中不为null的属性充当where条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        User userDB = userMapper.selectOne(queryWrapper);

        if(userDB == null) {
            return null;
        }

        //用户名和密码正确.  开始单点登录
        String ticket = UUID.randomUUID().toString().replace("-", "");
        //防止涉密信息泄露,则需要进行脱敏处理
        userDB.setPassword("你猜猜,看看能不能猜对!!!!");
        String value = ObjectMapperUtil.toJSON(userDB);
        jedisCluster.setex(ticket, 7*24*3600, value);
        return ticket;
    }
}
