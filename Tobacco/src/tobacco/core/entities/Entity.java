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
public interface Entity {

	/**
	 * Gets the map of components.
	 * @return Map of {@link Component} for this entity.
	 */
	public Map<Short, Component> getComponents();
	
	/**
	 * Adds a {@link Component} to the map of components.
	 * @param _component Component to be added
	 */
	public void addComponent(Component _component);

}
