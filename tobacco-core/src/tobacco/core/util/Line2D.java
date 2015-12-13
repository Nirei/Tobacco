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

import tobacco.core.datatypes.GVector2D;

public class Line2D {

	private final GVector2D a, b;
	
	public Line2D(GVector2D a, GVector2D b) {
		this.a = a;
		this.b = b;
	}
	
	public GVector2D getA() {
		return a;
	}
	
	public GVector2D getB() {
		return b;
	}
	
	public float length() {
		return GVector2D.minus(a, b).module();
	}
}
