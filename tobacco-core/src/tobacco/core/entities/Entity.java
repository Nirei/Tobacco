/*
* 	Tobacco - A portable and reusable game engine written in Java.
*	Copyright © 2014 Nirei
*
*	This file is part of Tobacco
*
*   Tobacco is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package tobacco.core.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import tobacco.core.components.Component;
import tobacco.core.components.Type;

/**
 * Entity for the game engine. Contains components. No logic.
 * @author nirei
 */
public final class Entity implements Iterable<Component> {

	// TODO: Object pool for Entities
//	private static List<Entity> entityList = new LinkedList<Entity>();
	private static long counter = 0;

	private long id = 0;

	private Map<Type, Component> components = new HashMap<Type, Component>();

	Entity() {
		id = counter++;
//		synchronized(entityList) {
//			entityList.add(this);
//		}
	}

	/**
	 * Get a component from this entity.
	 * @param type The type of the component
	 * @return {@link Component} of the specified type.
	 */
	public Component get(Type type) {
		synchronized (components) {
			return components.get(type);
		}
	}

	/**
	 * Adds a {@link Component} to the map of components.
	 * 
	 * @param component
	 *            Component to be added
	 */
	public void add(Component component) {
		components.put(component.getComponentType(), component);
	}
	
	/**
	 * Removes a {@link Component} to the map of components.
	 * @param type - The {@link Type} of the {@link Component} to be removed from the {@link Entity}
	 */
	public void remove(Type type) {
		components.remove(type);
	}

	/**
	 * Check if the entity contains a specific component.
	 * 
	 * @param type - {@link Type} of the {@link Component}.
	 */
	public boolean has(Type type) {
		return components.containsKey(type);
	}
	
//	/**
//	 * Deletes the Entity from the Entity directory.
//	 */
//	public void delete() {
//		synchronized(entityList) {
//			entityList.remove(this);
//		}
//	}

	/**
	 * Gets the ID for this {@link Entity}.
	 * 
	 * @return Long representing Entity's ID.
	 */
	public final Long getID() {
		return id;
	}
	
//	/**
//	 * @return The full list of entities
//	 */
//	public static List<Entity> getEntityList() {
//		synchronized(entityList) {
//			return new LinkedList<Entity>(entityList);
//		}
//	}

	@Override
	public Iterator<Component> iterator() {
		return new LinkedList<Component>(components.values()).iterator();
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
