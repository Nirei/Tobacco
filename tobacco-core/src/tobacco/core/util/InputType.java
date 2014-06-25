package tobacco.core.util;

public enum InputType {
	TYPE_HOLD(0), TYPE_PRESS(1), TYPE_RELEASE(2);

	private int code;

	private InputType(int _code) {
		code = _code;
	}

	public int getCode() {
		return code;
	}

}
