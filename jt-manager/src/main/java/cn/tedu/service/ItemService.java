package cn.tedu.service;

import cn.tedu.pojo.Item;
import cn.tedu.pojo.ItemDesc;
import cn.tedu.vo.EasyUITable;


public interface ItemService {

    EasyUITable findItemByPage(Integer page, Integer rows);

    void saveItem(Item item, ItemDesc itemDesc);
    void updateItem(Item item);

    void deleteItems(Long[] ids);

    void updateItemStatus(Long[] ids, Integer status);

    ItemDesc findItemDescById(Long itemId);
}
