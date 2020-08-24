package cn.tedu.service;

import cn.tedu.mapper.OrderItemMapper;
import cn.tedu.mapper.OrderMapper;
import cn.tedu.mapper.OrderShippingMapper;

import cn.tedu.pojo.Order;
import cn.tedu.pojo.OrderItem;
import cn.tedu.pojo.OrderShipping;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements DubboOrderService {
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderShippingMapper orderShippingMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	@Transactional
	@Override
	public String saveOrder(Order order) {
		//orderId由登录用户id+当前时间戳手动拼接.
		String orderId = "" + order.getUserId() + System.currentTimeMillis();
		//1.完成订单入库操作
		order.setOrderId(orderId).setStatus(1);
		orderMapper.insert(order);
		System.out.println("订单入库成功!!!");
		//2.完成订单商品入库
		List<OrderItem> orderItems = order.getOrderItems();
		//insert into tb_order_item values(xxxxxx),(xxxxxxx),(xxxxxxx);
		for (OrderItem orderItem : orderItems){
			orderItem.setOrderId(orderId);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单商品入库成功!!!!");
		//3.完成订单物流入库
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流入库成功!!!!");
		return orderId;
	}
	@Override
	public Order findOrderById(String orderId) {
		Order order = orderMapper.selectById(orderId);
		OrderShipping orderShipping = orderShippingMapper.selectById(orderId);
		QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId);
		List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
		return order.setOrderShipping(orderShipping).setOrderItems(orderItems);
	}
}

