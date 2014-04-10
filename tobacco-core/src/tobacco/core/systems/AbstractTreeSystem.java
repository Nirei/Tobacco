package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

/**
 * This kind of system recursively traverses the tree of entities
 * processing each.
 * @author nirei
 */
public abstract class AbstractTreeSystem implements EngineSystem {

	/**
	 * Array with the Components that should be present on an Entity
	 * for the system to process it
	 */
	private String[] requiredComponents;
	
	/**
	 * Current root entity
	 */
	private Entity rootEntity;

	/**
	 * Defines what this system does to Entities
	 * @param entity - Current Entity being processed
	 * @param data - Recursive data received when calling it from its parent 
	 * @return Data to pass on to its children
	 */
	public abstract Object process(Entity entity, Object data);
	
	/**
	 * Prepares the system for the current traversal of the entity tree.
	 * It's called when this system's work() beggins.
	 */
	public abstract void setUp();
	
	/**
	 * Finalize this system's work.
	 * Called at the current tree traversal's end.
	 */
	public abstract void tearDown();
	

	public AbstractTreeSystem(String[] _requiredComponents) {
		requiredComponents = _requiredComponents;
	}
	
	/**
	 * Determine if the entity qualifies for processing and 
	 * therefore should be processed by the system.
	 * @param entity - Entity to check
	 * @return <b>true</b> - if the Entity has the required Components<br />
	 * <b>false</b> - otherwise
	 */
	public boolean qualifies(Entity entity) {
		for(String type : requiredComponents) {
			if(!entity.contains(type)) return false;
		}
		return true;
	}

	private void processTree(Entity entity, Object data) {
		data = process(entity, data);
		
		if(entity.contains(Component.CONTAINER_C)) {
			ContainerComponent ccomp = (ContainerComponent) entity.getComponent(Component.CONTAINER_C);
			for(Entity e : ccomp) {
				processTree(e, data);
			}
		}
	}

	/**
	 * @return The current root entity
	 */
	public Entity getRootEntity() {
		return rootEntity;
	}
	
	@Override
	public void work(Entity root) {
		if(rootEntity != root) {
			rootEntity = root;
		}
		setUp();
		processTree(root, null);
		tearDown();
	}
}
