package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;

public class MovementSystem implements EngineSystem {
	
	private long lastCall = System.currentTimeMillis();
	
	
	private void processTree(Entity entity, long delta) {
		float speed;
		PositionComponent posComp;
		Vector2D direction, position;
		
		if(entity.contains(Component.POSITION_C)) 
		{
			posComp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
			position = posComp.getPosition();

			if(entity.contains(Component.MOVEMENT_C)) 
			{
				MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
				direction = movComp.getDirection();
				speed = movComp.getSpeed();
				
				// Calculate new position
				if(!direction.isZero()) {
					Vector2D newPos = Vector2D.sum(
						position,
						Vector2D.scale(
							Vector2D.scale(
								Vector2D.normalize(direction),
								speed
							),
							delta/1000f
						)
					);
					posComp.setPosition(newPos);
				}
			}
		
		}
		
		if(entity.contains(Component.CONTAINER_C)) {
			for(Entity e : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				processTree(e, delta);
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
