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

public class ScaleComponent implements Component {

	private GVector2D scale;
	
	public ScaleComponent() {}

	public ScaleComponent(GVector2D _scale) {
		scale = _scale;
	}

	@Override
	public Type getComponentType() {
		return SCALE_C;
	}

	public GVector2D getScale() {
		return scale;
	}

	public void setScale(GVector2D _scale) {
		scale = _scale;
	}

	@Override
	public String toString() {
		return "Scale: " + scale;
	}
}
