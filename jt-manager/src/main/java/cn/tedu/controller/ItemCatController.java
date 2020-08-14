package cn.tedu.controller;

import cn.tedu.pojo.ItemCat;
import cn.tedu.service.ItemCatService;
import cn.tedu.vo.EasyUITree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    /*
     * 需求：利用redis缓存实现业务功能
     * 根据ID查询子级目录
     * 步骤：
     *   1.准备key key="ITEM_CAT_LIST::0"
     * */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatByParentId
    (@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        //初始化时应该设定默认值.
        //1.查询一级商品分类信息
        //Long parentId = id==null?0L:id;

        //return itemCatService.findItemCatByParentId(parentId);
        //通过缓存的方式获取数据.
        return itemCatService.findItemCatByCache(parentId);
    }
}