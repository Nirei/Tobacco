package tobacco.core.components;


import tobacco.core.util.Vector2D;

public class PositionComponent implements Component {

	private Vector2D position; 
	@Override
	public int getComponentType() {
		return POSITION_C;
	}
	
	public Vector2D getPosition()
	{
		return new Vector2D(position.getX(),position.getY());
		
	}

}
