package tobacco.game.test.components;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tobacco.core.util.Vector2D;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DirectionComponent implements GameComponent {
	
	private Vector2D direction;
	
	public DirectionComponent() {}
	
	public DirectionComponent(Vector2D direction) {
		this.direction = direction;
	}

	@Override
	public String getComponentType() {
		return DIRECTION_C;
	}

	@XmlElement
	public Vector2D getDirection() {
		return direction;
	}
	
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "Direction: " + direction;
	}
}
