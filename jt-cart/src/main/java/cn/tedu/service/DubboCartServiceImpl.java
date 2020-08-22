package cn.tedu.service;

import cn.tedu.mapper.CartMapper;
import cn.tedu.pojo.Cart;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DubboCartServiceImpl implements DubboCartService {
    @Resource
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return cartMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void updateCartNum(Cart cart) {
        Cart cartTemp = new Cart();
        cartTemp.setNum(cart.getNum())
                .setUpdated(cart.getUpdated());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("item_id", cart.getItemId())
                .eq("user_id", cart.getUserId());
        cartMapper.update(cartTemp,updateWrapper);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartMapper.delete(new QueryWrapper<>(cart));
    }
}
