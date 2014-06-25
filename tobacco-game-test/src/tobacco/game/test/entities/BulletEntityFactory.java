package tobacco.game.test.entities;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;

public class BulletEntityFactory {

	public Entity create(Vector2D pos, BulletComponent b) {
		Entity entity = new Entity();
		DrawableComponent drawComp = new DrawableComponent(b.getBulletData().getTexture(), b.getBulletData().getSize());
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent(pos));
		Vector2D dir = b.getBulletDirection();
		entity.putComponent(new MovementComponent(dir, b.getBulletSpeed()));
		entity.putComponent(new RotationComponent(90f + Vector2D.angle(Vector2D.VERTICAL, dir).getDegrees()));
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(new DurationComponent(1000));

		return entity;
	}

}
