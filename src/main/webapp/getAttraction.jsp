

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	
	
	int attractionID =Integer.parseInt(request.getParameter("attractionID"));
	Attraction attraction=new AttractionDAO().getAttractionInfo(attractionID);		

%>


<response>
	<attractionName ><%=attraction.getAttractionName()%></attractionName>
	<attractionID ><%=attraction.getAttractionID()%></attractionID>
	<addr1> <%=attraction.getAddr1()%>  </addr1>
	<Thema><%=attraction.getThema()%></Thema>
	<mapX><%=attraction.getMapX()%></mapX>
	<mapY><%=attraction.getMapY()%></mapY>
	<attractionScore><%=attraction.getAttractionScore()%></attractionScore>
</response>
