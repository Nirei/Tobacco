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
public class ScaleComponent implements Component {

	private Vector2D scale;
	
	public ScaleComponent() {}

	public ScaleComponent(Vector2D _scale) {
		scale = _scale;
	}

	@Override
	public String getComponentType() {
		return SCALE_C;
	}

	@XmlElement
	public Vector2D getScale() {
		return scale;
	}

	public void setScale(Vector2D _scale) {
		scale = _scale;
	}

	@Override
	public String toString() {
		return "Scale: " + scale;
	}
}
