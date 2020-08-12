package cn.tedu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    //1.创建工具API对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //2封装API 将对象转为JSON
    public static String toJSON(Object object) {
        if (object == null) {
            throw new RuntimeException("传入对象不能为null");
        }
        try {
            String json = MAPPER.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /*
     * 3.将JSON转化为对象 用户传递什么类型，则返回什么类型
     */
    public static <T> T toObject(String json, Class<T> target) {
        //1.校验有效性
        if (json == null || "".equals(json) || target == null) {
            //参数有误
            throw new RuntimeException("参数不能为null");
        }
        //2.执行业务处理
        try{
            T t = MAPPER.readValue(json, target);
            return t;
        }
       catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
       }
    }
}
