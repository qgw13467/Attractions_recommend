

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

			
	int boardID=Integer.parseInt(request.getParameter("boardID"));
	Board board=new BoardDAO().getBoard(boardID);
	
%>





	{
	"boardID":"<%=board.getBoardID()%>",
	"routeID":"<%=board.getRouteID()%>",
	"userID":"<%=board.getUserID()%>",
	"boardDate":"<%=board.getBoardDate()%>",
	"maxP":"<%=board.getMaxP()%>",
	"currentP":"<%=board.getCurrentP()%>",
	"appliT":"<%=board.getAppliT()%>",
	"boardTitle":"<%=board.getBoardTitle()%>",
	"boardContent":"<%=board.getBoardContent()%>"
	}



