package tobacco.core.util;

public class InputEvent {
	private InputCode inputCode;
	private InputType inputType;

	public InputEvent(InputCode _inputCode, InputType _inputType) {
		inputCode = _inputCode;
		inputType = _inputType;
	}

	public InputCode getInputCode() {
		return inputCode;
	}

	public InputType getInputType() {
		return inputType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inputCode == null) ? 0 : inputCode.hashCode());
		result = prime * result
				+ ((inputType == null) ? 0 : inputType.hashCode());
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
		InputEvent other = (InputEvent) obj;
		if (inputCode == null) {
			if (other.inputCode != null)
				return false;
		} else if (!inputCode.equals(other.inputCode))
			return false;
		if (inputType == null) {
			if (other.inputType != null)
				return false;
		} else if (!inputType.equals(other.inputType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InputEvent [inputCode=" + inputCode + ", inputType="
				+ inputType + "]";
	}
}
