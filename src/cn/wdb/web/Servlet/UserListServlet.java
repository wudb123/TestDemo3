package cn.wdb.web.Servlet;

import cn.wdb.domain.User;
import cn.wdb.service.UserService;
import cn.wdb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用UserService查询
        UserService us = new UserServiceImpl();
        List<User> users = us.findAll();
        //将list存入request域
        request.setAttribute("users",users);
        //转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
