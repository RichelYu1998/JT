package cn.tedu.controller;

import cn.tedu.service.ItemService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class ItemController {
	
	@Resource
	private ItemService itemService;
	
	
	
	
}
