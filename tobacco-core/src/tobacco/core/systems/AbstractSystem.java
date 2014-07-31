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

public abstract class AbstractSystem implements EngineSystem {
	
	private boolean enabled = true;

	public AbstractSystem(String[] _requiredComponents) {
		requiredComponents = _requiredComponents;
	}

	/**
	 * Array with the Components that should be present on an Entity for the system to process it
	 */
	private String[] requiredComponents;

	/**
	 * Current root entity
	 */
	private Entity rootEntity;

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
	public void work(Entity root) {

		if (getRootEntity() != root) {
			setRootEntity(root);
		}
		setUp();
		if(enabled)	traverse();
		tearDown();
	}

	/**
	 * @return The current root Entity
	 */
	public Entity getRootEntity() {
		return rootEntity;
	}

	public void setRootEntity(Entity _rootEntity) {
		rootEntity = _rootEntity;
	}

	/**
	 * @return Returns an array of String with the names of the components this system needs on an entity to be able to process.
	 */
	public String[] getRequiredComponents() {
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
		for (String type : getRequiredComponents()) {
			if (!entity.has(type))
				return false;
		}
		return true;
	}
	
	/**
	 * Enable or disable this system. Disabled systems will still call setUp()
	 * and tearDown() but won't call their traverse() function.
	 * 
	 * @param enabled <br />
	 * <b>true</b> - to enable this system <br />
	 * <b>false</b> - to disable this system
	 */
	public void enable(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Return this system's status.
	 * @return <b>true</b> - if the system is enabled <br />
	 * <b>false</b> - otherwise
	 */
	public boolean isEnabled() {
		return enabled;
	}
}
