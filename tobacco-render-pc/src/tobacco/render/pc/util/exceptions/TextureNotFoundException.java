/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
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
package tobacco.render.pc.util.exceptions;

public class TextureNotFoundException extends Exception {

	private static final long serialVersionUID = 4517464021048914467L;

	private final String texturePath;

	public TextureNotFoundException(String _texturePath, Throwable cause) {
		super("Texture " + _texturePath + " not found", cause);
		texturePath = _texturePath;
	}

	public String getTexturePath() {
		return texturePath;
	}
}
