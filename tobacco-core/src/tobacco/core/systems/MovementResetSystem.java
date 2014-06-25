package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.util.Vector2D;

/**
 * This system resets the movement of the player entity each tick after it has
 * been applied.
 * 
 * @author nirei
 * 
 */
public class MovementResetSystem extends AbstractTreeSystem {

	private final static String[] requiredComponents = { Component.PLAYER_C,
			Component.MOVEMENT_C };

	public MovementResetSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if (qualifies(entity)) {
			MovementComponent movComp = (MovementComponent) entity
					.getComponent(Component.MOVEMENT_C);
			movComp.setDirection(Vector2D.ZERO);
		}
		return null;
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
