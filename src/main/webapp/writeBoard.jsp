

<%@ page language="java" contentType="text/xml; charset=UTF-8"
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

<?xml version="1.0" encoding="UTF-8"?>

<response>

<%
	if(result==-1){
		%>
		<success>false</success>
		<%
	}
	else{
		%>
		<success>true</success>
		<%
	}
%>
</response>


