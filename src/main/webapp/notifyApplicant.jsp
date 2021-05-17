<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<response>

<%
int number=0;

String userID = request.getParameter("userID");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");

	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	String sql = "SELECT permission.boardID, board.boardTitle, permission.userID, user.nickName "
			+ "FROM permission "
				+ "JOIN board ON permission.boardID = board.boardID "
				+ "JOIN user ON permission.userID = user.userID "
			+ "WHERE permission.stateInt = 0 AND board.userID = ?";
	pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	pstmt.setString(1,userID);
	rs = pstmt.executeQuery();
	
	rs.last(); 
	number = rs.getRow(); 
	rs.beforeFirst();
	
%>

<number><%=number %> </number>
<items>
<%
	while(rs.next()){
%>	
<item>
<boardID><%=rs.getString(1) %></boardID>
<boardTitle><%=rs.getString(2) %></boardTitle>
<userID><%=rs.getString(3) %></userID>
<nickName><%=rs.getString(4) %></nickName>
</item>
<%	
	}
%>
</items>

<%
}catch (Exception e) {
	e.printStackTrace();
}finally {
	if(rs != null) try{ rs.close();} catch(Exception e){}
	if(pstmt != null) try{ pstmt.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}
%>
</response>
