package cn.tedu.controller;

import cn.tedu.pojo.ItemCat;
import cn.tedu.service.ItemCatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Resource
    private ItemCatService itemCatService;
    /*
    * 1.url:/item/cat/queryItemName
    * 2.参数：data:{ItemCatId:val}
    * 3.返回值：返回商品分类的名称
    * */
    @RequestMapping("queryItemName")
    public String findItemCatNameById(Long itemCatId){
        ItemCat itemCat=itemCatService.findItemCatById(itemCatId);
        return itemCat.getName();
    }
}
