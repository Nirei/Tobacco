package tobacco.core.components;


import tobacco.core.util.Vector2D;

public class PositionComponent implements Component {

	private Vector2D position; 
	
	public PositionComponent(Vector2D _position) {
		position = _position;
	}

	@Override
	public short getComponentType() {
		return POSITION_C;
	}
	
	public Vector2D getPosition()
	{
		return new Vector2D(position.getX(),position.getY());
	}
	
	public void setPosition(Vector2D _position) {
		position = _position;
	}

	@Override
	public String toString() {
		return "Position: " + position;
	}
}
