package tobacco.core.components;

import java.util.HashSet;
import tobacco.core.util.InputCode;

public class KeymapComponent implements Component {

	HashSet<InputCode> keyMap = new HashSet<InputCode>();
	HashSet<InputCode> pressedNowMap = new HashSet<InputCode>();
	HashSet<InputCode> releasedNowMap = new HashSet<InputCode>();

	@Override
	public String getComponentType() {
		return KEYMAP_C;
	}

	public void press(InputCode key) { 
		keyMap.add(key);
		pressedNowMap.add(key);
	}
	public void release(InputCode key) { 
		keyMap.remove(key);
		releasedNowMap.add(key);
	}
	public boolean isPressed(InputCode key) { return keyMap.contains(key); }
	public boolean wasPressed(InputCode key) { return pressedNowMap.contains(key); }
	public boolean wasReleased(InputCode key) { return releasedNowMap.contains(key); }
	public void clear() {
		pressedNowMap.clear();
		releasedNowMap.clear();
	}

	public String toString() {
		return "Keymap: [Hold: " + keyMap + ", Pressed: " + pressedNowMap + ", Released: " + releasedNowMap + "]";
	}
}
