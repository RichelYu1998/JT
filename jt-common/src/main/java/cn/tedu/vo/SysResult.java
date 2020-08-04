package cn.tedu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
* 系统返回值vo对象，主要包含3个属性
* 1.设定状态码 200:执行成功 201:执行失败 业务定义
* 2.定义返回值信息 服务器可能会给用户一些提示信息 eg:执行成功、用户名/密码错误
* 3.定义返回值对象 服务器在后端处理完成业务之后，将对象返回给前端
* * */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysResult {
    private Integer status; //200成功 201失败
    private String msg; //服务器提示信息
    private Object data;    //服务器返回前端的业务数据
    /*
    *为了简化用户调用过程，准备了一系列API
    * 用户关注点 1.执行成功 2.执行失败
    * */
    public static SysResult fail(){
        return new SysResult(201,"业务执行失败",null);
    }
    public static SysResult success(){//只标识成功
        return new SysResult(200,"业务执行成功",null);
    }
    public static SysResult success(Object data){//成功之后返回业务数据
        return new SysResult(200,"业务执行成功",data);
    }
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }
}
