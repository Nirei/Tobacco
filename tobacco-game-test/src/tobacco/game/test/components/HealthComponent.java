package tobacco.game.test.components;

public class HealthComponent implements GameComponent {

	float health = 1f; // Don't auto-kill entities without defined health

	public HealthComponent() {
	}

	public HealthComponent(float _health) {
		health = _health;
	}

	@Override
	public String getComponentType() {
		return GameComponent.HEALTH_C;
	}

	public boolean isDead() {
		return health <= 0f;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

}
