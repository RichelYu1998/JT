package cn.tedu.controller;

import cn.tedu.pojo.Item;
import cn.tedu.pojo.ItemDesc;
import cn.tedu.service.ItemService;
import cn.tedu.vo.EasyUITable;
import cn.tedu.vo.SysResult;
import org.springframework.web.bind.annotation.PathVariable;
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
	/*@RequestMapping("/save")
	public SysResult saveItem(Item item){
		//1.利用对象的get方法,获取对象的属性的信息
		//item.getId()---->get去除------获取id的属性(大小写忽略);
		//之后将获取到的值利用对象的set方法为属性赋值.
		//request.getParameter("id")
		try {
			itemService.saveItem(item);
			return SysResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return  SysResult.fail();
		}
	}*/
    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc) {
        itemService.saveItem(item,itemDesc);
        return SysResult.success();
    }

    /*
     * 完成商品的信息修改
     * url:"/item/update"
     * 参数:整个页面表单
     * 返回值：SysResult
     * */
    @RequestMapping("/update")
    public SysResult updateItem(Item item,ItemDesc itemDesc) {
        itemService.updateItem(item,itemDesc);
        return SysResult.success();
    }

    /*
     *
     * 商品删除
     * url:/item/delete
     * 参数: ids=id,id,id,id
     * 返回值结果: SysResult对象
     * */
    @RequestMapping("delete")
    public SysResult deleteItems(Long[] ids) {
        itemService.deleteItems(ids);
        return SysResult.success();
    }
    @RequestMapping("/updateStatus/{status}")
    public SysResult updateItemStatus(Long[] ids, @PathVariable Integer status){
        itemService.updateItemStatus(ids,status);
        return SysResult.success();
    }
    /**
     * 业务需求:  根据itemId查询商品详情信息
     * url地址:  http://localhost:8091/item/query/item/desc/1474391972
     * 参数:		使用restFul方式使用传输传递
     * 返回值:    SysResult对象,并且需要携带itemDesc数据信息.
     */
    @RequestMapping("/query/item/desc/{itemId}")
    public SysResult findItemDescById(@PathVariable  Long itemId){
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        return SysResult.success(itemDesc);
    }
}
