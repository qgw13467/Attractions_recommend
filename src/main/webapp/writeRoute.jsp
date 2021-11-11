

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	String userID =request.getParameter("userID");
	String routeTitle=request.getParameter("routeTitle");
	String routeList=request.getParameter("routeList");
	String Thema =request.getParameter("Thema");
	String arriveTime =request.getParameter("arriveTime");
	RouteDAO routeDAO=new RouteDAO();
	int result=routeDAO.writeRoute( userID, routeTitle, routeList, Thema, arriveTime);
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



