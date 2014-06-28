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
			texture = TextureIO.newTexture(TextureStorage.class.getResource(textureFileName), false, null);
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

	public static Texture getErrorTexture() {
		// TODO Apéndice de método generado automáticamente
		try {
			return getTexture("/tobacco/game/test/textures/error.png");
		} catch (TextureNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
