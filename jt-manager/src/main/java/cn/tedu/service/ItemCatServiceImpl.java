package cn.tedu.service;

import cn.tedu.anno.CacheFind;
import cn.tedu.mapper.ItemCatMapper;
import cn.tedu.pojo.ItemCat;
import cn.tedu.util.ObjectMapperUtil;
import cn.tedu.vo.EasyUITree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private ItemCatMapper itemCatMapper;
    @Resource
    private Jedis jedis;
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
    public List<EasyUITree> findItemCatByParentId(Long parentId){
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        List<EasyUITree> treeList = new ArrayList<>();  //先准备一个空集合.
        //需要将数据一个一个的格式转化.
        for(ItemCat itemcat :itemCatList){
            Long id = itemcat.getId();	//获取ID
            String text = itemcat.getName();	//获取文本
            //如果是父级,则默认应该处于关闭状态 closed, 如果不是父级 则应该处于打开状态. open
            String state = itemcat.getIsParent()?"closed":"open";
            //利用构造方法 为VO对象赋值  至此已经实现了数据的转化
            EasyUITree tree = new EasyUITree(id,text,state);
            treeList.add(tree);
        }

        //用户需要返回List<EasyUITree>
        return treeList;
    }

    @Override
    @CacheFind(key="ITEM_CAT_LIST")
    public List<EasyUITree> findItemCatByCache(Long parentId) {
        //1.定义key
        String key = "ITEM_CAT_LIST::"+parentId;
        List<EasyUITree> treeList = new ArrayList<EasyUITree>();
        Long  startTime = System.currentTimeMillis();
        //2.判断redis中是否有值
        if(jedis.exists(key)) {
            //不是第一次查询,则获取缓存数据之后直接返回
            String json = jedis.get(key);
            Long endTime = System.currentTimeMillis();
            treeList =
                    ObjectMapperUtil.toObject(json, treeList.getClass());
            System.out.println("redis查询缓存的时间为:"+(endTime-startTime)+"毫秒");
        }else {
            //redis中没有这个key,表示用户第一次查询.
            treeList = findItemCatByParentId(parentId);
            Long endTime = System.currentTimeMillis();
            //需要将list集合转化为json
            String json = ObjectMapperUtil.toJSON(treeList);
            //将数据保存到redis中
            jedis.set(key, json);
            System.out.println("查询数据库的时间为:"+(endTime-startTime)+"毫秒");
        }
        return treeList;
    }

}