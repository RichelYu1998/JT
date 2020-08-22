package cn.tedu.service;

import cn.tedu.mapper.CartMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DubboCartServiceImpl implements DubboCartService{
    @Resource
    private CartMapper cartMapper;
}
