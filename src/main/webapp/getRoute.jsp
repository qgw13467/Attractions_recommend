

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	int routeID =Integer.parseInt(request.getParameter("routeID"));
	Route route=new RouteDAO().getRoute(routeID);		

%>



	{
	"routeID":"<%=route.getRouteID()%>",
	"userID":"<%=route.getUserID()%>",
	"routeDate":"<%=route.getRouteDate()%>",
	"routeTitle":"<%=route.getRouteTitle()%>",
	"routeList":"<%=route.getRouteList()%>",
	"Thema":"<%=route.getThema()%>",
	"arriveTime":"<%=route.getArriveTime()%>"
	}

