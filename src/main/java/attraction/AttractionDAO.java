package attraction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.Board;



public class AttractionDAO {
	private Connection conn;
	private ResultSet rs;
	
	public AttractionDAO() {
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
	
	//모든 여행지 정보 가져오기
	public ArrayList<Attraction> getList()
	{
		String SQL = "SELECT * FROM attraction";
		
		ArrayList<Attraction> list = new ArrayList<Attraction>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Attraction attraction = new Attraction();
				attraction.setAttractionName(rs.getString(1));
				attraction.setAttractionID(rs.getInt(2));
				attraction.setAddr1(rs.getString(3));
				attraction.setThema(rs.getInt(4));
				attraction.setMapX(rs.getFloat(5));
				attraction.setMapY(rs.getFloat(6));
				attraction.setAttractionScore(rs.getFloat(7));
				list.add(attraction);

			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//여행지 정보 받아오기
	public Attraction getAttractionInfo(int attractionID) {
		
		String SQL = "SELECT * FROM attraction WHERE attractionID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  attractionID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				Attraction attraction = new Attraction();
				attraction.setAttractionName(rs.getString(1));
				attraction.setAttractionID(rs.getInt(2));
				attraction.setAddr1(rs.getString(3));
				attraction.setThema(rs.getInt(4));
				attraction.setMapX(rs.getFloat(5));
				attraction.setMapY(rs.getFloat(6));
				attraction.setAttractionScore(rs.getFloat(7));
				return attraction;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
}
