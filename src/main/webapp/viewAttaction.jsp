

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction" %>
<%@ page import="attraction.AttractionDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.ArrayList"%>

<%
	
	

	
	AttractionDAO attractionDAO=new AttractionDAO();
	ArrayList<Attraction> list= attractionDAO.getList();
%>



<response>
<%
for(int i=0;i<list.size();i++){
	%>
	<boards>
		<attractionName ><%=list.get(i).getAttractionName()%></attractionName>
		<attractionID ><%=list.get(i).getAttractionID()%></attractionID>
		<addr1> <%=list.get(i).getAddr1()%>  </addr1>
		<Thema><%=list.get(i).getThema()%></Thema>
		<mapX><%=list.get(i).getMapX()%></mapX>
		<mapY><%=list.get(i).getMapY()%></mapY>
		<attractionScore><%=list.get(i).getAttractionScore()%></attractionScore>
	</boards>
	
	<%
}
%>
</response>
