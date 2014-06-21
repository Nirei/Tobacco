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
		synchronized(children) {
			copy = new ArrayList<Entity>(children.values());
		}
		return copy.iterator();
	}
	
	public void addChild(Entity child) {
		synchronized(children) {
			children.put(child.getID(), child);
		}
	}
	
	public void delChildren(long id) {
		synchronized(children) {
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
