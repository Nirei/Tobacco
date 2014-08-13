package tobacco.game.test.components;

// TODO: Clear all default values from Components, they aren't needed and could bring about errors.
public class DamageComponent implements GameComponent {
	
	private float damage;

	public DamageComponent(float damage) {
		this.damage = damage;
	}

	@Override
	public String getComponentType() {
		return GameComponent.DAMAGE_C;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

}
