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

public class DrawableComponent implements Component {

	private String imagePath;
	private Vector2D size;

	public DrawableComponent() {
	}

	public DrawableComponent(String _imagePath, Vector2D _size) {
		imagePath = _imagePath;
		size = _size;
	}

	@Override
	public String getComponentType() {
		return DRAWABLE_C;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String _imagePath) {
		imagePath = _imagePath;
	}

	public Vector2D getSize() {
		return size == null ? null : new Vector2D(size.getX(), size.getY());
	}

	public void setSize(Vector2D _size) {
		size = _size;
	}

	@Override
	public String toString() {
		return "Drawable: (ImagePath: " + imagePath + ", Size: " + size + ")";
	}
}
