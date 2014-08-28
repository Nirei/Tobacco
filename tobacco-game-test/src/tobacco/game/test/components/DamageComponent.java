package tobacco.game.test.components;

import tobacco.core.components.Type;

public class DamageComponent implements GameComponent {
	
	private float damage;
	
	public DamageComponent() {}

	public DamageComponent(float damage) {
		this.damage = damage;
	}

	@Override
	public Type getComponentType() {
		return GameComponent.DAMAGE_C;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

}
