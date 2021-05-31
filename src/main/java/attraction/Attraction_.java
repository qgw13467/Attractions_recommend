package attraction;

public class Attraction_ {
	private String id;
	private float[] themas = new float[7];
	private String title;
	private String addr = "";
	private String x;
	private String y;
	private String firstimg = "";
	private String firstimg2 = "";
	private String overview = "";

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setThema(String cat) {
		this.themas = classifyThema(cat);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAddr(String addr) {
		this.addr = addr;
		if (addr == null) {
			this.addr = "";
		}
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

	public void setFirstImg(String img) {
		this.firstimg = img;
		if (img == null) {
			this.firstimg = "";
		}
	}

	public void setFirstImg2(String img) {
		this.firstimg2 = img;
		if (img == null) {
			this.firstimg2 = "";
		}
	}

	public void setOverview(String overview) {
		this.overview = overview;
		if (overview == null) {
			this.overview = "";
		}
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

	public String getSQLValue() {
		String value = "(";
		value += "\"" + id + "\",";
		value += "\"" + title + "\",";
		value += "\"" + addr + "\",";
		value += x + ",";
		value += y + ",";
		value += "\"" + firstimg + "\",";
		value += "\"" + firstimg2 + "\",";
		value += "\"" + overview + "\",";
		for (int i = 0; i < 7; i++) {
			value += Float.toString(themas[i]) + ",";
		}
		value += "0.0" + ")";

		return value;
	}

	public static String getSQLColumns() {
		return "(attractionID,title,addr,mapx,mapy,firstImage,firstImage2,overview,thema1,thema2,thema3,thema4,thema5,thema6,thema7,attractionScore)";
	}
}
