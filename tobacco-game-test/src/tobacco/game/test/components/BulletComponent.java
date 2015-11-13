package tobacco.game.test.components;

import tobacco.core.components.Type;

public class BulletComponent implements GameComponent {

	public BulletComponent() {}
	
	@Override
	public String toString() {
		return "Bullet";
	}

	@Override
	public Type getComponentType() {
		return GameComponent.BULLET_C;
	}
}
