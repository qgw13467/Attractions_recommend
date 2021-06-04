

<%@ page language="java" contentType="application/json; charset=UTF-8"
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



{
	"routes":
		[
			<%
				for(int i=0;i<list.size();i++){
			%>
				{
				"routeID":"<%=list.get(i).getRouteID()%>",
				"userID":"<%=list.get(i).getUserID()%>",
				"routeDate":"<%=list.get(i).getRouteDate() %>",
				"routeTitle":"<%=list.get(i).getRouteTitle() %>",
				"routeList":"<%=list.get(i).getRouteList() %>",
				"Thema":"<%=list.get(i).getThema() %>",
				"arriveTime":"<%=list.get(i).getArriveTime() %>"
				}<%	if(i!=list.size()-1){%>,<%
					}
				}%>
		]
}
