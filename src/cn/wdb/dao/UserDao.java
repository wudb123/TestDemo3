package cn.wdb.dao;

import cn.wdb.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();

    void addUser(User user);

    void delUser(int i);


    User find(int i);

    void update(User user);

    int findTotalByPage(Map<String, String[]> information);

    List<User> findByPage(int start, int raw, Map<String, String[]> information);


}
