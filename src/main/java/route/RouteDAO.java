
package route;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.Board;



public class RouteDAO {
	private Connection conn;
	private ResultSet rs;
	
	public RouteDAO() {
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
	
	public int getNextRoute() {
		String SQL = "SELECT boardID FROM route ORDER BY routeID DESC";
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
	
	
	public int writeRoute(String userID, String routeTitle ,String  routeList  ) {
		String SQL = "INSERT INTO route(routeID ,userID ,routeDate, routeTitle  ,routeList ) VALUES (?, ?, ?, ?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1,  getNextRoute());
			pstmt.setString(2,  userID);
			pstmt.setString(3,  getDate());
			pstmt.setString(4,  routeTitle);
			pstmt.setString(5,  routeList);
				
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	
	public Route getRoute(int boardID) {
		
		String SQL = "SELECT * FROM board WHERE boardID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  boardID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				Route route = new Route();
				route.setRouteID(rs.getInt(1));
				route.setUserID(rs.getString(2));
				route.setRouteDate(rs.getString(3));
				route.setRouteTitle(rs.getString(4));
				route.setRouteList(rs.getString(5));
				
				return route;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	
	public ArrayList<Route> getList(int pageNumber)
	{
		String SQL = "SELECT * FROM route WHERE routeID  < ?  ORDER BY routeID  DESC LIMIT 10";
		
		ArrayList<Route> list = new ArrayList<Route>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNextRoute() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {


				Route route = new Route();
				route.setRouteID(rs.getInt(1));
				route.setUserID(rs.getString(2));
				route.setRouteDate(rs.getString(3));
				route.setRouteTitle(rs.getString(4));
				route.setRouteList(rs.getString(5));
				list.add(route);
				
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
