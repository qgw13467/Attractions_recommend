
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="attraction.Attraction_" %>
<%@ page import="attraction.AttractionDAO" %>
<%@ page import="attractionreview.AttractionReview" %>
<%@ page import="attractionreview.AttractionreviewDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

	String userID =request.getParameter("userID");
	String attractionID=request.getParameter("attractionID");
	int score =Integer.parseInt(request.getParameter("score"));

	
	AttractionreviewDAO attractionreviewDAO= new AttractionreviewDAO();
	AttractionDAO attractionDAO=new AttractionDAO();
	
	float fscore=attractionreviewDAO.getAttractionreview(attractionID);
	
	int result= attractionreviewDAO.writeAttractionreview(userID,attractionID,score);
	int result2= attractionDAO.updateScore(attractionID,fscore);
%>



<%
	if(result==-1||result2==-1){
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


