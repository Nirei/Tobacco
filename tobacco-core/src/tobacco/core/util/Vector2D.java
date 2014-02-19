package tobacco.core.util;

public class Vector2D {
	private float x,y;
	
	
	public Vector2D(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public static Vector2D sum(Vector2D a,Vector2D b)
	{
		return new Vector2D (a.x+b.x,a.y+b.y);	
	}
	
	public static Vector2D minus(Vector2D a,Vector2D b)
	{
		return new Vector2D (a.x-b.x,a.y-b.y);	
	}
	
	public static Vector2D scale(Vector2D v, float n)
	{
		return new Vector2D (v.x*n,v.y*n);	
	}
	
	public static float dotProduct (Vector2D a, Vector2D b)
	{
		return a.x*b.x+a.y*b.y;
	}
	
	
}
