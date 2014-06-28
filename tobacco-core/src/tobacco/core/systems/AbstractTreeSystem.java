package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;

/**
 * This kind of system recursively traverses the tree of entities processing each and carrying recursive data if required.
 * 
 * @author nirei
 */
public abstract class AbstractTreeSystem extends AbstractSystem {

	public AbstractTreeSystem(String[] _requiredComponents) {
		super(_requiredComponents);
	}
	
	/**
	 * Defines what this system does to Entities
	 * 
	 * @param entity
	 *            - Current Entity being processed
	 * @param data
	 *            - Recursive data received when calling it from its parent
	 * @return Data to pass on to its children
	 */
	public abstract Object process(Entity entity, Object data);

	private void processTree(Entity entity, Object data) {
		data = process(entity, data);

		if (entity.contains(Component.CONTAINER_C)) {
			ContainerComponent ccomp = (ContainerComponent) entity.getComponent(Component.CONTAINER_C);
			for (Entity e : ccomp) {
				processTree(e, data);
			}
		}
	}

	@Override
	public void traverse() {
		processTree(getRootEntity(), null);
	}
}
