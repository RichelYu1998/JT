package cn.tedu;

import cn.tedu.pojo.ItemDesc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestObjectMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Test
    public void test01(){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(100L)
                .setItemDesc("测试JSON转化")
                .setCreated(new Date())
                .setUpdated(itemDesc.getCreated());
        try {
            //1.将java对象转化为JSON
            String json = MAPPER.writeValueAsString(itemDesc);
            System.out.println(json);
            //2.将JSON转化为对象 利用反射机制实例化对象 利用get/set方法为对象赋值
            ItemDesc itemDesc2 = MAPPER.readValue(json, ItemDesc.class);
            System.out.println(itemDesc2.toString()); //只输出当前对象的数据
            //3.将集合转换为JSON list
            List<ItemDesc> list = new ArrayList<>();
            list.add(itemDesc);
            String listJSON = MAPPER.writeValueAsString(list);
            System.out.println(listJSON);
            //将json转换为list集合
            List<ItemDesc> list2 = MAPPER.readValue(listJSON, list.getClass());
            System.out.println(list2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}