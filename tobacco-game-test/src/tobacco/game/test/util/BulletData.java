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
package tobacco.game.test.util;

import tobacco.core.util.Vector2D;

public class BulletData {

	private String texture = null;
	private Vector2D size = Vector2D.ZERO;

	public BulletData(String texture, Vector2D size) {
		this.texture = texture;
		this.size = size;
	}

	public String getTexture() {
		return texture;
	}

	public Vector2D getSize() {
		return size;
	}
}
