package ex04_has_a_inherit;

public class SoldierMain {

	public static void main(String[] args) {
		
		Soldier soldier = new Soldier();
		soldier.reload(10);
		soldier.shoot();
		soldier.shoot();
		soldier.reload(6);
		System.out.println(soldier.getBullet());

	}

}
