package cn.wdb.service.impl;

import cn.wdb.dao.UserDao;
import cn.wdb.dao.impl.UserDaoImpl;
import cn.wdb.domain.PageBean;
import cn.wdb.domain.User;
import cn.wdb.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{
    UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        List<User> users = dao.findAll();
        return users;
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void delUser(int i) {
        dao.delUser(i);
    }

    @Override
    public User find(int i) {
        return dao.find(i);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delSelect(String[] ids) {
        for (String id : ids) {
            dao.delUser(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findByPage(String _currentPage, String _raw, Map<String, String[]> information) {
        PageBean<User> pb = new PageBean<User>();
        int raw = Integer.parseInt(_raw);
        pb.setRaw(raw);
        //计算总记录数
        int totalCount = dao.findTotalByPage(information);
        pb.setTotalCount(totalCount);
        //计算总页码数
        int totalPage = (totalCount % raw) == 0 ? totalCount / raw : (totalCount /raw) +1;
        pb.setTotalPage(totalPage);
        int currentPage = Integer.parseInt(_currentPage);
        if(currentPage <= 0){
            currentPage = 1;
        }
        if(currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);
        //计算用户集合
        int start = (currentPage - 1) * raw;
        List<User> list = dao.findByPage(start,raw,information);
        pb.setList(list);
        return pb;
    }




}
