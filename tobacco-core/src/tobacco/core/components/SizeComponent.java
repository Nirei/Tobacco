package tobacco.core.components;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tobacco.core.util.Vector2D;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SizeComponent implements Component {
	
	private Vector2D size;
	
	public SizeComponent() {}
	
	public SizeComponent(Vector2D size) {
		this.size = size;
	}
	
	@XmlElement
	public Vector2D getSize() {
		return size == null ? null : new Vector2D(size.getX(), size.getY());
	}

	public void setSize(Vector2D _size) {
		size = _size;
	}

	@Override
	public String getComponentType() {
		return SIZE_C;
	}
	
	@Override
	public String toString() {
		return "Size: " + size;
	}
}
