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
package tobacco.core.collision;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.util.Vector2D;

// TODO: Implements collection?
// TODO: Refactor QuadTree abstraction up
public class CollisionQuadTree2D {
	
	private static final int
			 		//	S/N W/E
			SW = 0, //	0	0
			SE = 1, //	0	1
			NW = 2, //	1	0
			NE = 3; // 	1	1
	
	private final int nodeSize, maxDepth;
	private Vector2D center, halfSides;
	
	private class Element {
		private Vector2D point;
		private Entity entity;
		
		public Element(Vector2D point, Entity entity) {
			this.point = point;
			this.entity = entity;
		}
		
		public Vector2D getPoint() {
			return point; 
		}
		
		public Entity getEntity() {
			return entity;
		}
	}
	
	private class Node {
		// Number of elements, if -1, it has children
		private int fill = 0;
		private final int depth;
		private Vector2D center;
		private Vector2D halfSides;

		private List<Element> elements = new ArrayList<Element>(nodeSize);
		private Node children[] = new Node[4];

		private Node(Vector2D center, Vector2D halfSides, int depth) {
			this.center = center;
			this.halfSides = halfSides;
			this.depth = depth;
		}
		
		public Node(Vector2D center, Vector2D halfSides) {
			this(center, halfSides, 0);
		}

		private boolean isFull() {
			return (fill >= nodeSize);
		}
		
		private boolean hasChildren() {
			return fill == -1;
		}

		private void subdivide() {
			Vector2D hS1 = halfSides.scale(.5f);
			Vector2D hS2 = new Vector2D(hS1.getX(), -hS1.getY());
			int childDepth = depth + 1;
			
			// Set fill to -1 to indicate non-leaf node
			fill = -1;

			children[SW] = new Node(Vector2D.minus(center, hS2), hS1, childDepth);
			children[SE] = new Node(Vector2D.minus(center, hS1), hS1, childDepth);
			children[NW] = new Node(Vector2D.sum(center, hS2), hS1, childDepth);
			children[NE] = new Node(Vector2D.sum(center, hS1), hS1, childDepth);
			
			// Move elements to the newborn children
			for(Element e : elements) {
				insert(e);
			}

			// Just in case...
			elements = null;
		}

		public boolean insert(Element element) {
			if(!element.getPoint().isInsideArea(center, halfSides)) return false;

			if(hasChildren()) {
				for(Node n : children)
					if(n.insert(element)) return true;
				return false;
			}

			if(isFull() && (depth <= maxDepth)) {
				subdivide();
			} else {
				elements.add(element);
				++fill;
				return true;
			}

			return false;
		}
		
		public List<Entity> query(Vector2D point) {
			// Recursion end condition
			if(!hasChildren()) {
				List<Entity> entities = new ArrayList<Entity>();
				for(Element e : elements) {
					entities.add(e.getEntity());
				}
				return entities;
			}
			
			float centerX = center.getX();
			float centerY = center.getY();
			float pointX = point.getX();
			float pointY = point.getY();
			int child = 0;

			if(pointX > centerX) child |= 0x1;
			if(pointY > centerY) child |= 0x2;
			
			// Tail-recursive call
			return children[child].query(point);
		}
	}

	private Node root;

	public CollisionQuadTree2D(Vector2D center, Vector2D halfSides, int nodeSize, int maxDepth) {
		root = new Node(center, halfSides);
		this.nodeSize = nodeSize;
		this.maxDepth = maxDepth;
		this.center = center;
		this.halfSides = halfSides;
	}
	
	public void insert(Entity entity) {
		Vector2D pos = ((PositionComponent) entity.get(Component.POSITION_C)).getPosition();
		// Insert returns false if inserted element is out of tree range.
		root.insert(new Element(pos, entity));
	}

	public List<Entity> query(Vector2D point) {
		return root.query(point);
	}

	public void clear() {
		root = new Node(center, halfSides);
	}
}
