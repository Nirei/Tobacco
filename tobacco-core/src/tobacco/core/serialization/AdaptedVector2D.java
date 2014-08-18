package tobacco.core.serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class AdaptedVector2D {
	
	public float x,y;
	
	public AdaptedVector2D() {}
	
	public AdaptedVector2D(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	@XmlAttribute
	public float getX() {
		return x;
	}

	@XmlAttribute
	public float getY() {
		return y;
	}
}
