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
package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class MovementComponent implements Component {

	private Vector2D direction = Vector2D.ZERO;
	private float speed;

	@Override
	public Type getComponentType() {
		return MOVEMENT_C;
	}

	public MovementComponent() {}

	public MovementComponent(Float speed) {
		this.speed = speed;
	}

	public MovementComponent(Vector2D direction, float speed) {
		this.direction = direction;
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public String toString() {
		return "Movement: (Direction: " + direction + ", Speed: " + speed + ")";
	}

}
