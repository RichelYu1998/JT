package cn.tedu.service;

import cn.tedu.anno.CacheFind;
import cn.tedu.mapper.ItemDescMapper;
import cn.tedu.mapper.ItemMapper;
import cn.tedu.pojo.Item;
import cn.tedu.pojo.ItemDesc;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class DubboItemServiceImpl implements DubboItemService {

    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ItemDescMapper itemDescMapper;
    @Override
    @CacheFind(key = "ITEM_ID")
    public Item findItemById(Long itemId) {
        return itemMapper.selectById(itemId);
    }
    @Override
    @CacheFind(key = "ITEM_DESC_ID")
    public ItemDesc findItemDescById(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }
}
