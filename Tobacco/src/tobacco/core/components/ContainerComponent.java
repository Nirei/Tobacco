package tobacco.core.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tobacco.core.entities.Entity;

public class ContainerComponent implements Component, Iterable<Entity> {
	
	private static int TYPE = CONTAINER;
	private Map<Long, Entity> children = new HashMap<Long, Entity>();

	@Override
	public Iterator<Entity> iterator() {
		return children.values().iterator();
	}
	
	public void addChild(Entity child) {
		children.put(child.getID(), child);
	}
	
	public void delChildren(long id) {
		children.remove(id);
	}

	@Override
	public int getComponentType() {
		return TYPE;
	}
}
