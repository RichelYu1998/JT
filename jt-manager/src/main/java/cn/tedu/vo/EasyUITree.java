package cn.tedu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EasyUITree implements Serializable {
    private static final long serialVersionUID = -8737180905395139477L;
    private Long id;        //数据库类型为Long
    private String text;    //定义节点名称
    private String state;   //控制节点打开/关闭
}
