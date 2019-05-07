package cn.wdb.dao;

import cn.wdb.domain.Admin;
import cn.wdb.domain.User;
import cn.wdb.util.JDBCUtils;
import cn.wdb.util.JDBCUtils1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDs());
    public Admin login(Admin loginAdmin){
        try {
            String sql = "select * from user where username = ? and password = ?";
            loginAdmin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), loginAdmin.getUsername(), loginAdmin.getPassword());
            return loginAdmin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
