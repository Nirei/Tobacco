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
package tobacco.core.systems;

import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;

/**
 * This kind of system traverses the whole list of entities without a specific order
 * and without carrying data from parent to child
 * @author nirei
 */
public abstract class AbstractListSystem extends AbstractEntitySystem {

	public AbstractListSystem(Type[] _requiredComponents) {
		super(_requiredComponents);
	}

	public abstract void process(Entity e, long delta);
	
	@Override
	public void traverse(long milliseconds) {
		for(Entity e : Directory.getEntityService().getEntityList()) {
			process(e, milliseconds);
		}
	}
}
