package tobacco.core.systems;

import tobacco.core.components.Entity;

public abstract class AbstractSystem implements EngineSystem {

	public AbstractSystem(String[] _requiredComponents) {
		requiredComponents = _requiredComponents;
	}

	/**
	 * Array with the Components that should be present on an Entity for the system to process it
	 */
	private String[] requiredComponents;

	/**
	 * Current root entity
	 */
	private Entity rootEntity;

	/**
	 * Prepares the system for the current tick. It's called when this system's work() beggins.
	 */
	public abstract void setUp();

	// This calls for Strategy desing pattern but it can wait
	// since I don't think we'll need so much extensibility.

	/**
	 * Calls process on every entity that requires it.
	 */
	public abstract void traverse();

	/**
	 * Finalize this system's work.
	 */
	public abstract void tearDown();

	@Override
	public void work(Entity root) {
		if (getRootEntity() != root) {
			setRootEntity(root);
		}
		setUp();
		traverse();
		tearDown();
	}

	/**
	 * @return The current root Entity
	 */
	public Entity getRootEntity() {
		return rootEntity;
	}

	public void setRootEntity(Entity _rootEntity) {
		rootEntity = _rootEntity;
	}

	/**
	 * @return Returns an array of String with the names of the components this system needs on an entity to be able to process.
	 */
	public String[] getRequiredComponents() {
		return requiredComponents.clone();
	}
	
	/**
	 * Determine if the entity qualifies for processing and therefore should be processed by the system.
	 * 
	 * @param entity
	 *            - Entity to check
	 * @return <b>true</b> - If the Entity has the required Components<br />
	 *         <b>false</b> - Otherwise
	 */
	public boolean qualifies(Entity entity) {
		for (String type : getRequiredComponents()) {
			if (!entity.contains(type))
				return false;
		}
		return true;
	}

}
