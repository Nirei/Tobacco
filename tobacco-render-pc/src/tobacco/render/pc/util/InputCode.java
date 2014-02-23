package tobacco.render.pc.util;

import com.jogamp.newt.event.KeyEvent;

public enum InputCode {
	MOUSE_LEFT(-1),
	MOUSE_CENTER(-2),
	MOUSE_RIGHT(-3),
		
	KEY_CHARACTER(0),

	KEY_ALT(KeyEvent.VK_ALT),
	KEY_ALT_GR(KeyEvent.VK_ALT_GRAPH),
	KEY_UP(KeyEvent.VK_UP),
	KEY_DOWN(KeyEvent.VK_DOWN),
	KEY_LEFT(KeyEvent.VK_LEFT),
	KEY_RIGHT(KeyEvent.VK_RIGHT),
	KEY_SPACE(KeyEvent.VK_SPACE),
	KEY_SCAPE(KeyEvent.VK_SPACE),
	KEY_ENTER(KeyEvent.VK_ENTER),
	KEY_ESCAPE(KeyEvent.VK_ESCAPE),
	KEY_SHIFT(KeyEvent.VK_SHIFT),
	KEY_CONTROL(KeyEvent.VK_CONTROL),
	KEY_META(KeyEvent.VK_META),
	KEY_WINDOWS(KeyEvent.VK_WINDOWS),
	KEY_MENU(KeyEvent.VK_CONTEXT_MENU),
	KEY_TAB(KeyEvent.VK_TAB),
	KEY_TILDE(KeyEvent.VK_TILDE);
	
	private final int code;
	private InputCode(int _code) {
		code = _code;
	}
	
	public static InputCode getKeyByCode(int _code) {
		for(int i=0; i<values().length; i++) {
			if(values()[i].code == _code) {
				return values()[i];
			}
		}
		return KEY_CHARACTER;
	}
	
	public boolean isCharacter() {
		return code == 0;
	}
}
