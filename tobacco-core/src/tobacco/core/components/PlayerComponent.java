package tobacco.core.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;

public class PlayerComponent implements Component, Iterable<InputEvent> {

	private Map<InputEvent, Command> actionMap = new HashMap<InputEvent, Command>();
	
	@Override
	public String getComponentType() {
		return PLAYER_C;
	}

	@Override
	public String toString() {
		return "Player: " + actionMap.keySet().toString();
	}

	public void put(InputEvent key, Command value) {
		actionMap.put(key, value);
	}
	
	public Command get(InputEvent key) {
		return actionMap.get(key);
	}
	
	public boolean contains(InputEvent key) {
		return actionMap.containsKey(key);
	}

	@Override
	public Iterator<InputEvent> iterator() {
		return actionMap.keySet().iterator();
	}
}
