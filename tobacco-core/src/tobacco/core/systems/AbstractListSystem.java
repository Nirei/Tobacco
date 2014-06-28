package tobacco.core.systems;

import tobacco.core.components.Entity;

/**
 * This kind of system traverses the whole list of entities without a specific order
 * and without carrying data from parent to child
 * @author nirei
 */
public abstract class AbstractListSystem extends AbstractSystem {

	public AbstractListSystem(String[] _requiredComponents) {
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
	public abstract void process(Entity entity);

	@Override
	public void traverse() {
		for(Entity e : Entity.getEntityList()) {
			process(e);
		}
	}
}
