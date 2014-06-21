package tobacco.game.test.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.systems.AbstractTreeSystem;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.entities.BulletEntityFactory;

public class GunSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = {GameComponent.GUN_C, Component.CONTAINER_C, Component.POSITION_C};
	private BulletEntityFactory bulletEntityFactory;

	public GunSystem() {
		super(requiredComponents);
		bulletEntityFactory = new BulletEntityFactory();
	}

	@Override
	public Object process(Entity entity, Object data) {
		if(qualifies(entity)) {
			PositionComponent posComp = (PositionComponent) entity.getComponent(Component.POSITION_C);
			ContainerComponent children = (ContainerComponent) entity.getComponent(Component.CONTAINER_C);
			GunComponent gunComp = (GunComponent) entity.getComponent(GameComponent.GUN_C);
			
			if(gunComp.isShooting()) {
				for(Entity e : children) {
					if(e.contains(GameComponent.BULLET_C)) {
						BulletComponent bulletComp = (BulletComponent) e.getComponent(GameComponent.BULLET_C);
						long time = System.currentTimeMillis();
						long delta = time - bulletComp.getLastBullet();
						if(bulletComp.getBulletPeriod() <= delta) {
							bulletComp.setLastBullet(time);
							Entity bullet = bulletEntityFactory.create(posComp.getPosition(), bulletComp);
							((ContainerComponent) getRootEntity().getComponent(Component.CONTAINER_C)).addChild(bullet);
						}
					}
				}
			}
		}
		return null;
	}

	@Override public void setUp() {}

	@Override public void tearDown() {}
}
