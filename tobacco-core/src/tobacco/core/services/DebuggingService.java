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
package tobacco.core.services;

import tobacco.core.util.Line2D;
import tobacco.core.util.Vector2D;

public interface DebuggingService {

	public void displayText(String tag, String text, Vector2D position);
	public void displayPoint(String tag, Vector2D position);
	public void displayVector(String tag, Line2D position);
	public void removeText(String tag);
	public void removePoint(String tag);
	public void removeVector(String tag);
	public void clearText();
	public void clearPoint();
	public void clearVectors();

}
