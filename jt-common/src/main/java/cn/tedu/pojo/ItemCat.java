package cn.tedu.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@TableName("tb_item_cat")
public class ItemCat extends BasePojo{
    private Long id;
    private Long parentId;      //父级ID
    private String name;        //分类名称
    private Integer status;     //状态码 1：正常 2： 删除
    private Integer sortOrder;  //排序号
    private Boolean isParent;   //是否为父类ID
}
