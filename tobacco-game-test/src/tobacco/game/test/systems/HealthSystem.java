package tobacco.game.test.systems;

import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;
import tobacco.core.systems.AbstractTreeSystem;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.HealthComponent;

/**
 * Manages entities' deaths.
 * @author nirei
 *
 */
public class HealthSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = {GameComponent.HEALTH_C};

	public HealthSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if(qualifies(entity)) {
			HealthComponent healthComponent = (HealthComponent) entity.getComponent(GameComponent.HEALTH_C);
			if(healthComponent.isDead()) {
				entity.putComponent(new RemoveComponent());
			}
		}

		return null;
	}

	@Override public void setUp() {}
	@Override public void tearDown() {}

}
