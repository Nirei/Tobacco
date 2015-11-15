package tobacco.core.systems;

import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;

/**
 * Typed systems ask the {@link EntityService} for every {@link Entity}
 * containing a {@link Component} an specified {@link Type} on every
 * tick. Then do their work only on the returned entities.
 * @author nirei
 */
public abstract class AbstractTypedSystem extends AbstractEntitySystem {
	
	private Type type;

	/**
	 * Constructs an AbstractTypedSystem that will check entities which have
	 * a type {@link Type} {@link Component} for requiredComponents and
	 * process them.
	 * @param requiredComponents - component types required by the system 
	 * to be present in an Entity to do its work
	 * @param type - component type that the entities processed by the
	 * system must have
	 */
	public AbstractTypedSystem(Type[] requiredComponents, Type type) {
		super(requiredComponents);
		this.type = type;
	}
	
	/**
	 * Defines what this system does to Entities
	 * 
	 * @param entity
	 *            - Current Entity being processed
	 */
	public abstract void process(Entity entity);

	@Override
	public void traverse() {
		for(Entity e : Directory.getEntityService().findAll(type)) {
			process(e);
		}
	}

}
