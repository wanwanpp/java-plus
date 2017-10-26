package com.wp.servlet.demo6;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//0告诉浏览器 发给你的流的MIME类型. 
		response.setContentType("image/jpeg");
			//1 通过servlet上下文获得图片的输入流
		InputStream  in = 	getServletContext().getResourceAsStream("/WEB-INF/001.jpg");
			//2 通过response获得输出字节流
		OutputStream out = response.getOutputStream();
			//3 两个对接
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
			out.flush();
		}
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
