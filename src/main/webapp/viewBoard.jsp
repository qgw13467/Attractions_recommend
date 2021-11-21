

<%@ page language="java" contentType="application/json; charset=UTF-8"
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




{	
	"page":"<%=list.size()%>",
	"boards":
		[
			<%
				for(int i=0;i<list.size();i++){
			%>
				{
				"boardID":"<%=list.get(i).getBoardID()%>",
				"routeID":"<%=list.get(i).getRouteID() %>",
				"userID":"<%=list.get(i).getUserID()%>",
				"boardDate":"<%=list.get(i).getBoardDate()%>",
				"maxP":"<%=list.get(i).getMaxP() %>",
				"currentP":"<%=list.get(i).getCurrentP() %>",
				"appliT":"<%=list.get(i).getAppliT() %>",
				"boardTitle":"<%=list.get(i).getBoardTitle() %>"
				}<%	if(i!=list.size()-1){%>,<%
					}
				}%>
		]
}
