

<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.Board" %>
<%@ page import="board.BoardDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.ArrayList"%>

<%
	//마지막 페이지의 번호보내기
	int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
	BoardDAO boardDAO=new BoardDAO();
	ArrayList<Board> list= boardDAO.getList(pageNumber);
%>

<?xml version="1.0" encoding="UTF-8"?>

<response>
<%
for(int i=0;i<list.size();i++){
	%>
	<boards>
		<boardID><%=list.get(i).getBoardID()%></boardID>
		<routeID ><%=list.get(i).getRouteID() %></routeID>
		<userID> <%=list.get(i).getUserID() %>  </userID>
		<boardDate><%=list.get(i).getBoardDate() %></boardDate>
		<maxP><%=list.get(i).getMaxP() %></maxP>
		<currentP><%=list.get(i).getCurrentP() %></currentP>
		<appliT><%=list.get(i).getAppliT() %></appliT>
		<boardTitle><%=list.get(i).getBoardTitle() %></boardTitle>
	</boards>
	
	<%
}
%>
</response>
