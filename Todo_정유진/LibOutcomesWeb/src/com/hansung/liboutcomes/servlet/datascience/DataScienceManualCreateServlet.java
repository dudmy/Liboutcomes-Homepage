package com.hansung.liboutcomes.servlet.datascience;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hansung.liboutcomes.model.Manual;
import com.hansung.liboutcomes.model.ManualService;

@WebServlet("/DataScienceManualCreate")
public class DataScienceManualCreateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ManualService manualService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/page/dataScienceManualCreate.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		
		// �� �Ѱ� �ޱ�
		String title = req.getParameter("title");
		String writerName = req.getParameter("writerName");
		String contents = req.getParameter("contents");
		
		// �𵨿� �� ���
		Manual manual = new Manual();
		manual.setTitle(title);
		manual.setWriterName(writerName);
		manual.setContents(contents);
		
		// �Խù� ���
		manualService = new ManualService();
		manualService.createManual(manual);
		
		resp.sendRedirect("DataScienceManual");

	}

}
