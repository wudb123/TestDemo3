package cn.wdb.web.Servlet;

import cn.wdb.domain.PageBean;
import cn.wdb.domain.User;
import cn.wdb.service.UserService;
import cn.wdb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

@WebServlet("/userFindByPageServlet")
public class UserFindByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String raw = request.getParameter("raw");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(raw == null || "".equals(raw)){
            raw = "7";
        }
        //获取条件查询参数
        Map<String, String[]> information = request.getParameterMap();
        //2.调用service
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findByPage(currentPage,raw,information);
        //3.将pb存入request 域中
        request.setAttribute("pb",pb);
        request.setAttribute("information",information); //将条件查询集合存入request域中
        //4.转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
