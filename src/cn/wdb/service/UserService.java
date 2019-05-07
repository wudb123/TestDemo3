package cn.wdb.service;

import cn.wdb.domain.PageBean;
import cn.wdb.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();

    void addUser(User user);

    void delUser(int i);


    User find(int i);

    void update(User user);

    void delSelect(String[] ids);

    PageBean<User> findByPage(String currentPage, String raw, Map<String, String[]> information);


}
