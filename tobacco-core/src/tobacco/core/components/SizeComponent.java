package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class SizeComponent implements Component {
	
	private Vector2D size;
	
	public SizeComponent() {}
	
	public SizeComponent(Vector2D size) {
		this.size = size;
	}
	
	public Vector2D getSize() {
		return size == null ? null : new Vector2D(size.getX(), size.getY());
	}

	public void setSize(Vector2D _size) {
		size = _size;
	}

	@Override
	public Type getComponentType() {
		return SIZE_C;
	}
	
	@Override
	public String toString() {
		return "Size: " + size;
	}
}
