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
import java.util.Arrays;
import java.util.List;

import tobacco.core.datatypes.GVector2D;

public class QuadTree<T> {
	
	private static final int
			 		//	S/N W/E
			SW = 0, //	0	0
			SE = 1, //	0	1
			NW = 2, //	1	0
			NE = 3; // 	1	1
	
	private final int nodeSize, maxDepth;
	private GVector2D center, halfSides;
	
	private class Element {

		private GVector2D point;
		private T element;
		
		public Element(GVector2D point, T element) {
			this.point = point;
			this.element = element;
		}
		
		public GVector2D getPoint() {
			return point; 
		}
		
		public T getElement() {
			return element;
		}
		
		@Override
		public String toString() {
			return "Element [point=" + point + ", element=" + element + "]";
		}
	}
	
	private class Node<K> {

		// Number of elements, if -1, it has children
		private int fill = 0;
		private final int depth;
		private GVector2D center;
		private GVector2D halfSides;

		private List<Element> elements = new ArrayList<Element>(nodeSize);
		@SuppressWarnings("unchecked")
		private Node<K> children[] = new Node[4];
		
		private Node(GVector2D center, GVector2D halfSides, int depth) {
			this.center = center;
			this.halfSides = halfSides;
			this.depth = depth;
		}
		
		public Node(GVector2D center, GVector2D halfSides) {
			this(center, halfSides, 0);
		}

		private boolean isFull() {
			return (fill >= nodeSize);
		}
		
		private boolean hasChildren() {
			return fill == -1;
		}

		private void subdivide() {
			GVector2D hS = halfSides.scale(.5f);
			float hSx = hS.getX(), hSy = hS.getY();
			int childDepth = depth + 1;
			
			// Set fill to -1 to indicate non-leaf node
			fill = -1;
			
			GVector2D centerSW = GVector2D.sum(center, new GVector2D(-hSx, -hSy));
			GVector2D centerSE = GVector2D.sum(center, new GVector2D(hSx, -hSy));
			GVector2D centerNW = GVector2D.sum(center, new GVector2D(-hSx, hSy));
			GVector2D centerNE = GVector2D.sum(center, new GVector2D(hSx, hSy));
			
			children[SW] = new Node<K>(centerSW, hS, childDepth);
			children[SE] = new Node<K>(centerSE, hS, childDepth);
			children[NW] = new Node<K>(centerNW, hS, childDepth);
			children[NE] = new Node<K>(centerNE, hS, childDepth);
			
			// Move elements to the newborn children
			for(Element e : elements) {
				insert(e);
			}

			// Just in case...
			elements = null;

		}
		
		public boolean insert(Element element) {
			GVector2D point = element.getPoint();
			// We check if the point is inside the area, corners inclusive.
			// This means something might get inserted in more than one leaf.
			if(!(point.getX() >= center.getX() - halfSides.getX() &&
					point.getX() <= center.getX() + halfSides.getX() &&
					point.getY() >= center.getY() - halfSides.getY() &&
					point.getY() <= center.getY() + halfSides.getY())) {
				return false;
			}

			// If the node has children, send them the element.
			if(hasChildren()) {
				for(Node<K> n : children)
					if(n.insert(element)) return true;
				return false;
			}

			// Else, insert it.
			synchronized (elements) {
				if(isFull() && (depth <= maxDepth)) {
					subdivide();
					return insert(element);
				} else {
					elements.add(element);
					++fill;
					return true;
				}
			}
		}
		
		public List<T> query(GVector2D point) {
			// Recursion end condition
			if(!hasChildren()) {
				List<T> found = new ArrayList<T>();
				synchronized (elements) {
					for(Element e : elements) {
						found.add(e.getElement());
					}
				}
				return found;
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
		
		@Override
		public String toString() {
			return "Node [fill=" + fill + ", depth=" + depth + ", center=" + center + ", halfSides=" + halfSides
					+ ", elements=" + elements + ", children=" + Arrays.toString(children) + "]";
		}
	}

	private Node<T> root;

	public QuadTree(GVector2D center, GVector2D halfSides, int nodeSize, int maxDepth) {
		root = new Node<T>(center, halfSides);
		this.nodeSize = nodeSize;
		this.maxDepth = maxDepth;
		this.center = center;
		this.halfSides = halfSides;
	}
	
	public void insert(T elem, GVector2D pos) {
		// Insert returns false if inserted element is out of tree range.
		root.insert(new Element(pos, elem));
	}

	public List<T> query(GVector2D point) {
		return root.query(point);
	}

	public void clear() {
		root = new Node<T>(center, halfSides);
	}

	@Override
	public String toString() {
		return "QuadTree [nodeSize=" + nodeSize + ", maxDepth=" + maxDepth + ", center=" + center + ", halfSides="
				+ halfSides + ", root=" + root + "]";
	}
}