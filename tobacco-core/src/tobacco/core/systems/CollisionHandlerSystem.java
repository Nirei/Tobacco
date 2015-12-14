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

import java.util.ArrayList;
import java.util.List;

import tobacco.core.collision.Collision;
import tobacco.core.collision.CollisionHandler;
import tobacco.core.components.CollisionQueueComponent;
import tobacco.core.components.Component;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;

/**
 * This system handles {@link Collision}s by keeping a list of {@link CollisionHandler}s and calling them over any queued collision
 * @author nirei
 *
 */
public class CollisionHandlerSystem extends AbstractSystem {

	private List<CollisionHandler> handlers = new ArrayList<CollisionHandler>();
	
	@Override
	public void work() {
		Entity root = Directory.getEntityService().getRoot();
		CollisionQueueComponent cols = (CollisionQueueComponent) root.get(Component.COLLISIONMAP_C);
		for(Collision c = cols.poll(); c != null; c = cols.poll()) {
			if(c.getE1().getID() == 0 || c.getE2().getID() == 0) System.out.println("Collision " + c + " detected");
			for(CollisionHandler h : handlers) {
				h.handle(c);
			}
		}
		
		tick();
	}

	public void addHandler(CollisionHandler handler) {
		handlers.add(handler);
	}
}
