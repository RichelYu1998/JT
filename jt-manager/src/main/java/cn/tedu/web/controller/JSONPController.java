package cn.tedu.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONPController {
    /**
     * 完成JSONP的调用
     * url:http://manager.jt.com/web/testJSONP?callback=jQuery111101021758391465013_1597656788213&_=1597656788214
     * 规定:返回值结果,必须经过特殊的格式封装.callback(json)
     */
    @RequestMapping("/web/testJSONP")
    public String  jsonp(String callback){
        return callback+"({'id':'100','name':'tomcat猫'})";
    }
}
