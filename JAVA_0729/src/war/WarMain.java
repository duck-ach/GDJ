package war;

public class WarMain {

	public static void main(String[] args) {
		Soldier soldier = new Soldier(new Gun("K2"), "RAMBO");
		
		soldier.reload(2);
		soldier.reload(5);
		
		soldier.shoot();
		
		soldier.info();
		
		
		

	}

}
