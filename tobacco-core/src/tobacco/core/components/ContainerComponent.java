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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class ContainerComponent implements Component, Iterable<Entity> {

	private Map<Long, Entity> children = new HashMap<Long, Entity>();

	@Override
	public Iterator<Entity> iterator() {
		ArrayList<Entity> copy;
		synchronized (children) {
			copy = new ArrayList<Entity>(children.values());
		}
		return copy.iterator();
	}

	public void addChild(Entity child) {
		synchronized (children) {
			children.put(child.getID(), child);
		}
	}

	public void delChildren(long id) {
		synchronized (children) {
			children.remove(id);
		}
	}

	@Override
	public String getComponentType() {
		return CONTAINER_C;
	}

	@Override
	public String toString() {
		return "Children: " + children;
	}
}
