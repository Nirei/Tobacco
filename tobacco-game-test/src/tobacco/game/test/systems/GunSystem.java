package tobacco.game.test.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;
import tobacco.core.systems.AbstractTreeSystem;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.entities.BulletEntityFactory;

public class GunSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = {GameComponent.GUN_C};
	private BulletEntityFactory bulletEntityFactory;

	public GunSystem() {
		super(requiredComponents);
		bulletEntityFactory = new BulletEntityFactory();
	}

	@Override
	public Object process(Entity entity, Object data) {
		if(qualifies(entity)) {
			GunComponent gunComp = (GunComponent) entity.getComponent(GameComponent.GUN_C);
			if(gunComp.isShooting()) {
				Entity bullet = bulletEntityFactory.create(entity);
				((ContainerComponent) getRootEntity().getComponent(Component.CONTAINER_C)).addChild(bullet);
				gunComp.setShooting(false);
			}
		}
		return null;
	}

	@Override public void setUp() {}
	@Override public void tearDown() {}
}
