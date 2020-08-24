package cn.tedu.service;

import cn.tedu.mapper.OrderItemMapper;
import cn.tedu.mapper.OrderMapper;
import cn.tedu.mapper.OrderShippingMapper;

import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements DubboOrderService {
	
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderShippingMapper orderShippingMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	
	
}
