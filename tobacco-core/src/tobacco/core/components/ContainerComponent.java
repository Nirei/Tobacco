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
package tobacco.core.components;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import tobacco.core.entities.Entity;

public class ContainerComponent implements Component, Iterable<Entity> {

	private List<Entity> children = new LinkedList<Entity>();

	@Override
	public Iterator<Entity> iterator() {
		synchronized (children) {
			return new LinkedList<Entity>(children).iterator();
		}
	}

	public void addChild(Entity child) {
		synchronized (children) {
			children.add(child);
		}
	}

	public void delChildren(Entity e) {
		synchronized (children) {
			children.remove(e);
		}
	}

	@Override
	public Type getComponentType() {
		return CONTAINER_C;
	}

	@Override
	public String toString() {
		synchronized (children) {
			return "Children: " + children;
		}
	}
}
