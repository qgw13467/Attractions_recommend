<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.xml.parsers.DocumentBuilder"%>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.Element"%>
<%@ page import="org.w3c.dom.Node"%>
<%@ page import="org.w3c.dom.NodeList"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="attraction.Attraction_" %>
<%!
private static final String key="bN6M3dpkGO0j8S%2F3a9kgwaHwkfLqpxeQDmDNCpBmfT%2Fy4Zu61493fKgqVRpWtw1J5liqI2QqDHJcsuvakvEz4Q%3D%3D";

private static String getTagValue(String tag, Element eElement) {
	try {
    	NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
    	Node nValue = (Node) nlList.item(0);
    	return nValue.getNodeValue();
	}catch(NullPointerException e){
		return null;
	}
}

private static String busanURL(int page){
	String url ="http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=" +key
			+"&areaCode=6&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=100&pageNo=" + page;
	return url;
}

private static String tourDetailURL(String contentId){
	String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=" +key
			+"&contentId="+contentId+"&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&overviewYN=Y";
	return url;
}



%>
<%
Connection conn = null;
PreparedStatement stmt = null;
ArrayList<Attraction_> arrAttraction = new ArrayList<>();

String sIndex = request.getParameter("index");
int index = 0;
if(sIndex == null)
{
	index =0;
}
else
{
	index = Integer.parseInt(sIndex) * 10;
}

String msg ="";

int pageNo = 1;
while(true){
	String url = busanURL(pageNo);
	DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();	
	DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	Document doc = dBuilder.parse(url);
	
	doc.getDocumentElement().normalize();
	
	NodeList nList = doc.getElementsByTagName("item");
	
	Attraction_ attraction;
	if(nList.getLength() == 0)
	{
		break;
	}
	
	for(int i=0; i< nList.getLength(); i++)
	{
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE){
			Element eElement = (Element) nNode;
			attraction = new Attraction_();
			attraction.setID(getTagValue("contentid", eElement));
			attraction.setThema( getTagValue("cat3", eElement));
			attraction.setTitle(getTagValue("title", eElement));
			attraction.setAddr( getTagValue("addr1", eElement));
			attraction.setX( getTagValue("mapx", eElement));
			attraction.setY( getTagValue("mapy", eElement));
			attraction.setFirstImg( getTagValue("firstimage", eElement));
			attraction.setFirstImg2( getTagValue("firstimage2", eElement));
			
			if(attraction.getX() == null){
				continue; // 위경도 없으면 일단 무시
			}
		
			arrAttraction.add(attraction);
			
		}
	}
	
	pageNo++;
}

for(int i=0; i< 10; i++)
{
	if(i >= arrAttraction.size())
	{
		break;
	}
	String url = tourDetailURL(arrAttraction.get(i + index).getID());
	DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();	
	DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	Document doc = dBuilder.parse(url);
	
	doc.getDocumentElement().normalize();
	
	NodeList nList = doc.getElementsByTagName("item");
	Node nNode = nList.item(0);
	if(nNode.getNodeType() == Node.ELEMENT_NODE){
		Element eElement = (Element) nNode;
		arrAttraction.get(i+ index).setOverview( getTagValue("overview", eElement));
	}
}

try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com/teamproject";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	for(int i=0; i< 10; i++)
	{
		if(i >= arrAttraction.size())
		{
			break;
		}
		
		String sql = "REPLACE INTO attraction"
				+"(attractionID,title,addr,mapx,mapy,firstImage,firstImage2,overview,thema1,thema2,thema3,thema4,thema5,thema6,thema7,attractionScore)"
				+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)" ;
		msg = Integer.toString(i+index);
	
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,arrAttraction.get(i+index).getID());
		stmt.setString(2,arrAttraction.get(i+index).getTitle());
		stmt.setString(3,arrAttraction.get(i+index).getAddr());
		stmt.setString(4,arrAttraction.get(i+index).getX());
		stmt.setString(5,arrAttraction.get(i+index).getY());
		stmt.setString(6,arrAttraction.get(i+index).getFirstImg());
		stmt.setString(7,arrAttraction.get(i+index).getFirstImg2());
		stmt.setString(8,arrAttraction.get(i+index).getOverview());
		for(int j=0; j< 7; j++){
			stmt.setFloat(9+j,arrAttraction.get(i+index).getThema(j));
		}
		
		stmt.executeUpdate();
		
	}
	
	msg = "성공";
}catch (SQLException e){
// 	msg = "error code:" + e.getErrorCode();
	e.printStackTrace();
}finally{
	if(stmt != null) try{ stmt.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}


%>

<response>
<msg><%=msg%></msg> 
</response>
