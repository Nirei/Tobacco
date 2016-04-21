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

public final class NullDebuggingService implements DebuggingService {
	
	private static void printWarning(String method) {
		System.err.println("NullDebuggingService received a call to " + method);
	}

	@Override
	public void clearText() {
		printWarning("clearText()");
	}

	@Override
	public void clearPoint() {
		printWarning("clearPoint()");
	}

	@Override
	public void clearVectors() {
		printWarning("clearVectors()");
	}

	@Override
	public void displayText(String tag, String text, Vector2D position) {
		printWarning("displayText()");
		
	}

	@Override
	public void displayPoint(String tag, Vector2D position) {
		printWarning("displayPoint()");
		
	}

	@Override
	public void displayVector(String tag, Line2D position) {
		printWarning("displayVector()");
		
	}

	@Override
	public void removeText(String tag) {
		printWarning("removeText()");
		
	}

	@Override
	public void removePoint(String tag) {
		printWarning("removePoint()");
		
	}

	@Override
	public void removeVector(String tag) {
		printWarning("removeVector()");		
	}
}
