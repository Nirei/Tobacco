package tobacco.core.components;

import java.util.concurrent.ArrayBlockingQueue;

import tobacco.core.util.RawInputElement;

public class KeyMapComponent extends ArrayBlockingQueue<RawInputElement> implements Component
{	
	
	private static final long serialVersionUID = 7606284401632225233L;

	public KeyMapComponent(int capacity) {
		super(capacity);
	}
	

	@Override
	public short getComponentType() {
		return Component.KEYMAP_C;
	}

	@Override
	public String toString()
	{
		String s = super.toString();
		return  "KeyMapComponent: " + s;
	}
}
