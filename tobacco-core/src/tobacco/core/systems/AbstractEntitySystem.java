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
package tobacco.core.systems;

import tobacco.core.components.Entity;
import tobacco.core.components.Type;
import tobacco.core.services.Directory;

/**
 * A partial EngineSystem implementation for systems which do stuff with entities
 * @author nirei
 */
public abstract class AbstractEntitySystem extends AbstractSystem {
	
	public AbstractEntitySystem(Type[] _requiredComponents) {
		requiredComponents = _requiredComponents;
	}

	/**
	 * Array with the Components that should be present on an Entity for the system to process it
	 */
	private Type[] requiredComponents;

	/**
	 * Prepares the system for the current tick. It's called when this system's work() beggins.
	 */
	public abstract void setUp();

	// TODO: This calls for Strategy desing pattern but it can wait
	// since I don't think we'll need so much extensibility.

	/**
	 * Calls process on every entity that requires it.
	 */
	public abstract void traverse();

	/**
	 * Finalize this system's work.
	 */
	public abstract void tearDown();

	@Override
	public void work() {
		setUp();
		if(isEnabled())	traverse();
		tearDown();
		
		tick();
	}

	/**
	 * @return The current root Entity
	 */
	public Entity getRoot() {
		return Directory.getDataService().getRoot();
	}

	/**
	 * @return Returns an array of String with the names of the components this system needs on an entity to be able to process.
	 */
	public Type[] getRequiredComponents() {
		return requiredComponents.clone();
	}
	
	/**
	 * Determine if the entity qualifies for processing and therefore should be processed by the system.
	 * 
	 * @param entity
	 *            - Entity to check
	 * @return <b>true</b> - If the Entity has the required Components<br />
	 *         <b>false</b> - Otherwise
	 */
	public boolean qualifies(Entity entity) {
		if(entity == null) return false;
		for (Type type : getRequiredComponents()) {
			if (!entity.has(type))
				return false;
		}
		return true;
	}
}
