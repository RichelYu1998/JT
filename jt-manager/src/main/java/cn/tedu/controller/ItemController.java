package cn.tedu.controller;

import cn.tedu.pojo.Item;
import cn.tedu.service.ItemService;
import cn.tedu.vo.EasyUITable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController //JSON字符串
@RequestMapping("/item")
public class ItemController {
	
	@Resource
	private ItemService itemService;
	/*
	* 业务：根据分页要求展现商品列表，要求最热门的商品优先展示
	* url:"/item/query"
	* 参数：page=1&rows=20 page 当前页 rows 记录数
	* 返回值： EasyUITable
	* */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1.调用业务层,获取商品分页信息
		return itemService.findItemByPage(page, rows);
	}
}
