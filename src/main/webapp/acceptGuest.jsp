<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%
String userID = request.getParameter("userID");
String boardID = request.getParameter("boardID");
int accept = -1;
if(request.getParameter("accept").equals("true")){
	accept = 1;
}
else{
	accept = 2;
}

Connection conn = null;
PreparedStatement pstmt_select = null;
PreparedStatement pstmt_update = null;
ResultSet rs_select = null;

String msg ="";
String success ="";

try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	String sql = "SELECT stateInt FROM permission WHERE boardID = ? AND userID = ?";

	pstmt_select = conn.prepareStatement(sql);
	
	pstmt_select.setString(1,boardID);
	pstmt_select.setString(2,userID);
	
	rs_select = pstmt_select.executeQuery();
	
	if(rs_select.next()){
		int state = rs_select.getInt(1);
		
		if(state == 0){ // 0 : 신청후 수락전.
			sql = "UPDATE permission SET stateInt = ? WHERE boardID = ? AND userID = ?";
			pstmt_update = conn.prepareStatement(sql);
			pstmt_update.setInt(1,accept);
			pstmt_update.setString(2,boardID);
			pstmt_update.setString(3,userID);
			pstmt_update.executeUpdate();
			success = "true";
			if(state == 1){
				msg ="수락완료";
			}
			else{
				msg ="거절완료";
			}
		}
		else {
			success = "false";
			msg ="이미 수락 또는 거절한 요청입니다.";
		}
	}
	else {
		success = "false";
		msg ="잘못된 요청";
	}
}catch (SQLException e){
	success = "false";
	msg = "error code:" + e.getErrorCode();
}
catch (Exception e) {
	e.printStackTrace();
	success = "false";
	msg ="exception";
} finally{
	if(rs_select != null) try{ rs_select.close();} catch(Exception e){}
	if(pstmt_update != null) try{ pstmt_update.close();} catch(Exception e){}
	if(pstmt_select != null) try{ pstmt_select.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}
%>

<response>
<success><%=success %></success>
<msg><%=msg%></msg> 
</response>
