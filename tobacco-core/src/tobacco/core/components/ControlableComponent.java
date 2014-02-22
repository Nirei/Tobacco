package tobacco.core.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tobacco.core.util.Action;
import tobacco.core.util.RawInputElement;

public class ControlableComponent implements Component, Iterable<Action> {

	private Map<RawInputElement, Action> actionMap = new HashMap<RawInputElement, Action>();
	
	@Override
	public short getComponentType() {
		return Component.CONTROLABLE_C;
	}
	
	public void addAction(Action action) {
		actionMap.put(action.getKey(), action);
	}
	
	public void removeAction(Action action) {
		actionMap.remove(action.getKey());
	}
	
	public Action getAction(RawInputElement rie) {
		return actionMap.get(rie);
	}
	
	public void clearActions() {
		actionMap.clear();
	}

	@Override
	public Iterator<Action> iterator() {
		return actionMap.values().iterator();
	}
	
	@Override
	public String toString() {
		return "Controlable: " + actionMap;
	}

}
