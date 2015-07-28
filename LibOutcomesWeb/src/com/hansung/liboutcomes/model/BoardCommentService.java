package com.hansung.liboutcomes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hansung.liboutcomes.utils.LibDBConnector;
import com.mysql.jdbc.Connection;

public class BoardCommentService {
	
	Connection conn;
	
	public BoardCommentService() {
		LibDBConnector libConnector = new LibDBConnector();
		conn = libConnector.getConnection();
	}
	
	public BoardCommentService(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * @brief �Խñ��� ��� ��������
	 * @param id: �Խñ� ��ȣ
	 * @return
	 */
	public ArrayList<BoardComment> getCommentList(int num) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardComment> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from board_comment where boardNum=? order by num desc");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				BoardComment boardComment= new BoardComment();
				boardComment.setNum(rs.getInt("num"));
				boardComment.setBoardNum(rs.getInt("boardNum"));
				boardComment.setWriterId(rs.getInt("writerId"));
				boardComment.setWriterName(rs.getString("writerName"));
				boardComment.setDate(rs.getDate("date"));
				boardComment.setComment(rs.getString("comment"));
				
				commentList.add(boardComment);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentList;
		
	}
	
	/**
	 * @brief �Խñۿ� ��� ���
	 * @param manualComment: �Է¹��� ��� ����
	 */
	public void createBoardComment(BoardComment boardComment) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("insert into board_comment"
					+ "(boardNum, writerId, writerName, date, comment)"
					+ "values(?, ?, ?, now(), ?)");
			pstmt.setInt(1, boardComment.getBoardNum());
			pstmt.setInt(2, boardComment.getWriterId());
			pstmt.setString(3, boardComment.getWriterName());
			pstmt.setString(4, boardComment.getComment());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteBoardComment(int num) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("delete from board_comment where boardNum=?");
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
