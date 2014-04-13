package tobacco.game.test.entities;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.util.Vector2D;

public class EnemyEntityFactory {

	public Entity create() {
		Entity entity = new Entity();
		
		DrawableComponent drawComp = new DrawableComponent();
		drawComp.setSize(new Vector2D(50, 50));
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent());
		entity.putComponent(new MovementComponent());
		entity.putComponent(new DebuggingComponent());

		return entity;
	}
	
}
