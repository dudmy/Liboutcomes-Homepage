package webp.yujin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webp.yujin.model.Board;
import webp.yujin.model.BoardService;

/**
 * @author YuJin
 * WebServlet: �ش� URL�� ������ ��� TalkServlet�� ó���ϵ��� ����
 */
@WebServlet("/Talk")
public class TalkServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public TalkServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �Խ��� ����� DB���� �ҷ�����
		BoardService boardService = new BoardService();
		ArrayList<Board> boardList = boardService.getBoardList();
				
		// ���� �� ����Ʈ�� View�� ������
		request.setAttribute("boardList", boardList);
				
		// View ������
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/page/Talk.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
