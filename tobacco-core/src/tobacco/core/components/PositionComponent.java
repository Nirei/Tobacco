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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tobacco.core.util.Vector2D;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PositionComponent implements Component {

	private Vector2D position;
	private float zIndex;

	public PositionComponent() {}
	
	public PositionComponent(Vector2D position) {
		this(position, 0f);
	}
	
	public PositionComponent(Vector2D position, float zIndex) {
		this.position = position;
		this.zIndex = zIndex;
	}

	@Override
	public String getComponentType() {
		return POSITION_C;
	}

	@XmlElement
	public Vector2D getPosition() {
		return new Vector2D(position.getX(), position.getY());
	}

	public void setPosition(Vector2D _position) {
		position = _position;
	}
	
	@XmlElement
	public float getZIndex() {
		return zIndex;
	}
	
	public void setZIndex(float zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public String toString() {
		return "Position: " + position;
	}
}
