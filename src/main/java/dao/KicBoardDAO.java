package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.KicBoard;
import model.KicMember;

public class KicBoardDAO {
	
	public Connection getConnection() {
		// 1. driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 2. connection
		catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return null;
		
	} // end of geConnection()
	
	public int insertBoard(KicBoard board) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = 
				"insert into kicboard values (kicboardseq.nextval,?,?,?,?,?,sysdate,0,?)";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPass());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile1());
			pstmt.setString(6, "1");
			
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of insertMember()
	
	public List<KicBoard> boardList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicboard";
		
		List<KicBoard> li = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				KicBoard m = new KicBoard();
				m.setNum(rs.getInt("num"));
				m.setName(rs.getString("name"));
				m.setSubject(rs.getString("subject"));
				m.setContent(rs.getString("content"));
				m.setRegdate(rs.getTimestamp("regdate"));
				li.add(m);
				
				System.out.println(m.getRegdate());
			} // end of while(rs.next())
			
			return li;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // end of tryCatch
		
	} // end of memberList()


}
