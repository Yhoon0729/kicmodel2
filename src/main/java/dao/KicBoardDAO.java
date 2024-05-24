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
			pstmt.setString(6, board.getBoardid());
			
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of insertMember()
	
	public List<KicBoard> boardList(String boardid) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicboard where boardid=? order by num desc";
		
		List<KicBoard> li = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardid);
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
	
	public KicBoard getBoard(int num) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;

		String sql = "select * from kicboard where num = ?";

		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				KicBoard board = new KicBoard();
				// id 있음
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFile1(rs.getString("file1"));
				board.setRegdate(rs.getTimestamp("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setBoardid(rs.getString("boardid"));
				
				return board;
			} else {
				return null;
			} // end of if
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return null;  
	} // end of getBoard()
	
	public int boardCount(String boardid) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;

		String sql = "select nvl(count(*),0) from kicboard where boardid = ?";

		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				
				return rs.getInt(1);
			} else {
				return 0;
			} // end of if (rs.next())
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;  
	} // end of boardCont()

	
	public int addReadCount(int num) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "update kicboard set readcnt=readcnt+1 where num=?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			// sql 실행
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of addReadCount()

	public int updateBoard(KicBoard board) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		System.out.println(board);
		
		String sql = "update kicboard set name=?, subject=?, content=?, file1=? where num=?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFile1());
			pstmt.setInt(5, board.getNum());
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of updateBoard()
	
	public int deleteBoard(int num) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		System.out.println("delete=====!!!!!");
		String sql = "delete from kicboard where num = ?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// sql 실행
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of deleteBoard()

}
