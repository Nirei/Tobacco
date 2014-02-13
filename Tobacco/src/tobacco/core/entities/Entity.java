package tobacco.core.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tobacco.core.components.Component;

/**
 * Entity for the game engine.
 * Contains components.
 * No logic.
 * @author nirei
 *
 */
public abstract class Entity implements Iterable<Component> {
	
	private static long counter = 0;
	private long id = 0;
	private static Map<Integer, Component> components = new HashMap<Integer, Component>();
	
	public Entity() {
		id = counter++;
	}

	/**
	 * Gets the map of components.
	 * @return Map of {@link Component} for this entity.
	 */
	public Component getComponent(int type) {
		return components.get(type);
	}
	
	/**
	 * Adds a {@link Component} to the map of components.
	 * @param _component Component to be added
	 */
	public void putComponent(Component _component) {
		components.put(_component.getComponentType(), _component);
	}
	
	/**
	 * Check if the entity contains a specific component.
	 * @param _component
	 */
	public boolean contains(int type) {
		return components.containsKey(type);
	}
	
	@Override
	public Iterator<Component> iterator() {
		return components.values().iterator();
	}

	/**
	 * Gets the ID for this {@link Entity}.
	 * @return Long representing Entity's ID.
	 */
	public final Long getID() {
		return id;
	}
}
