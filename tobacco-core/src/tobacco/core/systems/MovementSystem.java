package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;

public class MovementSystem implements EngineSystem {
	
	private long lastCall = System.currentTimeMillis();
	
	private void processTree(Entity entity, long delta) {
		float speed;
		Vector2D direction, position;
		
		if(entity.contains(Component.POSITION_C)) {
			position = ((PositionComponent) entity.getComponent(Component.POSITION_C)).getPosition();

			if(entity.contains(Component.MOVEMENT_C)) {
				MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
				direction = movComp.getDirection();
				speed = movComp.getSpeed();
				
				// Calculate new position
				Vector2D.sum(
					position,
					Vector2D.scale(
						Vector2D.scale(
							Vector2D.normalize(direction),
							speed
						),
						delta/1000f
					)
				);
			}
		}
	}

	@Override
	public void work(Entity entity) {
		
		long now = System.currentTimeMillis();
		long delta = now - lastCall;
		lastCall = now;
		processTree(entity, delta);
	}

}
