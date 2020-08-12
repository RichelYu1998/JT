package cn.tedu;

import cn.tedu.pojo.ItemDesc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestObjectMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void test01() {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(100L).setItemDesc("测试JSON转换")
                .setCreated(new Date())
                .setUpdated(itemDesc.getCreated());
        try {
            //1.将java对象转化为JSON
            String json = MAPPER.writeValueAsString(itemDesc);
            System.out.println(json);
            //2.将json转化为对象   传递需要转化之后的class类型   调用是对象的set方法
            ItemDesc itemDesc2 = MAPPER.readValue(json, ItemDesc.class);
            System.out.println(itemDesc2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
