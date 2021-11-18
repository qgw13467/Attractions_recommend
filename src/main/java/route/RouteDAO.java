
package route;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class RouteDAO {
	private Connection conn;
	private ResultSet rs;
	
	public RouteDAO() {
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
		String SQL = "SELECT routeID FROM route ORDER BY routeID DESC";
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
	
	
	public int writeRoute(String userID, String routeTitle ,String  routeList ,String Thema,String arriveTime ) {
		String SQL = "INSERT INTO route(userID ,routeDate, routeTitle  ,routeList, stateInt ,Thema, arriveTime) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			

			pstmt.setString(1,  userID);
			pstmt.setString(2,  getDate());
			pstmt.setString(3,  routeTitle);
			pstmt.setString(4,  routeList);
			pstmt.setInt(5,  0);
			pstmt.setString(6, Thema);
			pstmt.setString(7, arriveTime);
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	
	public Route getRoute(int routeID) {
		
		String SQL = "SELECT * FROM route WHERE routeID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  routeID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				
				Route route = new Route();
				route.setRouteID(rs.getInt(1));
				route.setUserID(rs.getString(2));
				route.setRouteDate(rs.getString(3));
				route.setRouteTitle(rs.getString(4));
				route.setRouteList(rs.getString(5));
				route.setStateInt(rs.getInt(6));
				route.setThema(rs.getString(7));
				route.setArriveTime(rs.getString(8));

				return route;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	
	public ArrayList<Route> getList(int pageNumber, String userID)
	{
		String SQL = "SELECT * FROM route WHERE routeID  < ? AND userID = ?  ORDER BY routeID  DESC LIMIT 10";
		
		ArrayList<Route> list = new ArrayList<Route>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNextRoute() - (pageNumber - 1) * 10);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {


				Route route = new Route();
				route.setRouteID(rs.getInt(1));
				route.setUserID(rs.getString(2));
				route.setRouteDate(rs.getString(3));
				route.setRouteTitle(rs.getString(4));
				route.setRouteList(rs.getString(5));
				route.setStateInt(rs.getInt(6));
				route.setThema(rs.getString(7));
				route.setArriveTime(rs.getString(8));
				list.add(route);
			}
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	//경로 수정
	public int updateRoute(int routeID, String boardTitle , String routeList, String Thema, String arriveTime ) {
		String SQL = "UPDATE route SET routeTitle  = ?, routeList  = ?, Thema=?, arriveTime=? WHERE routeID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1,  boardTitle);
			pstmt.setString(2,  routeList);
			pstmt.setString(3,  Thema);
			pstmt.setString(4,  arriveTime);
			pstmt.setInt(5,  routeID);
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}

	
	
	public int delete(int routeID  ) {
		String SQL = "UPDATE route SET stateInt  = 1 WHERE routeID   = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  routeID  );
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	
	//추천을 위해 현제 루트에 포함된 여행지id 배열을 반환
	@SuppressWarnings("null")
	public ArrayList<String> attractionList(int routeID) {
		String SQL = "SELECT routeList FROM route WHERE routeID = ?";
		ArrayList<String> attractionlist = new ArrayList<String>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  routeID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String temp;
				temp=rs.getString(1);
				String[] attractionID=temp.split(",");
				
				for(int i=0;i<attractionID.length;i++) {
					attractionlist.add(attractionID[i]);
				}
				
				
			}
			return attractionlist; 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return attractionlist; 
	}
	
}
