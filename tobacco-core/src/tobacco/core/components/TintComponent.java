/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.core.components;

public class TintComponent implements Component {

	private float red;
	private float green;
	private float blue;
	
	public static final TintComponent RED = new TintComponent(1f,0f,0f);
	public static final TintComponent LIGHT_BLUE = new TintComponent(0.5f,0.5f,1f);
	public static final Component GREEN = new TintComponent(0f, 1f, 0f);

	public TintComponent(float r, float g, float b) {
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public Type getComponentType() {
		return TINT_C;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public void setRed(Float red) {
		this.red = red;
	}

	public void setGreen(Float green) {
		this.green = green;
	}

	public void setBlue(Float blue) {
		this.blue = blue;
	}
	
}
