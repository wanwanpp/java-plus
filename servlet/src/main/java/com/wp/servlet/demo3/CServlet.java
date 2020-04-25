package com.wp.servlet.demo3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//解决乱码
			response.setContentType("text/html;charset=utf-8");
			//1.设置响应头 为Refresh:5;url=/Day08-response/AServlet
			response.setHeader("Refresh", "5;url=/AServlet");
			//2.使用字符流发送提示,5秒后跳转
			response.getWriter().print("您将在<span id='one' ></span>秒后跳转到AServlet!" +
					"<script type='text/javascript'>" +
					"var count = 5;" +
					"function fun1(){" +
					"var span = document.getElementById('one');" +
					"span.innerHTML = count--;" +
					"" +
					"if(count>0){" +
					"window.setTimeout(fun1,1000);" +
						"}" +
					"}" +
					"fun1();" +
					"</script>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
