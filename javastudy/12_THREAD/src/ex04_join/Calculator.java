package ex04_join;

public class Calculator implements Runnable {
	long total;
	long begin;
	long end;
	
	
	
	public Calculator(long begin, long end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	
	
	
	public void add() {
		for(long n = begin; n <= end; n++) {
			total += n;
		}
	}
	
	


	@Override
	public void run() {
		add();
	}



	



	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
	}



	public long getBegin() {
		return begin;
	}



	public void setBegin(long begin) {
		this.begin = begin;
	}



	public long getEnd() {
		return end;
	}



	public void setEnd(long end) {
		this.end = end;
	}
	
}
