package cn.tedu.service;

import cn.tedu.mapper.ItemMapper;
import cn.tedu.pojo.Item;
import cn.tedu.vo.EasyUITable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    /**
     * 执行步骤:1.手动编辑sql    2.利用MP机制 动态生成
     * SELECT * FROM tb_item LIMIT 起始位置,查询记录数
     * /*第一页 java中数组运算 一般都是含头不含尾
     * SELECT * FROM tb_item LIMIT 0,20;
     * /*第二页
     * SELECT * FROM tb_item LIMIT 20,20;
     * /*第三页
     * SELECT * FROM tb_item LIMIT 40,20;
     * 第N页
     * SELECT * FROM tb_item LIMIT (n-1)ROWS,ROWS;
     */
	/*@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//参数1.记录总数   参数2: rows 当前页的记录数
		long total=itemMapper.selectCount(null);
		int startIndex=(page-1)*rows;
		List<Item> itemList=itemMapper.findItemByPage(startIndex,rows);
		return new EasyUITable(total,itemList);
	}*/
    /*2.利用MP机制 动态生成*/
    @Override
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        //Page 参数1：第几页 参数2：每页多少内容
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("updated");
        IPage<Item> iPage = new Page<>(page, rows);
        iPage = itemMapper.selectPage(iPage, queryWrapper);

        Long total = iPage.getTotal();
        List<Item> itemList = iPage.getRecords();
        return new EasyUITable(total, itemList);

    }

    /**
     * 实现商品信息的入库操作
     * 入库之前需要提前将数据补全.  刚新增的商品应该处于上架状态1
     * @param item
     * 注意事项:完成数据库更新操作时,需要注意数据库事务问题
     */
    @Override
    @Transactional
    public void saveItem(Item item) {
    	item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
    	itemMapper.insert(item);
    }
}
