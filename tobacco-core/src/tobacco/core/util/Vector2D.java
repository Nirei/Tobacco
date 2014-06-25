package tobacco.core.util;

public class Vector2D {
	public static final Vector2D ZERO = new Vector2D(0, 0);
	public static final Vector2D ONE = new Vector2D(1, 1);
	public static final Vector2D HORIZONTAL = new Vector2D(1, 0);
	public static final Vector2D VERTICAL = new Vector2D(0, 1);
	private float x, y;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D normalize() {
		return normalize(this);
	}

	public static Vector2D normalize(Vector2D v) {
		float modulo = (float) Math.sqrt(Math.pow(v.getX(), 2)
				+ Math.pow(v.getY(), 2));
		if (v.isZero())
			return v;
		return new Vector2D(v.getX() / modulo, v.getY() / modulo);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isZero() {
		return x == 0f && y == 0f;
	}

	public static Vector2D sum(Vector2D a, Vector2D b) {
		return new Vector2D(a.x + b.x, a.y + b.y);
	}

	public static Vector2D minus(Vector2D a, Vector2D b) {
		return new Vector2D(a.x - b.x, a.y - b.y);
	}

	public static Vector2D scale(Vector2D v, float n) {
		return new Vector2D(v.x * n, v.y * n);
	}

	public float module() {
		return module(this);
	}

	public static float module(Vector2D v) {
		return (float) Math.sqrt(v.x * v.x + v.y * v.y);
	}

	public static Angle angle(Vector2D a, Vector2D b) {
		return new Angle((float) (Math.atan2(b.y, b.x) - Math.atan2(a.y, a.x)));
	}

	public static float dot(Vector2D a, Vector2D b) {
		return a.x * b.x + a.y * b.y;
	}

	/**
	 * Tell if a vector is near this one
	 * 
	 * @param v
	 *            - Vector
	 * @param radius
	 *            - Maximum distance at which the vectors will be considerer close to each other
	 * @return <b>true</b> - If vector b is, at most, at a distance of radius from a<br />
	 *         <b>false</b> - Otherwise
	 */
	public boolean isNear(Vector2D v, float radius) {
		return module(minus(this, v)) <= radius;
	}

	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}

}
