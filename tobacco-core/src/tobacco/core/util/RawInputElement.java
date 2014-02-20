package tobacco.core.util;

public class RawInputElement {
	
	public static final int
		VALUE_PRESSED = 0,
		VALUE_RELEASED = 1,	
		CODE_MOUSE_X = -10,
		CODE_MOUSE_Y = -11,
		CODE_MOUSE_Z = -12;
	
	int code,value;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RawInputElement(int code, int value) {
		super();
		this.code = code;
		this.value = value;
	}
	
	public String toString()
	{
		return "("+code+","+value+")";
	}
	

}
