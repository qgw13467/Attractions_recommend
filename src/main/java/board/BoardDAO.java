package board;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;




public class BoardDAO {
	private Connection conn;
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ""; 
	}
	
	public int getNextBoard() {
		String SQL = "SELECT boardID FROM board ORDER BY boardID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; 
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	public int writeBoard(String boardID, String routeID  , String userID, int maxP ,int currentP ,String matesID,
			int appliT,String boardTitle ,String boardContent   ) {
		String SQL = "INSERT INTO board(boardID ,routeID ,userID ,boardDate , maxP, currentP,"
				+ "matesID ,appliT, boardTitle  ,boardContent  ) VALUES (?, ?, ?, ?, ?,    ?, ?, ?, ?, ? )";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1,  getNextBoard());
			pstmt.setString(2,  routeID);
			pstmt.setString(3,  userID);
			pstmt.setString(4,  getDate());
			pstmt.setInt(5,  maxP);
			pstmt.setInt(6,  currentP);
			pstmt.setString(7,  matesID);
			pstmt.setInt(8,  appliT);
			pstmt.setString(9,  boardTitle);
			pstmt.setString(10,  boardContent);
			
			
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public Board getBoard(int boardID) {
		
		String SQL = "SELECT * FROM board WHERE boardID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  boardID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setRouteID(rs.getInt(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setMaxP(rs.getInt(5));
				board.setCurrentP(rs.getInt(6));
				board.setMatesID(rs.getString(7));
				board.setAppliT(rs.getInt(8));
				board.setBoardContent(rs.getString(9));
				board.setBoardTitle(rs.getString(10));
				return board;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
	public ArrayList<Board> getList(int pageNumber)
	{
		String SQL = "SELECT * FROM board WHERE boardID < ?  ORDER BY boardID DESC LIMIT 10";
		
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNextBoard() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setRouteID(rs.getInt(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setMaxP(rs.getInt(5));
				board.setCurrentP(rs.getInt(6));
				board.setMatesID(rs.getString(7));
				board.setAppliT(rs.getInt(8));
				board.setBoardContent(rs.getString(9));
				board.setBoardTitle(rs.getString(10));
				list.add(board);
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber)
	{
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable <>0";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNextBoard() - (pageNumber -1) * 10);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	public int update(int boardID , String boardTitle , String boardContent ) {
		String SQL = "UPDATE board SET boardTitle = ?, boardContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1,  boardTitle);
			pstmt.setString(2,  boardContent);
			pstmt.setInt(3,  boardID);
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	/* 삭제가 안보이게 인지 완전 삭제인지에 따라 테이블 형테 바꿈
	public int delete(int bbsID) {
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	*/
	
}
