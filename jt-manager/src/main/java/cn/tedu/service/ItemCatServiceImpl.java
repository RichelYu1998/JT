package cn.tedu.service;

import cn.tedu.mapper.ItemCatMapper;
import cn.tedu.pojo.ItemCat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private ItemCatMapper itemCatMapper;
    @Override
    public ItemCat findItemCatById(Long itemCatId) {

        //利用MP机制查询数据库数据.
        return itemCatMapper.selectById(itemCatId);
    }
}