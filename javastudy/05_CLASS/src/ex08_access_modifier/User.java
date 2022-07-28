package ex08_access_modifier;

public class User {
	
	// 필드는 private이다.
	private String id;
	private String password;
	private String email;
	private int point;
	private boolean isVip;
	
	// 메소드는 public이다.
	public String getId() {
		return id;
	}
	
	public void setId(String pId) {
		id = pId;
	}
	
	public String getPW() {
		return password;
	}
	
	public void setPW(String pPW) {
		password = pPW;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String pEmail) {
		email = pEmail;
	}
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int pPoint) {
		point = pPoint;
		setVip(point >= 10000);
	}
	
	public boolean getIsVip() {
		return isVip;
	}
	
	public void setVip(boolean pIsVip) {
		isVip = pIsVip;
	}
	
	
}
