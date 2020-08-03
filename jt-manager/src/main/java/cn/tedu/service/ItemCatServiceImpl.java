package cn.tedu.service;

import cn.tedu.mapper.ItemCatMapper;
import cn.tedu.pojo.ItemCat;
import cn.tedu.vo.EasyUITree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private ItemCatMapper itemCatMapper;
    @Override
    public ItemCat findItemCatById(Long itemCatId) {

        //利用MP机制查询数据库数据.
        return itemCatMapper.selectById(itemCatId);
    }
    /**
     * 据接口添加实现类的方法
     * 业务思路:
     * 	1.用户传递的数据parentId
     * 	2.可以查询List<ItemCat>数据库对象信息.
     * 	3.动态的将ItemCat对象转化为EasyUITree对象
     * 	3.但是返回值要求 返回List<EasyUITree>
     */
    @Override
    public List<EasyUITree> findItemCatListByParentId(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        ArrayList<EasyUITree> treeList = new ArrayList<>();
        //需要将数据一个一个的格式转化.
        for (ItemCat itemcat:itemCatList) {
            Long id = itemcat.getId();///获取ID
        }
        return null;
    }
}