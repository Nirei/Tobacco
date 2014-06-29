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

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;

public class EntityRemovalSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = { Component.REMOVE_C };

	public EntityRemovalSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if (qualifies(entity)) {
			if (data != null) {
				Entity parent = (Entity) data;
				ContainerComponent children = (ContainerComponent) parent.getComponent(Component.CONTAINER_C);
				// Remove entity AND its children
				for(Entity e : children) e.putComponent(new RemoveComponent());
				children.delChildren(entity.getID());
				entity.delete(); // from entityList in Entity
			}
		}
		return entity;
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
