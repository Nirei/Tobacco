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

import tobacco.core.datatypes.GVector2D;

public class MovementComponent implements Component {

	private GVector2D direction = GVector2D.ZERO;
	private float speed;

	@Override
	public Type getComponentType() {
		return MOVEMENT_C;
	}

	public MovementComponent() {
	}

	public MovementComponent(float _speed) {
		speed = _speed;
	}

	public MovementComponent(GVector2D _direction, float _speed) {
		direction = _direction;
		speed = _speed;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float _speed) {
		speed = _speed;
	}

	public GVector2D getDirection() {
		return direction;
	}

	public void setDirection(GVector2D _direction) {
		direction = _direction;
	}

	public String toString() {
		return "Movement: (Direction: " + direction + ", Speed: " + speed + ")";
	}

}
