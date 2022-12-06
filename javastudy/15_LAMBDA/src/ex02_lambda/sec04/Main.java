package ex02_lambda.sec04;

public class Main {

	public static void main(String[] args) {
		
		/*
			3가지 방법 중 이 방법이 가장 효율적. (임시객체를 생성하여 자동차가 가면 메모리관리도 편함)
		 */
		Soil soil = new Soil();
		
		// 어떤자동차가 오는지 임시객체로 저장(한번 쓰고 사라짐)
		// 1번째 자동차
		soil.sellOil(() -> {
				int oil = 30;
				soil.setTotalOil(soil.getTotalOil() - oil);
				soil.setEarning(soil.getEarning() + oil * soil.getPayPerLiter());
				System.out.println("감사합니다 BMW");
		});
		
		// 2번째 자동차
		soil.sellOil(new Car() {
			@Override
			public void addOil() {
				int oil = 50;
				soil.setTotalOil(soil.getTotalOil() - oil);
				soil.setEarning(soil.getEarning() + oil * soil.getPayPerLiter());
				System.out.println("감사합니다 소나타");
			}
		});
		
		/*
			List<String> list = Arrays.asList("a", "b", "c");
			Stream<String> stream = list.forEach((a) -> {System.out.println(a)});
		*/
		System.out.println(soil.getTotalOil());
		System.out.println(soil.getEarning());

	}

}
