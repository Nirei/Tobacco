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
