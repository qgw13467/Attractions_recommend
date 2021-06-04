package attraction;

public class Attraction_{
	private String id;
	private float score;
	private String title;
	private String addr = "";
	private String x;
	private String y;
	private String firstimg = "";
	private String firstimg2 = "";
	private String overview = "";
	private float thema1;
	private float thema2;
	private float thema3;
	private float thema4;
	private float thema5;
	private float thema6;
	private float thema7;
	private float[] themas = new float[7];

	public void setID(String id) {
		this.id = id;
	}
	public String getID() {
		return id;
	}
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	public void setThema(String cat) {
		this.themas = classifyThema(cat);
	}
	
	public float getThema(int i) {
		return themas[i];
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setAddr(String addr) {
		this.addr = addr;
		if (addr == null) {
			this.addr = "";
		}
	}
	
	public String getAddr() {
		return addr;
	}

	public void setX(String x) {
		this.x = x;
	}
	
	public String getX() {
		return x;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getY() {
		return y;
	}
	
	public void setFirstImg(String img) {
		this.firstimg = img;
		if (img == null) {
			this.firstimg = "";
		}
	}
	
	public String getFirstImg() {
		return firstimg;
	}

	public void setFirstImg2(String img) {
		this.firstimg2 = img;
		if (img == null) {
			this.firstimg2 = "";
		}
	}
	
	public String getFirstImg2() {
		return firstimg2;
	}

	public void setOverview(String overview) {
		this.overview = overview;
		if (overview == null) {
			this.overview = "";
		}
	}
	
	public String getOverview() {
		return overview;
	}
	public float getThema1() {
		return thema1;
	}
	public void setThema1(float thema1) {
		this.thema1 = thema1;
	}
	public float getThema2() {
		return thema2;
	}
	public void setThema2(float thema2) {
		this.thema2 = thema2;
	}
	public float getThema3() {
		return thema3;
	}
	public void setThema3(float thema3) {
		this.thema3 = thema3;
	}
	public float getThema4() {
		return thema4;
	}
	public void setThema4(float thema4) {
		this.thema4 = thema4;
	}
	public float getThema5() {
		return thema5;
	}
	public void setThema5(float thema5) {
		this.thema5 = thema5;
	}
	public float getThema6() {
		return thema6;
	}
	public void setThema6(float thema6) {
		this.thema6 = thema6;
	}
	public float getThema7() {
		return thema7;
	}
	public void setThema7(float thema7) {
		this.thema7 = thema7;
	}
	
	
	// 자연 0, 역사 1, 휴향 2, 문화시설 3, 체험 4, 레저 5, 쇼핑 6
	float[] classifyThema(String contenttypeid) {

		String cat1, cat2;
		cat1 = contenttypeid.substring(0, 3);
		cat2 = contenttypeid.substring(0, 5);
		float[] themas = new float[7];

		if (cat1.equals("A01")) {
			themas[0] = 1;
		} else if (cat1.equals("A02")) {
			if (cat2.equals("A0201") || cat2.equals("A0204")) {
				themas[1] = 1;
			} else if (cat2.equals("A0202") || cat2.equals("A0205")) {
				themas[2] = 1;
			} else if (cat2.equals("A0206")) {
				themas[3] = 1;
			} else {
				themas[4] = 1;
			}
		} else if (cat1.equals("A03")) {
			themas[5] = 1;
		} else {
			themas[6] = 1;
		}

		return themas;
	}

	String splitName(String addr1) {
		String[] temp;
		temp = addr1.split(" ");
		return temp[0] + " " + temp[1];
	}

	


}
