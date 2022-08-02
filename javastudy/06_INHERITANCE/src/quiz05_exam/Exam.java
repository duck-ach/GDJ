package quiz05_exam;

public class Exam {
	
	private String examName;
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	private double avg;
	
	public Exam(String examName) {
		super();
		this.examName = examName;
	}
	

	public int getKor() {
		return kor;
	}
	public void setKor() {
		this.kor = (int) (Math.random() * 100 )+ 1;
	}
	public int getEng() {
		return eng;
	}
	public void setEng() {
		this.eng = (int) (Math.random() * 100 )+ 1;
	}
	
	public int getMat() {
		return mat;
	}
	public void setMat() {
		this.mat = (int) (Math.random() * 100 )+ 1;
	}
	
	public String getExamName() {
		return examName;
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum() {
		this.sum = getKor() + getEng() + getMat();
	}

	
	public double getAvg() {
		return avg;
	}

	public void setAvg() {
		this.avg = (double)sum / 3;
	}

	public void getName() { }
	
	public void examInfo() {
		System.out.println(examName + " 성적");
		System.out.println("국어 : " + getKor() + "점, 영어 : " + getEng() + "점, 수학 : " + getMat() + "점, 총점 : " + getSum() + "점, 평균 : " + getAvg() + "점");
	}

}
