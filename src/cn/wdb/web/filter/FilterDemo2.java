package cn.wdb.web.filter;

import sun.awt.SunHints;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter("/*")
public class FilterDemo2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //System.out.println(method.getName());
                if(method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req, args);
                    if(value != null && value.contains("sb")){
                        value = value.replaceAll("sb","***");
                    }
                    return value;
                }
                return  method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
