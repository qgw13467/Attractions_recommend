package board;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



//2021 박희종
public class BoardDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com:3306/teamproject";
			String dbID = "admin";
			String dbPassword = "123456789";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	//날짜 구하는 함수
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

	
	
	
	
	//게시물 쓰기
	public int writeBoard(int routeID  , String userID, int maxP ,
			String appliT,String boardTitle ,String boardContent ,String kakaoLink  ) {
		String SQL = "INSERT INTO board(routeID ,userID ,boardDate , maxP, currentP,"
				+ " appliT, boardTitle  ,boardContent, stateInt, kakaoLink   ) VALUES (?, ?, ?, ?, ?,    ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
	
			pstmt.setInt(1,  routeID);
			pstmt.setString(2,  userID);
			pstmt.setString(3,  getDate());
			pstmt.setInt(4,  maxP);
			pstmt.setInt(5,  0);
			pstmt.setString(6,  appliT);
			pstmt.setString(7,  boardTitle);
			pstmt.setString(8,  boardContent);
			pstmt.setInt(9,  0);
			pstmt.setString(10,  kakaoLink);
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	
	
	//게시물 받아오기
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
				board.setAppliT(rs.getString(7));
				board.setBoardTitle(rs.getString(8));
				board.setBoardContent(rs.getString(9));
				board.setStateInt(rs.getInt(10));
				board.setKakaoLink(rs.getString(11));
				return board;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 	
	}
	
	
	
	
	//다음 게시물 번호받아오기
	public int getNextBoard() {
		String SQL = "SELECT COUNT(*) FROM board WHERE stateInt <> 1";
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
	
	
	
	//페이징
	public ArrayList<Board> getList(int pageNumber)
	{
		String SQL = "SELECT * FROM (SELECT * FROM board  WHERE stateInt<>1) b ORDER BY boardID DESC LIMIT ?,?";
		
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  (pageNumber - 1) * 10);
			pstmt.setInt(2,  10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setRouteID(rs.getInt(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setMaxP(rs.getInt(5));
				board.setCurrentP(rs.getInt(6));
				board.setAppliT(rs.getString(7));
				board.setBoardTitle(rs.getString(8));
				board.setBoardContent(rs.getString(9));
				board.setStateInt(rs.getInt(10));
				board.setKakaoLink(rs.getString(11));
				list.add(board);

			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	//다음 페이지 받기
	public boolean nextPageBoard(int pageNumber)
	{
		String SQL = "SELECT * FROM board WHERE boardID  < ? AND stateInt  <>0";
		
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
	
	
	
	//게시물 수정 (현재 신청인원보다 최대인원을 줄일 수 없도록 할것)
	public int updateBoard(int boardID ,int routeID, int maxP, String appliT, String boardTitle , String boardContent, String kakaoLink ) {
		String SQL = "UPDATE board SET routeID =?, maxP =?, appliT=?, boardTitle = ?, boardContent = ?, kakaoLink=? WHERE boardID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  routeID);
			pstmt.setInt(2,  maxP);
			pstmt.setString(3,  appliT);
			pstmt.setString(4,  boardTitle);
			pstmt.setString(5,  boardContent);
			pstmt.setString(6,  kakaoLink);
			pstmt.setInt(7,  boardID);
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	
	
	
	
	// 삭제가 안보이게 인지 완전 삭제인지에 따라 테이블 형테 바꿈
	public int delete(int boardID ) {
		String SQL = "UPDATE board SET stateInt  = 1 WHERE boardID  = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  boardID );
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
}
