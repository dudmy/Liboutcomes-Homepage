package webp.yujin.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webp.yujin.utils.DBConnector;

import com.mysql.jdbc.Connection;

public class BoardService {
	
	Connection conn;
	
	public BoardService() {
		DBConnector dbConnector = new DBConnector();
		conn = dbConnector.getConnection();
	}
	
	public BoardService(Connection conn) {
		this.conn = conn;
	}

	// �Խ��� �� ����Ʈ ��������
	public ArrayList<Board> getBoardList() {
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from data_science");
			
			while (rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
				board.setWriter(rs.getString("writer"));
				board.setDate(rs.getDate("date"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	// Ŭ���� �� ���� ��������
	public Board getBoard(int id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();
		
		try {
			pstmt = conn.prepareStatement("select * from data_science where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setId(rs.getInt("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
				board.setWriter(rs.getString("writer"));
				board.setDate(rs.getDate("date"));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
	}
	
	// �� ����ϱ�
	public void insertBoard(Board board) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("insert into data_science"
					+ "(title, content, filename, writer, date)"
					+ "values(?,?,?,?,now())");
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getWriter());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// �� �����ϱ�
	public void updateBoard(Board board) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("update data_science set "
					+ "title=?, content=?, writer=? where id=?");
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4, board.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// �� �����ϱ�
	public void deleteBoard(int id) {

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("delete from data_science where id=?");
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
