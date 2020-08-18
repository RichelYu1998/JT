package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
}
