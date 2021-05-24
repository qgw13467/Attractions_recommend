

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	int boardID=Integer.parseInt(request.getParameter("boardID"));
	int routeID=Integer.parseInt(request.getParameter("routeID"));
	String userID =request.getParameter("userID");
	int maxP =Integer.parseInt(request.getParameter("maxP"));
	int currentP=Integer.parseInt(request.getParameter("currentP"));
	String appliT=request.getParameter("appliT");
	String boardTitle=request.getParameter("boardTitle");
	String boardContent=request.getParameter("boardContent");
	String kakaoLink=request.getParameter("kakaoLink");
			
			
	BoardDAO boardDAO=new BoardDAO();
	int result=boardDAO.updateBoard(boardID, routeID, maxP, appliT, boardTitle, boardContent, kakaoLink);
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


