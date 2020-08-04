package cn.tedu.controller;

import cn.tedu.pojo.Item;
import cn.tedu.service.ItemService;
import cn.tedu.vo.EasyUITable;
import cn.tedu.vo.SysResult;
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
	/*
	* 1.url:localhost:8091/item/save
	* 2.参数：整个form表单
	* 返回值：SysResult对象
	*
	* 复习:页面中的参数是如何通过SpringMVC为属性赋值
	* 分析:1)页面提交三种方式:1.form表单提交 2.ajax页面提交 3.标签 参数提交 页面参数提交一般遵守协议规范 key-value
	* 分析:2)SpringMVC的底层实现Servlet 包含了2个请求对象 request/response
	* 		Q:servlet如何获取数据？
	* 		A:参数提交的名称与mvc中接收参数的名称一致
	* */
	@RequestMapping("/save")
	public SysResult saveItem(Item item){
		//1.利用对象的get方法,获取对象的属性的信息
		//item.getId()---->get去除------获取id的属性(大小写忽略);
		//之后将获取到的值利用对象的set方法为属性赋值.
		//request.getParameter("id")
		try{
			itemService.saveItem(item);
			return SysResult.success();
		}
		catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}
}
