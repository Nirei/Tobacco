package tobacco.core.components;

import tobacco.core.datatypes.GVector2D;

public class SizeComponent implements Component {
	
	private GVector2D size;
	
	public SizeComponent() {}
	
	public SizeComponent(GVector2D size) {
		this.size = size;
	}
	
	public GVector2D getSize() {
		return size == null ? null : new GVector2D(size.getX(), size.getY());
	}

	public void setSize(GVector2D _size) {
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
