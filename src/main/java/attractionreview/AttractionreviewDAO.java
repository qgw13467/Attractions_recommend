package attractionreview;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.Board;
import route.Route;
import route.RouteDAO;


public class AttractionreviewDAO {
	private Connection conn;
	private ResultSet rs;
	
	public AttractionreviewDAO() {
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
	
	//여행지 폄점 쓰기
	public int writeAttractionreview(String userID  , String attractionID, int score  ) {
		String SQL = "INSERT INTO attractionreview(userID ,attractionID ,score, reviewDate ) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
	
			pstmt.setString(1,  userID);
			pstmt.setString(2,  attractionID);
			pstmt.setInt(3,  score);
			pstmt.setString(4,  getDate());
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	

	//여행지 폄점 읽기
	public AttractionReview getAttractionreview(String userID  , String attractionID) {
		
		String SQL = "SELECT * FROM attractionreview WHERE userID = ?,attractionID=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			pstmt.setString(2,  attractionID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				AttractionReview attractionReview = new AttractionReview();
				attractionReview.setUserID(rs.getString(1));
				attractionReview.setAttractionID(rs.getString(2));
				attractionReview.setScore(rs.getInt(3));
				return attractionReview;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
		
	}
	
	//여행지 평균 평점 구하기
	public float getAttractionreview(String attractionID) {
		
		String SQL = "SELECT AVG(score) FROM attractionreview WHERE attractionID=?";
		float score=0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  attractionID);;
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				return score=rs.getFloat(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return score; 
		
	}
}
