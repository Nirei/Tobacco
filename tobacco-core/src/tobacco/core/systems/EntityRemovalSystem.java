package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;

public class EntityRemovalSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = { Component.REMOVE_C };

	public EntityRemovalSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if (qualifies(entity)) {
			if (data != null) {
				Entity parent = (Entity) data;
				ContainerComponent children = (ContainerComponent) parent.getComponent(Component.CONTAINER_C);
				children.delChildren(entity.getID());
			}
		}
		return entity;
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
