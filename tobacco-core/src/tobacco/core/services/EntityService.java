/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
	 * Creates a new Entity as a children of root.
	 * @return created {@link Entity}.
	 */
	public Entity createInRoot();
	
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
