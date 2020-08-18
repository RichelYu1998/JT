package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService{

    //采用工具API形式动态获取
    private static Map<Integer,String> typeMap = new HashMap<>();
    static {  //类加载时就要执行  只执行一次
        typeMap.put(1,"username");
        typeMap.put(2,"phone");
        typeMap.put(3,"email");
    }


    @Resource
    private UserMapper userMapper;

    /**
     * 查询数据库,检查是否有数据.
     * @param param
     * @param type
     * @return  true 表示数据已存在   false 数据可以使用
     */
    @Override
    public boolean checkUser(String param, Integer type){
        //1.根据参数类型获取校验的类型 column
        String column = typeMap.get(type);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, param);
        int count = userMapper.selectCount(queryWrapper);
        return count==0?false:true;
    }

    @Override
    public void saveHttpCleint(User userPOJO) {
        userPOJO.setEmail("111222333@qq.com")
                .setPhone("1311112222");
        userMapper.insert(userPOJO);
    }


}