package ex06_constructor;

public class Alba extends Student{

	private String company;
	
	public Alba(String name, String school, String company) {
		super(name, school);
		this.company = company; // super 랑 sub의 위치가 바뀌면 error가 난다.
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}
