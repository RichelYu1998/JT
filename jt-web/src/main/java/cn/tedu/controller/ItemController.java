package cn.tedu.controller;

import cn.tedu.pojo.Item;
import cn.tedu.pojo.ItemDesc;
import cn.tedu.service.DubboItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//需要跳转页面
@Controller
public class ItemController {
    @Reference(check = false)
    private DubboItemService itemService;
    /**
     * 知识点:1.mvc页面跳转机制 2.restFul 3.jsp页面取值写法 4.dubbo
     * 需求:根据商品ID查询商品信息.(item/itemDesc)
     * url地址:http://www.jt.com/items/562379.html
     * 参数: 商品id
     * 页面取值要求:  ${item.title } ${itemDesc.itemDesc }
     * 返回值: 页面逻辑名称  item
     */
    @RequestMapping("/items/{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){
        //1.远程访问获取商品信息
        Item item = itemService.findItemById(itemId);
        //2.远程访问获取商品描述信息
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        //3.将数据传到页面中
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}