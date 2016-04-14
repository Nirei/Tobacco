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
 * Typed systems ask the {@link EntityService} for every {@link Entity}
 * containing a {@link Component} an specified {@link Type} on every
 * tick. Then do their work only on the returned entities.
 * @author nirei
 */
public abstract class AbstractTypedSystem extends AbstractEntitySystem {
	
	private Type type;

	/**
	 * Constructs an AbstractTypedSystem that will check entities which have
	 * a type {@link Type} {@link Component} for requiredComponents and
	 * process them.
	 * @param requiredComponents - component types required by the system 
	 * to be present in an Entity to do its work
	 * @param type - component type that the entities processed by the
	 * system must have
	 */
	public AbstractTypedSystem(Type[] requiredComponents, Type type) {
		super(requiredComponents);
		this.type = type;
	}
	
	public abstract void process(Entity e, long delta);

	@Override
	public void traverse(long milliseconds) {
		for(Entity e : Directory.getEntityService().findAll(type)) {
			process(e, milliseconds);
		}
	}

}
