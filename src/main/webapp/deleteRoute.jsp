<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%
	int routeID=Integer.parseInt(request.getParameter("routeID"));
	RouteDAO routeDAO=new RouteDAO();
	int result=routeDAO.delete(routeID);
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
