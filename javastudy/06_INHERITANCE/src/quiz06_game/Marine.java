package quiz06_game;

public class Marine extends GameUnit {

	public Marine (String name) {
		super(name, 50, 5);
	}
	
	@Override
	public void attack(GameUnit unit) {
		if (Math.random() < 0.4) { 
			unit.setEnergy(0);
			unit.setAlive(false);
			System.out.println(unit.getName() + "를 한 방에 죽였다.");
			return;
		} else {
			int unitEnergy = unit.getEnergy() - getPower() < 0 ? 0 : unit.getEnergy() - getPower();
			unit.setEnergy(unitEnergy);
			unit.setAlive(unitEnergy > 0);
			System.out.println(unit.getName() + "의 남은 에너지 " + unit.getEnergy());
		}
	}
	
}
