package quiz04_cart;



public class Customer {
	// field
	private int money;
	private double bonusPoint;
	int total;
	private Product[] cart = new Product[10]; // cart안에 담을 물건 배열 선언
	private int idx; // 카트에 몇번째 담긴 물건인지 알아야할 때 쓰임. (카트에 담긴 물건 수)
	
	
	
	// constructor
	public Customer() { // 생성자 생략. new Customer()가능
		
	}
	
	
	// method
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public double getBonusPoint() {
		return bonusPoint;
	}


	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	
	
	// 구매 메소드
	public void buy(Product product) { // UpCasting사용
		int price = product.getPrice(); // Refactoring (성능상 이점)
		// 가진 돈보다 비싼 물건은 사지 못한다.
		if(money < price) { // product.getPrice = 상품의 가격
			System.out.println(product.getName() + "를 사려면 돈이" + (price - money) + "원 부족합니다.");
			return;
		}
		// 카트가 가득 차면 물건을 못 산다.
		if(idx == cart.length) { // idx가 cart의 길이가 되면(max) 물건 못삼
			System.out.println("카트가 가득 찼습니다.");
			return;
		}
		// 구매
		cart[idx++] = product; // 상품을 카트에 넣고 인덱스의 숫자가 오른다(후위연산자)
		total += price;
		money -= price;
		bonusPoint += price * 0.1; // 포인트는 구매가격의 10퍼센트 늘어난다.
	}
	
	public void receipt() {
		// 물건을 안 샀다.
		if(idx == 0) {
			System.out.println("구매한 물건이 없습니다.");
			return;
		}
		// 구매 총액 구하기
		
		for(int i = 0; i < idx; i++) { // 인덱스 까지 for문 돌리기 NullPointExeption 회피하는 방법
			Product product = cart[i];
			System.out.println(product.getName() + "  " + product.getPrice() + "원");
			
		}
	
		System.out.println("-------------------------");
		System.out.println("구매총액 : " + total + "원");
		System.out.println("보너스   : " + bonusPoint + "원");
		System.out.println("거스름돈 : " + money + "원"); 
		// 구매할때마다 위에서 money값이 줄어들고 있기 때문에 money라고 해도된다.
		
		
	}

	
	


}

