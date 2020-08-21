package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class DubboUserServiceImpl implements DubboUserService{
    @Resource
    private UserMapper userMapper;
}
