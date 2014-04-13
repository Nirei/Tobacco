package tobacco.game.test.entities;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;

public class BulletEntityFactory {

	public Entity create(Entity shooter) {
		Entity entity = new Entity();
		if(!shooter.contains(GameComponent.POSITION_C))	return null;		
		if(!shooter.contains(GameComponent.GUN_C)) return null;

		PositionComponent shooterPosition = (PositionComponent) shooter.getComponent(GameComponent.POSITION_C);
		Vector2D pos = shooterPosition.getPosition();
		GunComponent weaponComp = (GunComponent) shooter.getComponent(GameComponent.GUN_C);
		
		DrawableComponent drawComp = new DrawableComponent(weaponComp.getBulletTexture(), weaponComp.getBulletSize());
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent(pos));
		entity.putComponent(new MovementComponent(weaponComp.getBulletDirection(), weaponComp.getBulletSpeed()));
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(new DurationComponent(1000));

		return entity;
	}

}
