package cn.tedu.service;

import cn.tedu.mapper.CartMapper;
import cn.tedu.pojo.Cart;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DubboCartServiceImpl implements DubboCartService{
    @Resource
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return cartMapper.selectList(queryWrapper);
    }
}
