<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String boardID = request.getParameter("boardID");
String guestID = request.getParameter("userID");

Connection conn = null;
PreparedStatement pstmt_insert = null;
PreparedStatement pstmt_select = null;
ResultSet rs_select = null;
String msg ="";
String success ="";
String kakaoLink = "none";
try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");

	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	String sql = "INSERT INTO permission VALUES(?,?,0)";
	pstmt_insert = conn.prepareStatement(sql);
	
	pstmt_insert.setString(1,boardID);
	pstmt_insert.setString(2,guestID);
	
	int r = pstmt_insert.executeUpdate();
	
	sql = "SELECT kakaoLink FROM board WHERE boardID = ?";
	pstmt_select = conn.prepareStatement(sql);
	pstmt_select.setString(1,boardID);
	rs_select = pstmt_select.executeQuery();
	
	if(rs_select.next()){
		kakaoLink = rs_select.getString(1);
		
		if(kakaoLink == null)
		{
			kakaoLink = "None";
		}
	}
	
	success = "true";

	
}catch (SQLException e){
	success = "false";
	if(e.getErrorCode() == 1452){ // 외래키 매칭 실패시. (잘못된 boardID or userID)
		msg = "요청 정보가 유효하지 않습니다.";
	}
	else if(e.getErrorCode() == 1062){ // 키값 중복. (이미 신청한 곳에 또 신청함)
		msg = "이미 신청한 적이 있는 게시글입니다.";
	}
	else {
		msg = "error code:" + e.getErrorCode();
	}
}
catch (Exception e) {
	e.printStackTrace();
	success = "false";
	msg ="exception";
} finally{
	if(rs_select != null) try{ rs_select.close();} catch(Exception e){}
	if(pstmt_insert != null) try{ pstmt_insert.close();} catch(Exception e){}
	if(pstmt_select != null) try{ pstmt_select.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}

%>
<response>
<success><%=success %></success>
<msg><%=msg%></msg> 
<kakaoLink><%=kakaoLink %></kakaoLink>
</response>
