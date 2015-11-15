package tobacco.game.test.components;

import tobacco.core.components.Type;

public class EnemyComponent implements GameComponent {

	@Override
	public Type getComponentType() {
		return GameComponent.ENEMY_C;
	}

}
