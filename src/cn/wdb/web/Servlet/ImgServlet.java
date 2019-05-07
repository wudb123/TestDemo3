package cn.wdb.web.Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/imgServlet")
public class ImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);
        g.setColor(Color.blue);
        g.drawRect(0,0,width,height);
        g.setColor(Color.red);
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            int index = ran.nextInt(str.length());
            char c = str.charAt(index);
            sb.append(c);
            g.drawString(c+"",width/5*i,height/2);
        }
        String check_session = sb.toString();
        //System.out.println(check_session);
        request.getSession().setAttribute("check_session",check_session);
        g.setColor(Color.yellow);
        for (int i = 0; i < 5; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //输出
        ImageIO.write(image,"jpg",response.getOutputStream());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
