package cn.tedu.controller;

import cn.tedu.pojo.ItemCat;
import cn.tedu.service.ItemCatService;
import cn.tedu.vo.EasyUITree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public String findItemCatNameById(Long itemCatId) {
        ItemCat itemCat = itemCatService.findItemCatById(itemCatId);
        return itemCat.getName();
    }
    /**
     * 业务需求:  查询一级商品分类信息
     * Sql语句:   SELECT * FROM tb_item_cat WHERE parent_id = 0
     * url地址:   /item/cat/list
     * 返回值:    List<EasyUITree>
     */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatList(Long id){
        /*if(id==null){
            id=0L;
        }*/
        Long parentId =(id==null?0L:id);  //根据parentId=0 查询一级商品分类信息
        return itemCatService.findItemCatListByParentId(parentId);
    }
}