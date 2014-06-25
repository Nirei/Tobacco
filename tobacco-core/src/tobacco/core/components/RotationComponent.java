package tobacco.core.components;

public class RotationComponent implements Component {

	private float rotation = 0f;

	public RotationComponent() {
	}

	public RotationComponent(float _rotation) {
		rotation = _rotation;
	}

	@Override
	public String getComponentType() {
		return ROTATION_C;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float _rotation) {
		rotation = _rotation;
	}

	@Override
	public String toString() {
		return "Rotation: " + rotation;
	}
}
