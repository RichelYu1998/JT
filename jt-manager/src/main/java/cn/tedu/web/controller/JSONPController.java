package cn.tedu.web.controller;

import cn.tedu.pojo.ItemDesc;
import cn.tedu.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.scripts.JS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //json字符串
public class JSONPController {

    /**
     * 完成JSONP的调用
     * url:http://manage.jt.com/web/testJSONP?callback=jQuery111101021758391465013_1597656788213&_=1597656788214
     * 规定:返回值结果,必须经过特殊的格式封装.callback(json)
     */
    /*@RequestMapping("/web/testJSONP")
    public JSONPObject jsonp(String callback){
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(101L).setItemDesc("我是商品详情信息");
        return new JSONPObject(callback,itemDesc);
    }*/
    @RequestMapping("/web/testJSONP")
    public String  jsonp(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(101L).setItemDesc("我是商品详情信息");
        String json = ObjectMapperUtil.toJSON(itemDesc);
        //return callback+"({'id':'100','name':'tomcat猫'})";
        return callback +"("+json+")";
    }

}