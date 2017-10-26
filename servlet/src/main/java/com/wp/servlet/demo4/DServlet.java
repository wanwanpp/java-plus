package com.wp.servlet.demo4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class DServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 获得输出字节流
		OutputStream os = response.getOutputStream();
		//2 输出中文
		os.write("你好 世界!".getBytes("UTF-8"));
		//3告诉浏览器使用GBK解码 ==> 乱码
		//os.write("<meta http-equiv='Content-Type' content='text/html;charset=gbk'>".getBytes());
			response.setHeader("Content-Type", "text/html;charset=utf-8");
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
