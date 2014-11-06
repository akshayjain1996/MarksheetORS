package com.ctl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MarksheetDTO;
import com.service.MarksheetService;

public class MarksheetListCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String doC = '"' + "";

		out.print("<?xml version=" + doC + "1.0" + doC + " encoding=" + doC
				+ "UTF-8" + doC + "?>");
		out.print("<DATA>");
		MarksheetService service = new MarksheetService();
		try {
			List list = service.list();
			System.out.println("List Size " + list.size());
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				MarksheetDTO dto = (MarksheetDTO) iterator.next();
				out.print("<ELEMENT>");
				out.print("<ROLLNO>" + dto.getRollno());
				out.print("</ROLLNO>");
				out.print("<NAME>" + dto.getName());
				out.print("</NAME>");
				out.print("<PHYSICS>" + dto.getPhysics());
				out.print("</PHYSICS>");
				out.print("<CHEMISTRY>" + dto.getChemistry());
				out.print("</CHEMISTRY>");
				out.print("<MATHS>" + dto.getMaths());
				out.print("</MATHS>");
				out.print("</ELEMENT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("</DATA>");
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}