package tobacco.core.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Entity for the game engine.
 * Contains components.
 * No logic.
 * @author nirei
 *
 */
public final class Entity implements Iterable<Component> {
	
	private static long counter = 0;
	private long id = 0;
	private  Map<String, Component> components = new HashMap<String, Component>();
	
	public Entity() {
		id = counter++;
	}

	/**
	 * Gets the map of components.
	 * @return Map of {@link Component} for this entity.
	 */
	public Component getComponent(String type) {
		synchronized(components) {
			return components.get(type);
		}
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
	public boolean contains(String type) {
		return components.containsKey(type);
	}
	
	@Override
	public Iterator<Component> iterator() {
		return new ArrayList<Component>(components.values()).iterator();
	}

	/**
	 * Gets the ID for this {@link Entity}.
	 * @return Long representing Entity's ID.
	 */
	public final Long getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Entity@" + Long.toString(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}
}