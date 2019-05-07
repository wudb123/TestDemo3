package cn.wdb.web.Servlet;

import cn.wdb.domain.Admin;
import cn.wdb.service.LoginService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //获取验证码信息
        String verifycode = request.getParameter("verifycode");
        String check_session = (String) request.getSession().getAttribute("check_session");
        request.getSession().removeAttribute("check_session");//确保验证码一次性
        if (verifycode != null && check_session.equalsIgnoreCase(verifycode)){
            //验证码正确
            Admin admin = new Admin();
            Map<String, String[]> parameterMap = request.getParameterMap();
            try {
                BeanUtils.populate(admin,parameterMap);
                admin = new LoginService().login(admin);
                if (admin != null){
                    //登录成功
                    request.getSession().setAttribute("admin",admin);
                    //跳转到index.jsp页面
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }else{
                    request.setAttribute("login_msg","用户名或密码错误");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            //验证码错误
            request.setAttribute("login_msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
