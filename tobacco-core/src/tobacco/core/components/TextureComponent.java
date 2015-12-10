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

/**
 * Represents a Texture and keeps associated properties
 * (i.e. sprite columns and cells). This component is
 * immutable and can be reutilized across various {@link Entity}
 * instances.
 * @author nirei
 *
 */
public class TextureComponent implements Component {

	private String imagePath;
	private int width;
	private int height;
	private int columns = 1;
	private int rows = 1;
	private int frames = 1;
	
	public TextureComponent() {}

	/** Creates a texture component with one row and column.
	 * Useful for non-sprite textures.
	 * @param imagePath - path of the texture image.
	 * @param width - width of the texture image.
	 * @param height - height of the texture image.
	 */
	public TextureComponent(String imagePath, int width, int height) {
		this.imagePath = imagePath;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates a texture component and associates texture and number
	 * of sprite columns and rows.
	 * @param imagePath - path of the texture image.
	 * @param width - width of the texture image.
	 * @param height - height of the texture image.
	 * @param columns - columns of the sprite.
	 * @param rows - rows of the sprite.
	 */
	public TextureComponent(String imagePath, int width, int height, int columns, int rows, int frames) {
		this(imagePath, width, height);
		this.columns = columns;
		this.rows = rows;
		this.frames = frames;
	}

	@Override
	public Type getComponentType() {
		return TEXTURE_C;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public String toString() {
		return "ImagePath: " + imagePath;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}

	public int getFrames() {
		return frames;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setFrames(Integer frames) {
		this.frames = frames;
	}
}
