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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import tobacco.core.collision.Collision;

public class CollisionMapComponent implements Component, Iterable<Collision> {
	
	private Set<Collision> collisionSet = new HashSet<Collision>();

	public void addCollision(Entity e1, Entity e2) {
		synchronized(collisionSet) {
			collisionSet.add(new Collision(e1, e2));			
		}
	}

	public void clear() {
		synchronized(collisionSet) {
			collisionSet.clear();
		}
	}

	@Override
	public Type getComponentType() {
		return COLLISIONMAP_C;
	}

	@Override
	public Iterator<Collision> iterator() {
		Set<Collision> copy = null;
		synchronized(collisionSet) { copy = new HashSet<Collision>(collisionSet); }
		return copy.iterator();
	}
	
	@Override
	public String toString() {
		return "Collisions: " + collisionSet;
	}
}
