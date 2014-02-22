package tobacco.game.test.main;

import java.awt.event.KeyEvent;

import tobacco.core.util.Action;
import tobacco.core.util.RawInputElement;

public abstract class AbstractAction implements Action {
	private RawInputElement key = new RawInputElement(KeyEvent.VK_UP);
	private String name;
	
	public AbstractAction(RawInputElement _key, String _name) {
		key = _key;
		name = _name;
	}
	
	@Override
	public RawInputElement getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
