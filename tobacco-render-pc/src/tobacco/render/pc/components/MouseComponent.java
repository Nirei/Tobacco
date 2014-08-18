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
package tobacco.render.pc.components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tobacco.core.util.Vector2D;

@XmlRootElement
public class MouseComponent implements RendererComponent {

	private Vector2D position = Vector2D.ZERO;

	@Override
	public String getComponentType() {
		return MOUSE_C;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	@XmlElement
	public Vector2D getPosition() {
		return position;
	}

	public String toString() {
		return "Mouse: " + position;
	}
}
