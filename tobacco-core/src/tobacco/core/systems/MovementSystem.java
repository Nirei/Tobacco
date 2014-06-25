package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.util.Vector2D;

public class MovementSystem extends AbstractTreeSystem {

	private final static String[] requiredComponents = {
		Component.POSITION_C,
		Component.MOVEMENT_C };
	private long lastCall = System.currentTimeMillis();
	private long delta = 0;

	public MovementSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if (qualifies(entity)) {
			PositionComponent posComp;

			posComp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
			Vector2D position = posComp.getPosition();

			MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
			Vector2D direction = movComp.getDirection();

			// Calculate new position
			if (!direction.isZero()) {
				float speed = movComp.getSpeed();

				Vector2D newPos = Vector2D.sum(position, Vector2D.scale(Vector2D.scale(direction.normalize(), speed), delta / 1000f));
				posComp.setPosition(newPos);
			}
		}

		return null;
	}

	@Override
	public void setUp() {
		long now = System.currentTimeMillis();
		delta = now - lastCall;
		lastCall = now;
	}

	@Override
	public void tearDown() {
	}

}
