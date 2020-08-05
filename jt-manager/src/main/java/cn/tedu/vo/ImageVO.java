package cn.tedu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO implements Serializable {
    private Integer error;  //错误信息   0正确    1错误
    private String url;     //url地址
    private Integer width;  //宽度
    private Integer height; //高度

}
