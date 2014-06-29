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

/**
 * This kind of system traverses the whole list of entities without a specific order
 * and without carrying data from parent to child
 * @author nirei
 */
public abstract class AbstractListSystem extends AbstractSystem {

	public AbstractListSystem(String[] _requiredComponents) {
		super(_requiredComponents);
	}
	
	/**
	 * Defines what this system does to Entities
	 * 
	 * @param entity
	 *            - Current Entity being processed
	 * @param data
	 *            - Recursive data received when calling it from its parent
	 * @return Data to pass on to its children
	 */
	public abstract void process(Entity entity);

	@Override
	public void traverse() {
		for(Entity e : Entity.getEntityList()) {
			process(e);
		}
	}
}
