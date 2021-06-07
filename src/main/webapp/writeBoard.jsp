

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%

	int routeID=Integer.parseInt(request.getParameter("routeID"));
	String userID=request.getParameter("userID");
	int maxP=Integer.parseInt(request.getParameter("maxP"));
	String appliT=request.getParameter("appliT");
	String boardTitle =request.getParameter("boardTitle");
	String boardContent=request.getParameter("boardContent");
	String kakaoLink=request.getParameter("kakaoLink");
	
	BoardDAO boardDAO=new BoardDAO();
	int result=boardDAO.writeBoard(routeID, userID, maxP, appliT, boardTitle , boardContent, kakaoLink);
%>

<%
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>

<%
	if(result==-1){
		%>
		{"success":"false"}
		<%
	}
	else{
		%>
		{"success":"true"}
		<%
	}
%>


