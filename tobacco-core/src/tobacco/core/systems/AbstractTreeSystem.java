/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
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
package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;

/**
 * This kind of system recursively traverses the tree of entities processing each and carrying recursive data if required.
 * 
 * @author nirei
 */
public abstract class AbstractTreeSystem extends AbstractEntitySystem {

	public AbstractTreeSystem(Type[] _requiredComponents) {
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
	public abstract Object process(Entity entity, Object data, long milliseconds);

	private void processTree(Entity entity, Object data, long milliseconds) {
		data = process(entity, data, milliseconds);

		if (entity.has(Component.CONTAINER_C)) {
			ContainerComponent ccomp = (ContainerComponent) entity.get(Component.CONTAINER_C);
			for (Entity e : ccomp) {
				processTree(e, data, milliseconds);
			}
		}
	}

	@Override
	public void traverse(long milliseconds) {
		processTree(getRoot(), null, milliseconds);
	}
}
