<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String userID = request.getParameter("userID");
String pwd = request.getParameter("pwd");

Connection conn = null;
PreparedStatement pstmt_select = null;
ResultSet rs_select = null;

String msg ="";
String success ="";
String nickName ="none";
String gender = "none";

if( userID == null || userID.toUpperCase().equals("NULL") || pwd == null || pwd.toUpperCase().equals("NULL")){
	success ="false";
	msg = "아이디, 비밀번호를 입력해주세요.";
}
else try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com/teamproject";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	String sql = "SELECT nickName, gender FROM user WHERE userID = ? AND pwd = ?";

	pstmt_select = conn.prepareStatement(sql);
	pstmt_select.setString(1,userID);
	pstmt_select.setString(2,pwd);
	
	rs_select = pstmt_select.executeQuery();
	
	if(rs_select.next()){
		nickName = rs_select.getString(1);
		gender = rs_select.getString(2);
		if(gender == null)
		{
			gender = "none";
		}
		
		success = "true";
		msg = "로그인 성공";
	}
	else {
		success = "false";
		msg = "아이디 또는 비밀번호가 틀렸습니다.";
	}
}catch (SQLException e){
	e.printStackTrace();
	success = "false";
	msg = "error code:" + e.getErrorCode();
}
catch (Exception e) {
	e.printStackTrace();
	success = "false";
	msg ="exception";
} finally{
	if(rs_select != null) try{ rs_select.close();} catch(Exception e){}
	if(pstmt_select != null) try{ pstmt_select.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}
%>
{"success":<%=success %>,"msg":"<%=msg%>","nickName":"<%=nickName %>","gender":"<%=gender %>"}
