package attraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import route.Route;
import route.RouteDAO;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 모든 여행지 정보 가져오기
	public ArrayList<Attraction_> getList() {
		String SQL = "SELECT * FROM attraction";

		ArrayList<Attraction_> list = new ArrayList<Attraction_>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Attraction_ attraction = new Attraction_();
				attraction.setID(rs.getString(1));
				attraction.setScore(rs.getFloat(2));
				attraction.setTitle(rs.getString(3));
				attraction.setAddr(rs.getString(4));
				attraction.setX(rs.getString(5));
				attraction.setY(rs.getString(6));
				attraction.setFirstImg(rs.getString(7));
				attraction.setFirstImg2(rs.getString(8));
				attraction.setOverview(rs.getString(9));
				attraction.setThema1(rs.getFloat(10));
				attraction.setThema2(rs.getFloat(11));
				attraction.setThema3(rs.getFloat(12));
				attraction.setThema4(rs.getFloat(13));
				attraction.setThema5(rs.getFloat(14));
				attraction.setThema6(rs.getFloat(15));
				attraction.setThema7(rs.getFloat(16));
				list.add(attraction);

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public ArrayList<Attraction_> Arr = getList();

	// 여행지 정보 받아오기
	public Attraction_ getAttractionInfo(String attractionID) {

		String SQL = "SELECT * FROM attraction WHERE attractionID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, attractionID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Attraction_ attraction = new Attraction_();
				attraction.setID(rs.getString(1));
				attraction.setScore(rs.getFloat(2));
				attraction.setTitle(rs.getString(3));
				attraction.setAddr(rs.getString(4));
				attraction.setX(rs.getString(5));
				attraction.setY(rs.getString(6));
				attraction.setFirstImg(rs.getString(7));
				attraction.setFirstImg2(rs.getString(8));
				attraction.setOverview(rs.getString(9));
				attraction.setThema1(rs.getFloat(10));
				attraction.setThema2(rs.getFloat(11));
				attraction.setThema3(rs.getFloat(12));
				attraction.setThema4(rs.getFloat(13));
				attraction.setThema5(rs.getFloat(14));
				attraction.setThema6(rs.getFloat(15));
				attraction.setThema7(rs.getFloat(16));
				return attraction;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Attraction_> recommend( float mapX, float mapY, float distance) {
		
		RouteDAO routedao = new RouteDAO();
		ArrayList<Attraction_> list = new ArrayList<Attraction_>();
		

		


		String SQL = "SELECT *, (6371 * acos ( cos ( radians(?) ) * cos( radians( mapx ) ) * cos( radians( mapy ) "
				+ "- radians(?) )+ sin ( radians(?))* sin( radians( mapx )))) "
				+ "AS distance FROM attraction HAVING distance < ? ORDER BY distance limit 50;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setFloat(1, (float) mapX);
			pstmt.setFloat(2, (float) mapY);
			pstmt.setFloat(3, (float) mapX);
			pstmt.setFloat(4, distance);
			rs = pstmt.executeQuery();


			
			while (rs.next()) {
				Attraction_ attraction = new Attraction_();
				attraction.setID(rs.getString(1));
				attraction.setScore(rs.getFloat(2));
				attraction.setTitle(rs.getString(3));
				attraction.setAddr(rs.getString(4));
				attraction.setX(rs.getString(5));
				attraction.setY(rs.getString(6));
				attraction.setFirstImg(rs.getString(7));
				attraction.setFirstImg2(rs.getString(8));
				attraction.setOverview(rs.getString(9));
				attraction.setThema1(rs.getFloat(10));
				attraction.setThema2(rs.getFloat(11));
				attraction.setThema3(rs.getFloat(12));
				attraction.setThema4(rs.getFloat(13));
				attraction.setThema5(rs.getFloat(14));
				attraction.setThema6(rs.getFloat(15));
				attraction.setThema7(rs.getFloat(16));
				list.add(attraction);
			}
			

			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 추천받은 여행지목록중 거리가 distance km이내인 여행지를 넘김
	public ArrayList<Attraction_> filter(float mapX, float mapY, ArrayList<Attraction_> items, float distance) {

		ArrayList<Attraction_> list = new ArrayList<Attraction_>();
		for (int i=0;i<items.size();i++) {
			String SQL = "select *, (6371 * acos( cos( radians(?) ) * cos( radians( ? ) ) * cos( radians( ? ) "
					+ "- radians(?) ) + sin( radians(?) ) * sin( radians( ? ) ) ) ) as distance"
					+ "FROM attraction HAVING distance < ? AND attractionID= ?";
			

			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setFloat(1, (float) mapX);
				pstmt.setFloat(2, Float.parseFloat(items.get(i).getX()));
				pstmt.setFloat(3, Float.parseFloat(items.get(i).getY()));
				pstmt.setFloat(4, (float) mapY);
				pstmt.setFloat(5, (float) mapX);
				pstmt.setFloat(6, Float.parseFloat(items.get(i).getX()));
				pstmt.setFloat(7, distance);
				pstmt.setString(8, items.get(i).getID());
				rs = pstmt.executeQuery();

				while (rs.next()) {

					Attraction_ attraction = new Attraction_();
					attraction.setID(rs.getString(1));
					attraction.setScore(rs.getFloat(2));
					attraction.setTitle(rs.getString(3));
					attraction.setAddr(rs.getString(4));
					attraction.setX(rs.getString(5));
					attraction.setY(rs.getString(6));
					attraction.setFirstImg(rs.getString(7));
					attraction.setFirstImg2(rs.getString(8));
					attraction.setOverview(rs.getString(9));
					attraction.setThema1(rs.getFloat(10));
					attraction.setThema2(rs.getFloat(11));
					attraction.setThema3(rs.getFloat(12));
					attraction.setThema4(rs.getFloat(13));
					attraction.setThema5(rs.getFloat(14));
					attraction.setThema6(rs.getFloat(15));
					attraction.setThema7(rs.getFloat(16));
					list.add(attraction);
				}
				
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

//		public ArrayList<Attraction_> recommend(int routeID,String attractionID, double mapX, double mapY,ArrayList<String> items) {
//			RouteDAO routedao=new RouteDAO();
//			ArrayList<Integer> attractionlist = routedao.attractionList(routeID);
//			ArrayList<Attraction_> list = new ArrayList<Attraction_>();
//			String SQL = "select * from attraction x,(select * from attraction)where sqrt(power(mapX - x.mapX,2) + power(mapY -  x.mapY,2)) <= 2000";
//			
//			try {
//				PreparedStatement pstmt = conn.prepareStatement(SQL);
//				pstmt.setFloat(1,  (float) mapX);
//				pstmt.setFloat(2,  (float) mapY);
//				rs= pstmt.executeQuery();
//				while(rs.next())
//				{	
//					int check=0;
//					int temp=rs.getInt(1);
//					for(int i=0;i<attractionlist.size();i++) {
//						if(temp==attractionlist.get(i)) {
//							check=1;
//							break;
//						}
//					}
//					
//					if(check==0) {
//						Attraction_ attraction = new Attraction_();
//						attraction.setID(rs.getString(1));
//						attraction.setScore(rs.getFloat(2));
//						attraction.setTitle(rs.getString(3));
//						attraction.setAddr(rs.getString(4));
//						attraction.setX(rs.getString(5));
//						attraction.setY(rs.getString(6));
//						attraction.setFirstImg(rs.getString(7));
//						attraction.setFirstImg2(rs.getString(8));
//						attraction.setOverview(rs.getString(9));
//						attraction.setThema1(rs.getFloat(10));
//						attraction.setThema2(rs.getFloat(11));
//						attraction.setThema3(rs.getFloat(12));
//						attraction.setThema4(rs.getFloat(13));
//						attraction.setThema5(rs.getFloat(14));
//						attraction.setThema6(rs.getFloat(15));
//						attraction.setThema7(rs.getFloat(16));
//						list.add(attraction);
//					}
//					
//				}
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//			return list; 
//		}

	// 평점 갱신
	public int updateScore(String attractionID, float score) {
		String SQL = "UPDATE attraction SET attractionScore  =? WHERE attractionID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setFloat(1, score);
			pstmt.setString(2, attractionID);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
