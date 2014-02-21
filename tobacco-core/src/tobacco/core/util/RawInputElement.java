package tobacco.core.util;

public class RawInputElement {
	
	public static final int
		VALUE_PRESSED = 0,
		VALUE_RELEASED = 1,	
		CODE_MOUSE_X = -10,
		CODE_MOUSE_Y = -11,
		CODE_MOUSE_Z = -12;
	
	int code, value = 0;
	
	public RawInputElement(int _code, int _value) {
		code = _code;
		value = _value;
	}
	
	public RawInputElement(int _code) {
		code = _code;
	}

	public int getCode() {
		return code;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int _value) {
		value = _value;
	}
	
	public String toString()
	{
		return "(Code: " + code + ", Value: " + value + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawInputElement other = (RawInputElement) obj;
		if (code != other.code)
			return false;
		return true;
	}
	

}
