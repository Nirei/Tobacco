package tobacco.game.test.entities;

import tobacco.core.components.CommandBufferComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.util.Vector2D;

public class EnemyEntityFactory implements EntityFactory {

	@Override
	public Entity create() {
		Entity entity = new Entity();
		
		DrawableComponent drawComp = new DrawableComponent();
		drawComp.setSize(new Vector2D(50, 50));
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent());
		entity.putComponent(new MovementComponent());
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(new CommandBufferComponent());

		return entity;
	}
	
}
