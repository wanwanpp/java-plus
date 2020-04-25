package com.wp.servlet.demo2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BServlet extends HttpServlet {

	//重定向
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//重定向
			/*
			 //1 返回状态码为302
				response.setStatus(302);
			//2 告诉浏览器去哪找新的地址  发送一个响应头: Location : http://www.baidu.com
				//response.setHeader("Location", "http://www.baidu.com");
				response.setHeader("Location", "/Day08-response/AServlet");
				*/
			//-----------------------------------------------------------------
			response.sendRedirect("/AServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
