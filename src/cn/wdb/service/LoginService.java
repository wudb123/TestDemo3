package cn.wdb.service;

import cn.wdb.dao.LoginDao;
import cn.wdb.domain.Admin;

public class LoginService {
    public Admin login(Admin loginAdmin){
        LoginDao ld = new LoginDao();
        return ld.login(loginAdmin);
    }
}
