/*
* 	Tobacco - A portable and reusable game engine written in Java.
*	Copyright © 2014 Nirei
*
*	This file is part of Tobacco
*
*   Tobacco is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package tobacco.core.util;

public class Vector2D {
	public static final Vector2D ZERO = new Vector2D(0, 0);
	public static final Vector2D ONE = new Vector2D(1, 1);
	public static final Vector2D HORIZONTAL = new Vector2D(1, 0);
	public static final Vector2D VERTICAL = new Vector2D(0, 1);
	private final float x, y;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D normalize() {
		float modulo = (float) Math.sqrt(Math.pow(getX(), 2)
				+ Math.pow(getY(), 2));
		if (isZero())
			return this;
		return new Vector2D(getX() / modulo, getY() / modulo);
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
	
	public Vector2D scale(float n) {
		return new Vector2D(x*n, y*n);
	}

	public float module() {
		return (float) Math.sqrt(x*x + y*y);
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
	 * @param v - Vector
	 * @param radius - Maximum distance at which the vectors will be considerer close to each other
	 * @return <b>true</b> - If vector b is, at most, at a distance of radius from a<br />
	 *         <b>false</b> - Otherwise
	 */
	public boolean isNear(Vector2D v, float radius) {
		return minus(this, v).module() <= radius;
	}
	
	/**
	 * Check if this vector is inside the rectangular area described by two
	 * given vectors representing the center and half the length of the sides
	 * of the rectangle. This is useful for bounding boxes.
	 * @param center - Vector representing the center of the area
	 * @param halfSides - Vector that gives half the size of the rect
	 * @return	<b>true</b> - If the point is inside the described vector<br />
	 * 			<b>false</b> - Otherwise
	 */
	public boolean isInsideArea(Vector2D center, Vector2D halfSides) {
		return
				this.x >= center.x - halfSides.x &&
				this.x < center.x + halfSides.x &&
				this.y >= center.y - halfSides.y &&
				this.y < center.y + halfSides.y;
	}

	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}

}
