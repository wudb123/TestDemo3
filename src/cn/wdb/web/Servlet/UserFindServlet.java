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

@WebServlet("/userFindServlet")
public class UserFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String id = request.getParameter("id");
        //2.调用service查询
        UserService service = new UserServiceImpl();
        User user = service.find(Integer.parseInt(id));
        //3.存入域
        request.setAttribute("user",user);
        //4.转发
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
