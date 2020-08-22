package cn.tedu.service;

import cn.tedu.pojo.Cart;

import java.util.List;

public interface DubboCartService {
    List<Cart> findCartListByUserId(Long userId);
}
