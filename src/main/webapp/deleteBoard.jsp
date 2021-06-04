<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	int boardID=Integer.parseInt(request.getParameter("boardID"));
	
	
	BoardDAO boardDAO=new BoardDAO();
	int result=boardDAO.delete(boardID);
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
