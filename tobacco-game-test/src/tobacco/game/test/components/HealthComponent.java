package tobacco.game.test.components;

public class HealthComponent implements GameComponent {
	
	float health = 0;

	public HealthComponent() {}

	public HealthComponent(float _health) {
		health = _health;
	}
	
	@Override
	public String getComponentType() {
		return GameComponent.HEALTH_C;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

}
