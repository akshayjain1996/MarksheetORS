package com.ctl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.UserDTO;
import com.service.UserService;

public class UserCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String doC = '"' + "";

		out.print("<?xml version=" + doC + "1.0" + doC + " encoding=" + doC
				+ "UTF-8" + doC + "?>");
		out.print("<DATA>");

		String name = request.getParameter("name");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String operation = request.getParameter("operation");

		UserService service = new UserService();

		if ("Save".equals(operation)) {
			try {
				UserDTO dto = new UserDTO();
				dto.setName(name);
				dto.setUserName(userName);
				dto.setPassword(password);
				service.add(dto);
				out.print("<MESSAGE>Data Added</MESSAGE>");
				System.out.println("Database Add");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("Login".equals(operation)) {
			try {
				UserDTO dto = service.authenticate(userName, password);
				if (dto != null) {
					System.out.println("User is Valid");
					out.print("<MESSAGE>Valid</MESSAGE>");
				} else {
					out.print("<MESSAGE>Invalid</MESSAGE>");
					System.out.println("User is Invalid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		out.print("</DATA>");
		out.close();
	}
}