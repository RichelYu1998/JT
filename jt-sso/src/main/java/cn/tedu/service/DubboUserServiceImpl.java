package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.User;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class DubboUserServiceImpl implements DubboUserService{
    @Resource
    private UserMapper userMapper;

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
}
