package cn.tedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tedu.pojo.Item;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ItemMapper extends BaseMapper<Item>{
    //利用注解执行sql.
    @Select("select * from tb_item order by updated desc limit #{startIndex},#{rows}")
    List<Item> findItemByPage(int startIndex, Integer rows);
}
