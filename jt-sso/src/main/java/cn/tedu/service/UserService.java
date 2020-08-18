package cn.tedu.service;

import cn.tedu.pojo.User;

public interface UserService {
    boolean checkUser(String param, Integer type);

    void saveHttpCleint(User userPOJO);
}
