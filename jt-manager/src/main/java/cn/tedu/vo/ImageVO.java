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
    //自定义API
    public static ImageVO fail(){
        return new ImageVO(1,null,null,null);
    }
    public static ImageVO success(String url){
        return new ImageVO(0,url,null,null);
    }
    public static ImageVO success(String url,Integer width,Integer height){
        return new ImageVO(0,url,width,height);
    }
}
