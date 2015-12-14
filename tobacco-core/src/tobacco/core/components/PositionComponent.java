/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class PositionComponent implements Component {

	private Vector2D position;

	public PositionComponent() {}
	
	public PositionComponent(Vector2D position) {
		this.position = position;
	}

	@Override
	public Type getComponentType() {
		return POSITION_C;
	}

	public Vector2D getPosition() {
		return new Vector2D(position.getX(), position.getY());
	}

	public void setPosition(Vector2D _position) {
		position = _position;
	}

	@Override
	public String toString() {
		return "Position: " + position;
	}
}
