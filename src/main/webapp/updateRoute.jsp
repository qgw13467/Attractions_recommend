

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	int routeID=Integer.parseInt(request.getParameter("routeID"));
	String routeTitle=request.getParameter("routeTitle");
	String routeList=request.getParameter("routeList");
	String Thema =request.getParameter("Thema");
	String arriveTime =request.getParameter("arriveTime");
			
			
	RouteDAO routeDAO=new RouteDAO();
	int result=routeDAO.updateRoute(routeID, routeTitle, routeList, Thema, arriveTime);
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


