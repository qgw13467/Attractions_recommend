

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	int routeID =Integer.parseInt(request.getParameter("routeID"));
	Route route=new RouteDAO().getRoute(routeID);		

%>


<?xml version="1.0" encoding="UTF-8"?>
<response>
	<routeID><%=route.getRouteID()%></routeID>
	<userID><%=route.getUserID()%></userID>
	<routeDate > <%=route.getRouteDate()%>  </routeDate >
	<routeTitle ><%=route.getRouteTitle()%></routeTitle >
	<routeList ><%=route.getRouteList()%></routeList >
	<Thema><%=route.getThema()%></Thema >
	<arriveTime><%=route.getArriveTime()%></arriveTime>
</response>



