package cn.tedu.service;

import cn.tedu.pojo.User;

public interface DubboUserService {
    void saveUser(User user);

    String doLogin(User user);
}
