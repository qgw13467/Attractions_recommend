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
	private float themas1;
	private float themas2;
	private float themas3;
	private float themas4;
	private float themas5;
	private float themas6;
	private float themas7;
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
	public float getThemas1() {
		return themas1;
	}
	public void setThemas1(float themas1) {
		this.themas1 = themas1;
	}
	public float getThemas2() {
		return themas2;
	}
	public void setThemas2(float themas2) {
		this.themas2 = themas2;
	}
	public float getThemas3() {
		return themas3;
	}
	public void setThemas3(float themas3) {
		this.themas3 = themas3;
	}
	public float getThemas4() {
		return themas4;
	}
	public void setThemas4(float themas4) {
		this.themas4 = themas4;
	}
	public float getThemas5() {
		return themas5;
	}
	public void setThemas5(float themas5) {
		this.themas5 = themas5;
	}
	public float getThemas6() {
		return themas6;
	}
	public void setThemas6(float themas6) {
		this.themas6 = themas6;
	}
	public float getThemas7() {
		return themas7;
	}
	public void setThemas7(float themas7) {
		this.themas7 = themas7;
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
