package cn.wdb.web.Servlet;

import cn.wdb.service.UserService;
import cn.wdb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userDelSelectedServlet")
public class UserDelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String[] ids = request.getParameterValues("select");
        //2.调用service
        UserService service = new UserServiceImpl();
        service.delSelect(ids);
        //3.转发
        response.sendRedirect("userFindByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
