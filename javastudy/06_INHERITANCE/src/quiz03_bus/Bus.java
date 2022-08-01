package quiz03_bus;

public class Bus {
	
	private Seat[] seats; // 배열 선언 (배열 선언한다고 못씀)
	private int limit; // 버스 정원
	
	// Bus 생성자에서 배열 선언을 진행한다.
	
	public Bus(int cnt) {
		seats = new Seat[cnt]; // 배열 생성, new Bus(25)인 경우 Seat가 25개 생김.
		limit = cnt;
		for(int i = 0; i < cnt; i++) {
			seats[i] = new Seat();
		}
	}
	
	// ride 메소드
	public void ride(int seatNo, Person person) { // new Person, new Student, new Alba를 모두 저장할 수 있다.
		// 존재하지 않는 시트번호
		if(seatNo < 0 || seatNo > limit) {
			return; // ride() 메소드 종료
		}
		
		// 시트번호에 Person 저장하기
		Seat seat = seats[seatNo - 1];
		Person p = seat.getPerson();
		if(p == null) { // 이미 앉은 시트에 중복값 막는 코드
			seat.setPerson(person);
		}
		// 1~25를 저장하고 있기 때문에 0~24로 바꾸기위해 -1 해준다.
		
	}
	
	public void info() {
		for(int i = 0; i < limit; i++) { // limit은 seats 배열의 length와 같음.
			Seat seat = seats[i];
			Person person = seat.getPerson();
			if(person != null) { // if(seat.getPerson() !=
				// Seat seat = seats[i]; 없애고 if(seats[i] != null 하면 됨
				System.out.println((i + 1) + ", " + person.getName());// Person person = seat.getPerson(); 없애고
				//System.out,println((i + 1) + "," + person.getName()));
				// system.out.println(i + 1) + ", " + seats[i].getPerson().getName()
			} else {
				System.out.println((i + 1) + ", 비어 있음");
			}
			
		}
	}
	
	
}
