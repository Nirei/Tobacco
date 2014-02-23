package tobacco.core.components;

import java.util.HashMap;
import tobacco.core.actions.Action;

public class ControlableComponent extends HashMap<String, Action> implements Component {

	private static final long serialVersionUID = -6416378237683795075L;

	@Override
	public String getComponentType() {
		return CONTROLABLE_C;
	}

	@Override
	public String toString() {
		return "Controlable: " + super.keySet().toString();
	}

}
