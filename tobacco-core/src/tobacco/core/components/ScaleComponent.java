package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class ScaleComponent implements Component {

	private Vector2D scale = Vector2D.ZERO;

	public ScaleComponent() {
	}

	public ScaleComponent(Vector2D _scale) {
		scale = _scale;
	}

	@Override
	public String getComponentType() {
		return SCALE_C;
	}

	public Vector2D getScale() {
		return scale;
	}

	public void setScale(Vector2D _scale) {
		scale = _scale;
	}

	@Override
	public String toString() {
		return "Scale: " + scale;
	}
}
