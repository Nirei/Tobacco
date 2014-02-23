package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

public class EntityRemovalSystem implements EngineSystem {
	
	private void processTree(Entity current, Entity parent) {
		ContainerComponent children;
		if(current.contains(Component.REMOVE_C)) {
			if(parent != null) {
				children = (ContainerComponent) parent.getComponent(Component.CONTAINER_C);
				children.delChildren(current.getID());
			}
		} else if(current.contains(Component.CONTAINER_C)){
			for(Entity e : (ContainerComponent) current.getComponent(Component.CONTAINER_C)) {
				processTree(e, current);
			}
		}
	}

	@Override
	public void work(Entity root) {
		processTree(root, null);
	}

}
