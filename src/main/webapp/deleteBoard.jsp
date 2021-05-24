

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	int boardID=Integer.parseInt(request.getParameter("boardID"));
	
	
	BoardDAO boardDAO=new BoardDAO();
	int result=boardDAO.delete(boardID);
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

