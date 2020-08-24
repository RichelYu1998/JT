package cn.tedu.util;

import cn.tedu.pojo.User;

public class UserThreadLocal {
    //1.定义本地线程变量!!!!!
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();
    //ThreadLocal<Map>
    //2.定义数据新增的方法
    public static void set(User user){

        threadLocal.set(user);
    }

    //3.获取数据
    public static User get(){

        return threadLocal.get();
    }

    //4.移除方法 使用threadLocal时切记将数据移除.否则极端条件下,容易产出内存泄露的问题
    public static void remove(){

        threadLocal.remove();
    }
}
