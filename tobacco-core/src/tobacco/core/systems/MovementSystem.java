package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.util.Vector2D;

public class MovementSystem extends AbstractListSystem {

	private final static String[] requiredComponents = {
		Component.POSITION_C,
		Component.MOVEMENT_C };
	private long lastCall = System.currentTimeMillis();
	private long delta = 0;

	public MovementSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			PositionComponent posComp;

			posComp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
			Vector2D position = posComp.getPosition();

			MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
			Vector2D direction = movComp.getDirection();

			// Calculate new position
			if (!direction.isZero()) {
				float speed = movComp.getSpeed();

				Vector2D newPos = Vector2D.sum(position, direction.normalize().scale(speed * (delta / 1000f)));
				posComp.setPosition(newPos);
			}
		}
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
