package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.util.Vector2D;

/**
 * This system resets the movement of the player entity each tick after it has been applied.
 * 
 * @author nirei
 * 
 */
// TODO: Design "AbstractListenerSystem" for Systems that only work with a very specific Entity?
public class MovementResetSystem extends AbstractListSystem {

	private final static String[] requiredComponents = {
		Component.PLAYER_C,
		Component.MOVEMENT_C };

	public MovementResetSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			MovementComponent movComp = (MovementComponent) entity.getComponent(Component.MOVEMENT_C);
			movComp.setDirection(Vector2D.ZERO);
		}
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
