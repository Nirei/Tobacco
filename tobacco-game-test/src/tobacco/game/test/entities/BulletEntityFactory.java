package tobacco.game.test.entities;

import tobacco.core.components.CommandBufferComponent;
import tobacco.core.components.Component;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.WeaponComponent;

public class BulletEntityFactory implements EntityFactory {
	
	Entity shooter;
	
	public BulletEntityFactory(Entity _shooter) {
		shooter = _shooter;
	}

	@Override
	public Entity create() {
		Entity entity = new Entity();
		PositionComponent shooterPosition = (PositionComponent) shooter.getComponent(Component.POSITION_C);
		Vector2D pos = shooterPosition.getPosition();
		WeaponComponent weaponComp = (WeaponComponent) shooter.getComponent(GameComponent.WEAPON_C);
		
		DrawableComponent drawComp = new DrawableComponent();
		drawComp.setSize(weaponComp.getBulletSize());
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent(pos));
		entity.putComponent(new MovementComponent(weaponComp.getBulletDirection(), weaponComp.getBulletSpeed()));
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(new CommandBufferComponent());

		return entity;
	}

}
