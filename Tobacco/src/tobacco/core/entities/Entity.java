package tobacco.core.entities;

import java.util.Map;

import tobacco.core.components.Component;

/**
 * Entity for the game engine.
 * Contains components.
 * No logic.
 * @author nirei
 *
 */
public abstract class Entity {
	
	private static long counter = 0;
	private static long id = 0;
	
	private Entity() {
		id = counter++;
	}

	/**
	 * Gets the map of components.
	 * @return Map of {@link Component} for this entity.
	 */
	abstract public Map<Short, Component> getComponents();
	
	/**
	 * Adds a {@link Component} to the map of components.
	 * @param _component Component to be added
	 */
	abstract public void addComponent(Component _component);

	/**
	 * Gets the ID for this {@link Entity}.
	 * @return Long representing Entity's ID.
	 */
	public final Long getID() {
		return id;
	}

}
