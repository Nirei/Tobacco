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

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import tobacco.core.collision.Collision;
import tobacco.core.entities.Entity;

public class CollisionQueueComponent implements Component {
	
	private Set<Collision> collisionSet = Collections.synchronizedSet(new HashSet<Collision>());
	private Queue<Collision> collisionQueue = new ConcurrentLinkedQueue<Collision>(); 

	public void add(Entity e1, Entity e2) {
		Collision c = new Collision(e1, e2);
		if(collisionSet.add(c)) {
			collisionQueue.add(c);
		}
	}

	public void clear() {
		collisionSet.clear();
		collisionQueue.clear();
	}
	
	public Collision poll() {
		Collision c = collisionQueue.poll();
		collisionSet.remove(c);
		return c;
	}
	
	@Override
	public String toString() {
		return "Collisions: " + collisionSet;
	}
	
	@Override
	public Type getComponentType() {
		return COLLISIONMAP_C;
	}
}
