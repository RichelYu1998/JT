package cn.tedu.service;

import cn.tedu.pojo.Order;

public interface DubboOrderService {
    String saveOrder(Order order);

    Order findOrderById(String id);
}
