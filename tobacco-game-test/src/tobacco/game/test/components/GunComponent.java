package tobacco.game.test.components;

public class GunComponent implements GameComponent {

	private boolean shooting = false;

	public GunComponent() {
	}

	@Override
	public String getComponentType() {
		return GUN_C;
	}

	public void setShooting(boolean _shooting) {
		shooting = _shooting;
	}

	public boolean isShooting() {
		return shooting;
	}

	@Override
	public String toString() {
		return "Gun: ";
	}
}
