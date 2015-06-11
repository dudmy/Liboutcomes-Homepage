package webp.yujin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import webp.yujin.model.Board;
import webp.yujin.model.BoardService;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/Write")
public class WriteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public WriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/page/Write.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("euc-kr");	
		 
		MultipartRequest multi = null;
		
		// ���� �뷮 ���� 10mb
		int sizeLimit = 10 * 1024 * 1024;
		
		/**
		 * ���� ���ε�� ���� ��ġ: workspace > .metadata > .plugins > org.eclipse.wst.server.core
		 * 		> tmp0 > wtpwebapps > WebProgramming > upload
		 */
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// �� �Ѱ� �ޱ�
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String filename = multi.getFilesystemName("filename");
		String writer = multi.getParameter("writer");
		
		// �𵨿� �� ���
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setFilename(filename);
		board.setWriter(writer);
		
		// �Խù� ���
		BoardService boardService = new BoardService();
		boardService.insertBoard(board);
		
		// ������ �̵�
		response.sendRedirect("Talk");
	}

}
