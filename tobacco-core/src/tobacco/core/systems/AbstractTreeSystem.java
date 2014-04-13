package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;
/**
 * This kind of system recursively traverses the tree of entities
 * processing each and carrying recursive data if required.
 * @author nirei
 */
public abstract class AbstractTreeSystem extends AbstractSystem {

	public AbstractTreeSystem(String[] _requiredComponents) {
		super(_requiredComponents);
	}
	
	/**
	 * Determine if the entity qualifies for processing and 
	 * therefore should be processed by the system.
	 * @param entity - Entity to check
	 * @return <b>true</b> - if the Entity has the required Components<br />
	 * <b>false</b> - otherwise
	 */
	public boolean qualifies(Entity entity) {
		for(String type : getRequiredComponents()) {
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
	
	@Override
	public void traverse() {
		processTree(getRootEntity(), null);
	}
	
	@Override
	public void work(Entity root) {
		if(getRootEntity() != root) {
			setRootEntity(root);
		}
		setUp();
		processTree(root, null);
		tearDown();
	}
}
