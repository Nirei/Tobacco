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
