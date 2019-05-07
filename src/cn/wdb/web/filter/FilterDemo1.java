package cn.wdb.web.filter;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST)
public class FilterDemo1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取访问路径
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        //System.out.println(uri);
        if(uri.contains("/login.jsp") ||uri.contains("/loginServlet") ||uri.contains("/imgServlet") ||uri.contains("/css/") ||uri.contains("/js/") ||uri.contains("/fonts/")){
            //要登陆，放行
            chain.doFilter(req, resp);
        }else{
            Object admin = request.getSession().getAttribute("admin");
            //System.out.println(admin);
            if(admin != null){
                //已经登录，放行
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("login_msg","您还没登陆");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
