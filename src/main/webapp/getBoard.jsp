

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

			
	int boardID=Integer.parseInt(request.getParameter("boardID"));
	Board board=new BoardDAO().getBoard(boardID);
	
%>





<?xml version="1.0" encoding="UTF-8"?>
<response>
	<boardID><%=board.getBoardID()%></boardID>
	<routeID ><%=board.getRouteID()%></routeID>
	<userID> <%=board.getUserID() %>  </userID>
	<boardDate><%=board.getBoardDate() %></boardDate>
	<maxP><%=board.getMaxP() %></maxP>
	<currentP><%=board.getCurrentP() %></currentP>
	<appliT><%=board.getAppliT() %></appliT>
	<boardTitle><%=board.getBoardTitle() %></boardTitle>
	<boardContent><%=board.getBoardContent() %></boardContent>
</response>



