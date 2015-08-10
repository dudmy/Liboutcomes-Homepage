package com.hansung.liboutcomes.servlet.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hansung.liboutcomes.mail.SMTPAuthenticatior;
import com.hansung.liboutcomes.mail.STMPInformation;
import com.hansung.liboutcomes.model.Board;
import com.hansung.liboutcomes.model.BoardService;

/**
 * @breif �Ϲ� �Խ��� �� �ۼ� ����
 * @author YuJin
 *
 */
@WebServlet("/BoardWrite")
public class BoardWriteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/page/boardWrite.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		
		// �� �Ѱ� �ޱ�
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String writerName = req.getParameter("writerName");
		String contents = req.getParameter("contents");
		
		// �𵨿� �� ���
		Board board = new Board();
		board.setCategory(category);
		board.setTitle(title);
		board.setWriterName(writerName);
		board.setContents(contents);
		
		// �Խù� ���
		boardService = new BoardService();
		
		//���� ��¥, �ð� ���ϱ�
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		
		//���� ���������� ��ϵǸ�
		if( boardService.createBoard(board) ) {
			STMPInformation si = new STMPInformation();
			
			try{
			    Authenticator auth = new SMTPAuthenticatior();
			    Session ses = Session.getInstance(si.getP(), auth);
			     
			    ses.setDebug(true);
			     
			    MimeMessage msg = new MimeMessage(ses); // ������ ������ ���� ��ü
			    msg.setSubject("LibOutcomes: �� ���� ��ϵǾ����ϴ�."); // ����
			     
			    Address fromAddr = new InternetAddress("liboutcomes@naver.com");
			    msg.setFrom(fromAddr); // ������ ���
			     
			    Address toAddr = new InternetAddress("ymj9312@hanmail.net");
			    msg.addRecipient(Message.RecipientType.TO, toAddr); // �޴� ���
			     
			    msg.setContent("���� �� �Խñ��� ��ϵǾ����ϴ�.		�Խù� ��� �ð� : " + dateFormat.format(calendar.getTime()), 
			    				"text/html;charset=UTF-8"); // ����� ���ڵ�
			     
			    Transport.send(msg); // ����
			} catch(Exception e){
			    e.printStackTrace();
			    System.out.println(e.getMessage());
			}
		}//if end
		
		resp.sendRedirect("Board");

	}

}
