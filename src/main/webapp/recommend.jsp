

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="attraction.Attraction_" %>
<%@ page import="attraction.AttractionDAO" %>
<%@ page import="route.Route" %>
<%@ page import="route.RouteDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.MalformedURLException" %>
<%@ page import="java.net.URL" %>

<% request.setCharacterEncoding("UTF-8"); %>

<%	
	float mapX=Float.parseFloat(request.getParameter("mapX"));
	float mapY=Float.parseFloat(request.getParameter("mapY"));
	int routeID =Integer.parseInt(request.getParameter("routeID"));
	String attractionID=request.getParameter("attractionID");
	String userID=request.getParameter("userID");
	
	String recom="http://13.125.244.193:8081/?userId="+userID;
	URL url = new URL(recom);
	InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
	JSONObject object = (JSONObject)JSONValue.parse(isr);
	JSONArray JSONitems = (JSONArray) object.get("attractionID");
	ArrayList<String > names=new ArrayList<String>();
	for(int i=0;i<JSONitems.size();i++){
		names.add((String)JSONitems.get(i));
	}
	
	RouteDAO routeDAO=new RouteDAO();
	ArrayList<String> exc=routeDAO.attractionList(routeID);
	ArrayList<Attraction_> items= new ArrayList<Attraction_>();
	ArrayList<Attraction_> result= new ArrayList<Attraction_>();
	
	for(String name:names){
		items.add(new AttractionDAO().getAttractionInfo(name));
	}
	
	
	ArrayList<Attraction_> list=new AttractionDAO().recommend(mapX,mapY,2);		
	


	Iterator<Attraction_> ite = items.iterator();
	Iterator<String> ite2 = exc.iterator();
	while(ite.hasNext()) {
		Attraction_ item = ite.next();
		while(ite2.hasNext()) {
			
			String e = ite2.next();
		     if (item.getID().equals(e)) {
		    	 ite.remove();
		     }
		}    
	}
	
	ite = list.iterator();
	ite2 = exc.iterator();
	while(ite.hasNext()) {
		Attraction_ item = ite.next();
		while(ite2.hasNext()) {
			
			String e = ite2.next();
		     if (item.getID().equals(e)) {
		    	 ite.remove();
		     }
		}    
	}
	
	Iterator<Attraction_> ite3 = items.iterator();
	Iterator<Attraction_> ite4 = list.iterator();
	while(ite.hasNext()) {
		Attraction_ item = ite3.next();
		while(ite2.hasNext()) {
			
			Attraction_ l = ite4.next();
		     if (item.getID().equals(l.getID())) {
		    	 ite4.remove();
		    	 result.add(item);
		     }
		}    
	}
	
	
/* 	for(Attraction_ item:items	){
		for(String e:exc){
			if(item.getID().equals(e)){
				items.remove(item);
			}
		}
	}
	
	for(String e:exc){
		for(Attraction_ l:list){
			if(e.equals(l.getID())){
				list.remove(l);
			}
		}
	}
	

	for(Attraction_ item:items	){
		for(Attraction_ l:list){
			if(item.getID().equals(l.getID())){
				items.remove(item);
				list.remove(l);
			}
		}
	}
	 */
	for(int i=0;i<list.size();i++){
		if(result.size()>20)break;
		result.add(list.get(i));
	}
	

%>


{
	"attractions":
		[
			<%
				for(int i=0;i<result.size();i++){
			%>
				{
				"attractionID":"<%=result.get(i).getID()%>",
				"attractionScore":"<%=result.get(i).getScore()%>",
				"title":"<%=result.get(i).getTitle()%>",
				"addr":"<%=result.get(i).getAddr()%>",
				"mapX":"<%=result.get(i).getX()%>",
				"mapY":"<%=result.get(i).getY()%>",
				"Thema1":"<%=result.get(i).getThema1()%>",
				"Thema2":"<%=result.get(i).getThema2()%>",
				"Thema3":"<%=result.get(i).getThema3()%>",
				"Thema4":"<%=result.get(i).getThema4()%>",
				"Thema5":"<%=result.get(i).getThema5()%>",
				"Thema6":"<%=result.get(i).getThema6()%>",
				"Thema7":"<%=result.get(i).getThema7()%>"
				}<%	if(i!=result.size()-1){%>,<%
					}
				}%>
		]
}
