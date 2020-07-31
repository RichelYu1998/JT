package cn.tedu.service;

import cn.tedu.vo.EasyUITable;


public interface ItemService {

    EasyUITable findItemByPage(Integer page, Integer rows);
}
