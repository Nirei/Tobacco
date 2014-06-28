package tobacco.core.util;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;

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
			return !(fill <= nodeSize);
		}
		
		private boolean hasChildren() {
			return fill == -1;
		}

		private void subdivide() {
			Vector2D hS1 = halfSides.scale(.5f);
			Vector2D hS2 = new Vector2D(hS1.getX(), -hS1.getY());
			int childDepth = depth + 1;
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
			
			// Set fill to -1 to indicate non-leaf node
			fill = -1;
		}

		public boolean insert(Element element) {
			if(!element.getPoint().isInsideArea(center, halfSides)) return false;

			if(isFull() && (depth <= maxDepth)) {
				subdivide();
				
				for(Node n : children)
					if(n.insert(element)) return true;
			} else {
				elements.add(element);
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
	}
	
	public void insert(List<Entity> entities) {
		for(Entity e : entities) {
			Vector2D pos = ((PositionComponent) e.getComponent(Component.POSITION_C)).getPosition();
			if(!root.insert(new Element(pos, e))) {
				// TODO: Throw an exception
			}
		}
	}
	
	public List<Entity> query(Vector2D point) {
		return root.query(point);
	}
}
