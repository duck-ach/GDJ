package ex02_is_a;

public class CarMain {

	public static void main(String[] args) {
		
		Hybrid hybrid = new Hybrid();
		
		System.out.println("자동차 : ");
		hybrid.Run();
		
		System.out.println();
		
		System.out.println("전기차 : ");
		hybrid.Run();
		hybrid.Evol();
		
		System.out.println();
		
		System.out.println("하이브리드 : ");
		hybrid.Run();
		hybrid.Evol();
		hybrid.Oil();
		
		

	}

}
