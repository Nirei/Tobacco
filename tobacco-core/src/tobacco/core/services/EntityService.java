package tobacco.core.services;

import java.util.Collection;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;

public interface EntityService {
	
	/**
	 * Returns the current root entity, creates one if there's none present
	 * @return the current root entity of the entity tree.
	 */
	public Entity getRoot();
	
	/**
	 * @return a list containing all the entities.
	 */
	public List<Entity> getEntityList();
	
	/**
	 * Creates a new entity without components.
	 * @return the created entity
	 */
	public Entity create();
	
	/**
	 * Creates a new entity containing the specified components.
	 * @return the created entity
	 */
	public Entity create(Collection<Component> components);
	
	/**
	 * Removes this entity from the entity list
	 * @param entity
	 */
	public void remove(Entity entity);
	
	/**
	 * Clears the entity tree
	 */
	public void clear();
	
}
