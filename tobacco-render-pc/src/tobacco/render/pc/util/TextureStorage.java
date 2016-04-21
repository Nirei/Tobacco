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
package tobacco.render.pc.util;

import java.util.HashMap;
import java.util.Map;

import tobacco.render.pc.util.exceptions.TextureNotFoundException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 * Loads and stores textures by name (path)
 * 
 * @author nirei
 * 
 */
public final class TextureStorage {

	private TextureStorage() {
	}

	private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

	private static Texture loadTexture(String textureFileName)
			throws TextureNotFoundException {
		// Load texture from image
		Texture texture = null;

		try {
			// Create a OpenGL Texture object from (URL, mipmap, file suffix)
			// Use URL so that can read from JAR anddisk file.
			texture = TextureIO.newTexture(TextureStorage.class.getClassLoader().getResource(textureFileName), false, null);
		} catch (Exception e) {
			throw new TextureNotFoundException(textureFileName, e);
		}
		return texture;
	}

	public static Texture getTexture(String name)
			throws TextureNotFoundException {
		if (!textureMap.containsKey(name)) {
			textureMap.put(name, loadTexture(name));
		}

		return textureMap.get(name);
	}

	public static Texture getErrorTexture() throws TextureNotFoundException {
		return getTexture("textures/error.png");
	}
}
