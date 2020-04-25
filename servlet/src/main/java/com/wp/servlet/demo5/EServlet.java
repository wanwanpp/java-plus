package com.wp.servlet.demo5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*//控制字符流使用的编码,往上放.在获得字符流的时候,会来取这个编码.如果在取完之后设置,没有效果.
		response.setCharacterEncoding("UTF-8");
		//告诉浏览器使用什么码表解码
		response.setHeader("Content-Type", "text/html;charset=utf-8");*/
		//JAVAEE提供了一个方法,这个方法同时可以做 以上两件事.
		response.setContentType("text/html;charset=utf-8");

		System.out.println("在EServlet中");
		//1 获得字符流
		PrintWriter pw = response.getWriter();
		//2 发送中文
		pw.print("你好 世界!");

		//问题: 同时使用两种流 会出现问题
//		response.getOutputStream().write("haha".getBytes());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
