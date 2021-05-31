<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String userID = request.getParameter("userID");
String nickName = request.getParameter("nickName");
String gender = request.getParameter("gender");
String pwd = request.getParameter("pwd");

if(!"0".equals(gender) && !"1".equals(gender))
{
	gender = null;
}

Connection conn = null;
PreparedStatement pstmt_select = null;
PreparedStatement pstmt_insert = null;
ResultSet rs_select = null;

String msg ="";
String success ="";

if( userID == null || userID.toUpperCase().equals("NULL") ||nickName == null || nickName.toUpperCase().equals("NULL") 
	||pwd == null || pwd.toUpperCase().equals("NULL")){
	success ="false";
	msg = "아이디, 비밀번호, 닉네임은 반드시 필요합니다.";
}
else try {
	String dbURL = "jdbc:mysql://teamproject.cor0tt1ne1ys.ap-northeast-2.rds.amazonaws.com/teamproject";
	String dbID = "admin";
	String dbPassword = "123456789";
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	
	String sql = "SELECT userID FROM user WHERE userID = ?";

	pstmt_select = conn.prepareStatement(sql);
	
	pstmt_select.setString(1,userID);
	
	rs_select = pstmt_select.executeQuery();
	
	if(rs_select.next()){
		success = "false";
		msg = "ID 중복";
	}
	else {
		sql = "INSERT INTO user VALUES(?,?,?,?)";
		pstmt_insert = conn.prepareStatement(sql);
		pstmt_insert.setString(1,userID);
		pstmt_insert.setString(2,nickName);
		pstmt_insert.setString(3,gender);
		pstmt_insert.setString(4,pwd);
		pstmt_insert.executeLargeUpdate();
		
		success = "true";
		msg = "아이디 생성 성공";
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
	if(pstmt_insert != null) try{ pstmt_insert.close();} catch(Exception e){}
	if(pstmt_select != null) try{ pstmt_select.close();} catch(Exception e){}
	if(conn != null) try{ conn.close();} catch(Exception e){}
}
%>

{"success":<%=success %>,"msg":"<%=msg%>"}
