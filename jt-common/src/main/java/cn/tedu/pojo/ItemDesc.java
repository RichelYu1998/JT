package cn.tedu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/*
 *  构建商品详情的pojo对象
 * */
@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo {
    @TableId                    //主键
    private Long itemId;        //商品ID
    private String itemDesc;    //商品详情信息
}
