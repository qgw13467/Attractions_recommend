

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction_" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	
	int attractionID =Integer.parseInt(request.getParameter("attractionID"));
	Attraction_ attraction=new AttractionDAO().getAttractionInfo(attractionID);		

%>


	{
	"attractionID":"<%=attraction.getID()%>",
	"attractionScore":"<%=attraction.getScore()%>",
	"title":"<%=attraction.getTitle()%>",
	"addr":"<%=attraction.getAddr()%>",
	"mapX":"<%=attraction.getX()%>",
	"mapY":"<%=attraction.getY()%>",
	"firstimg":"<%=attraction.getFirstImg()%>",
	"firstimg2":"<%=attraction.getFirstImg2()%>",
	"overview":"<%=attraction.getOverview()%>",
	"Thema1":"<%=attraction.getThema1()%>",
	"Thema2":"<%=attraction.getThema2()%>",
	"Thema3":"<%=attraction.getThema3()%>",
	"Thema4":"<%=attraction.getThema4()%>",
	"Thema5":"<%=attraction.getThema5()%>",
	"Thema6":"<%=attraction.getThema6()%>",
	"Thema7":"<%=attraction.getThema7()%>"
	}
