package attraction;

public class Attraction {
	private String attractionName;
	private int attractionID ;
	private String addr1;
	private int Thema;
	private float mapX;
	private float mapY;
	private float attractionScore;
	
	
	public String getAttractionName() {
		return attractionName;
	}
	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}
	public int getAttractionID() {
		return attractionID;
	}
	public void setAttractionID(int attractionID) {
		this.attractionID = attractionID;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public int getThema() {
		return Thema;
	}
	public void setThema(int thema) {
		Thema = thema;
	}
	public float getMapX() {
		return mapX;
	}
	public void setMapX(float mapX) {
		this.mapX = mapX;
	}
	public float getMapY() {
		return mapY;
	}
	public void setMapY(float mapY) {
		this.mapY = mapY;
	}
	public float getAttractionScore() {
		return attractionScore;
	}
	public void setAttractionScore(float attractionScore) {
		this.attractionScore = attractionScore;
	}

	
}
