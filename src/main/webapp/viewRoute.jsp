

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.ArrayList"%>

<%

	int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
	String userID =request.getParameter("userID");
	RouteDAO routeDAO=new RouteDAO();
	ArrayList<Route> list= routeDAO.getList(pageNumber, userID);
%>

<?xml version="1.0" encoding="UTF-8"?>

<response>
<%
for(int i=0;i<list.size();i++){
	%>
	<routes>
		<routeID><%=list.get(i).getRouteID()%></routeID>
		<userID><%=list.get(i).getUserID()%></userID>
		<routeDate> <%=list.get(i).getRouteDate() %>  </routeDate>
		<routeTitle><%=list.get(i).getRouteTitle() %></routeTitle>
		<routeList><%=list.get(i).getRouteList() %></routeList>
		<Thema><%=list.get(i).getThema() %></Thema>
		<arriveTime><%=list.get(i).getArriveTime() %></arriveTime>
	</routes>
	<%
}
%>
</response>
