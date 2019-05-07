package cn.wdb.dao.impl;

import cn.wdb.dao.UserDao;
import cn.wdb.domain.User;
import cn.wdb.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        //1.定义sql语句
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void delUser(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    /**
     * 回显信息
     *
     * @param id
     * @return
     */
    @Override
    public User find(int id) {
        User user = new User();
        //1.定义sql
        String sql = "select * from user where id = ?";
        User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user1;
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void update(User user) {
        //1.定义sql
        String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * 批量条件查询总用户数
     *
     * @param information
     * @return
     */
    @Override
    public int findTotalByPage(Map<String, String[]> information) {
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        Set<String> map = information.keySet();
        List<Object> cs = new ArrayList<Object>();
        for (String key : map) {
            String value = information.get(key)[0];
            //排除分页条件参数
            if ("currentPage".equals(key) || "raw".equals(key)) {
                continue;
            }
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                sb.append("and " + key + " like ?");
                cs.add("%" + value + "%");
            }
        }
        int i = 0;
        try {
            i = template.queryForObject(sb.toString(), Integer.class, cs.toArray());
        } catch (Exception e) {

        }
        return i;
    }

    /**
     * 批量查询用户
     *
     * @param start
     * @param raw
     * @param information
     * @return
     */
    @Override
    public List<User> findByPage(int start, int raw, Map<String, String[]> information) {
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        Set<String> map = information.keySet();
        List<Object> cs = new ArrayList<Object>();
        for (String key : map) {
            String value = information.get(key)[0];
            //排除分页条件参数
            if ("currentPage".equals(key) || "raw".equals(key)) {
                continue;
            }
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ?");
                cs.add("%" + value + "%");
            }
        }
        sb.append(" limit ?,?");//添加分页查询
        //System.out.println(sb);
        cs.add(start);
        cs.add(raw);
        List<User> list = null;
        try {
            list = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), cs.toArray());
        } catch (Exception e) {

        }
        return list;
    }

}




