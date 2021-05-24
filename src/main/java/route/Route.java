package route;

public class Route {
	private int routeID;
	private String userID;
	private String routeDate;
	private String routeTitle;
	private String routeList;
	private int stateInt;
	private String Thema;
	private String arriveTime;
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRouteDate() {
		return routeDate;
	}
	public void setRouteDate(String routeDate) {
		this.routeDate = routeDate;
	}
	public String getRouteTitle() {
		return routeTitle;
	}
	public void setRouteTitle(String routeTitle) {
		this.routeTitle = routeTitle;
	}
	public String getRouteList() {
		return routeList;
	}
	public void setRouteList(String routeList) {
		this.routeList = routeList;
	}
	public int getStateInt() {
		return stateInt;
	}
	public void setStateInt(int stateInt) {
		this.stateInt=stateInt;
	}
	public String getThema() {
		return Thema;
	}
	public void setThema(String thema) {
		Thema = thema;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	
}
