package tobacco.core.services;

import java.util.List;

import tobacco.core.components.Type;
import tobacco.core.entities.Entity;

/**
 * Interface for entity service providers. An instance of an
 * EntityService represents a game world with its own entity tree.
 * @author nirei
 *
 */
public interface EntityService {
	
	/**
	 * Returns the current root {@link Entity}
	 * @return the current root {@link Entity} of the {@link Entity} tree.
	 */
	public Entity getRoot();
	
	/**
	 * Set the current root {@link Entity} of the entity tree.
	 * @param root
	 */
	public void setRoot(Entity root);

	/**
	 * Returns a list of all entities.
	 * @return a list containing all the entities.
	 */
	public List<Entity> getEntityList();
	
	/**
	 * Returns an {@link Entity} given its id.
	 * @param id - id of the {@link Entity} to retrieve
	 * @return {@link Entity} that has the given id or null if there isn't one
	 */
	public Entity findEntityById(long id);
	
	/**
	 * Creates a new entity without components.
	 * @return created {@link Entity}.
	 */
	public Entity create();
	
	/**
	 * Removes this entity from the entity list.
	 * @param entity - {@link Entity} to remove
	 */
	public void remove(Entity entity);
	
	/**
	 * Clears the entity tree
	 */
	public void clear();

	/**
	 * Finds all entities which hold a component of the specified Type.
	 * @param type - {@link Type} of the entities to find
	 * @return a list containing every entity with such component.
	 */
	public List<Entity> findAll(Type type);
	
}
