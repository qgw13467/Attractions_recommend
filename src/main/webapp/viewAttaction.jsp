

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.ArrayList"%>

<%
	
	

	
	AttractionDAO attractionDAO=new AttractionDAO();
	ArrayList<Attraction> list= attractionDAO.getList();
%>



{
	"attractions":
		[
			<%
				for(int i=0;i<list.size();i++){
			%>
				{
				"attractionName":"<%=list.get(i).getAttractionName()%>",
				"attractionID":"<%=list.get(i).getAttractionID()%>",
				"addr1":"<%=list.get(i).getAddr1()%>",
				"Thema":"<%=list.get(i).getThema()%>",
				"mapX":"<%=list.get(i).getMapX()%>",
				"mapY":"<%=list.get(i).getMapY()%>",
				"attractionScore":"<%=list.get(i).getAttractionScore()%>"
				}
			<%		if(i!=list.size()-1){%>
					,<%
					}
				}
			%>
		
		
		
		]
}
