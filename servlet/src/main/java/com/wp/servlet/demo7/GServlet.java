package com.wp.servlet.demo7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //0 告诉浏览器是什么东西
        //getServletContext().getMimeType(".jar") Context对象根据 后缀名去web.xml查找mime类型.
        response.setContentType(getServletContext().getMimeType(".jar"));
        //告诉浏览器推荐用户使用什么名称下载
        response.setHeader("Content-Disposition", "attachment;filename=ValidateCode.jar");
        //1 获得图片的输入流
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/ValidateCode.jar");
        //2 通过response获得输出字节流
        OutputStream out = response.getOutputStream();
        //3 两个对接
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        in.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
