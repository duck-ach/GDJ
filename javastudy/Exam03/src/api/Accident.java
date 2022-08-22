package api;

public class Accident {

	private String occrrncDt; // 발생월일시 (2019011622)
	private String occrrncDayCd;  // 발생요일코드 (4)
	private int dthDnvCnt;  // 사망자수 (0)
	private int injpsnCnt;  // 부상자수 (1)
	
	public Accident(String occrrncDt, String occrrncDayCd, int dthDnvCnt, int injpsnCnt) {
		super();
		this.occrrncDt = occrrncDt;
		this.occrrncDayCd = occrrncDayCd;
		this.dthDnvCnt = dthDnvCnt;
		this.injpsnCnt = injpsnCnt;
	}
	
	public String weekN() {
		String weekNm = null;
		switch(occrrncDayCd) {
		case "1" : weekNm = "일요일"; break;
		case "2" : weekNm = "월요일"; break;
		case "3" : weekNm = "화요일"; break;
		case "4" : weekNm = "수요일"; break;
		case "5" : weekNm = "목요일"; break;
		case "6" : weekNm = "금요일"; break;
		default : weekNm = "토요일";
		}
		return weekNm;
	}
	
	public String result() {
		return "발생일자 " + occrrncDt + " " + weekN() + ", 사망자수 " + dthDnvCnt + "명, 부상자수 " + injpsnCnt + "명";
	}
	
}
