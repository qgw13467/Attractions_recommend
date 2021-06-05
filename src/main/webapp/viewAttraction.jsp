

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction_" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.ArrayList"%>

<%
	
	

	AttractionDAO attractionDAO=new AttractionDAO();
	ArrayList<Attraction_> list= attractionDAO.getList();
	

%>



{
	"attractions":
		[
			<%
				for(int i=0;i<list.size();i++){
			%>
				{
				"attractionID":"<%=list.get(i).getID()%>",
				"attractionScore":"<%=list.get(i).getScore()%>",
				"title":"<%=list.get(i).getTitle()%>",<%String[] temp = list.get(i).getAddr().split("\\s"); String addr=temp[0] + "\\s" + temp[1];%>
				"addr":"<%=addr%>",
				"mapX":"<%=list.get(i).getX()%>",
				"mapY":"<%=list.get(i).getY()%>",
				"Thema1":"<%=list.get(i).getThema1()%>",
				"Thema2":"<%=list.get(i).getThema2()%>",
				"Thema3":"<%=list.get(i).getThema3()%>",
				"Thema4":"<%=list.get(i).getThema4()%>",
				"Thema5":"<%=list.get(i).getThema5()%>",
				"Thema6":"<%=list.get(i).getThema6()%>",
				"Thema7":"<%=list.get(i).getThema7()%>"
				}<%	if(i!=list.size()-1){%>,<%
					}
				}%>
		]
}
