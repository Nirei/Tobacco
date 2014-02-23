package tobacco.core.util;

public class Vector2D {
	private float x,y;
	
	
	public Vector2D(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public static Vector2D normalize(Vector2D v) {
		float modulo = (float) Math.sqrt(Math.pow(v.getX(),2) + Math.pow(v.getY(),2));
		if (v.isZero()) return v;
		return new Vector2D(v.getX() / modulo , v.getY() / modulo);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public boolean isZero() {
		return x==0f && y==0f;
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
	
	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}
	
}
