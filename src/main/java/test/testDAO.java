
package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class testDAO {
	private Connection conn;
	private ResultSet rs;
	
	public testDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/teamproject?serverTimezone=UTC";
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
	
	public int getNextRoute() {
		String SQL = "SELECT testID FROM testtable ORDER BY testID DESC";
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
	
	
	public int writeRoute(int test ) {
		String SQL = "INSERT INTO testtable(testID, test ) "
				+ "VALUES (?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1,  getNextRoute());
			pstmt.setInt(2,  test);

			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public test getRoute(int testID) {
		
		String SQL = "SELECT * FROM testtable WHERE testID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  testID);
			rs= pstmt.executeQuery();
			if(rs.next())
			{
				test route = new test();
				route.setTest(rs.getInt(1));
				route.setTestID(rs.getInt(2));
				return route;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	
}
