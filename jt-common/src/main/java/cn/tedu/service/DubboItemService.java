package cn.tedu.service;

import cn.tedu.pojo.Item;
import cn.tedu.pojo.ItemDesc;

//定义item的中立接口
public interface DubboItemService {
    Item findItemById(Long itemId);

    ItemDesc findItemDescById(Long itemId);
}
