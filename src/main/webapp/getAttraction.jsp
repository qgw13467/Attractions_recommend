

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	
	int attractionID =Integer.parseInt(request.getParameter("attractionID"));
	Attraction attraction=new AttractionDAO().getAttractionInfo(attractionID);		

%>


	{
	"attractionName":"<%=attraction.getAttractionName()%>",
	"attractionID":"<%=attraction.getAttractionID()%>",
	"addr1":"<%=attraction.getAddr1()%>",
	"Thema":"<%=attraction.getThema()%>",
	"mapX":"<%=attraction.getMapX()%>",
	"mapY":"<%=attraction.getMapY()%>",
	"attractionScore":"<%=attraction.getAttractionScore()%>"
	}
