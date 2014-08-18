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
package tobacco.core.components;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity for the game engine. Contains components. No logic.
 * 
 * @author nirei
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public final class Entity implements Iterable<Component> {
	
//	public static final String ENTITY_XML_NAME = "entity";

	// TODO: Object pool for Entities
	private static List<Entity> entityList = new LinkedList<Entity>();
	private static long counter = 0;

	private long id = 0;

	private Map<String, Component> components = new HashMap<String, Component>();

	public Entity() {
		id = counter++;
		entityList.add(this);
	}

	/**
	 * Gets the map of components.
	 * 
	 * @return Map of {@link Component} for this entity.
	 */
	public Component get(String type) {
		synchronized (components) {
			return components.get(type);
		}
	}

	/**
	 * Adds a {@link Component} to the map of components.
	 * 
	 * @param _component
	 *            Component to be added
	 */
	public void put(Component component) {
		components.put(component.getComponentType(), component);
	}

	/**
	 * Check if the entity contains a specific component.
	 * 
	 * @param _component
	 */
	public boolean has(String type) {
		return components.containsKey(type);
	}
	
	/**
	 * Deletes the Entity from the Entity directory.
	 */
	public void delete() {
		entityList.remove(this);
	}

	/**
	 * Gets the ID for this {@link Entity}.
	 * 
	 * @return Long representing Entity's ID.
	 */
	public final Long getID() {
		return id;
	}
	
	/**
	 * This is a marshalling method which returns each
	 * of this entity's components in a Collection
	 * @return A collection of this entity's components
	 */
	@XmlElementWrapper(name="components")
	@XmlAnyElement
	public Collection<Component> getComponentSet() {
		return components.values();
	}
	
	/**
	 * @return The full list of entities
	 */
	public static List<Entity> getEntityList() {
		return new LinkedList<Entity>(entityList);
	}

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
