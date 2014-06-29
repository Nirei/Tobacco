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

import java.util.HashSet;
import tobacco.core.util.InputCode;

public class KeymapComponent implements Component {

	HashSet<InputCode> keyMap = new HashSet<InputCode>();
	HashSet<InputCode> pressedNowMap = new HashSet<InputCode>();
	HashSet<InputCode> releasedNowMap = new HashSet<InputCode>();

	@Override
	public String getComponentType() {
		return KEYMAP_C;
	}

	public void press(InputCode key) {
		keyMap.add(key);
		pressedNowMap.add(key);
	}

	public void release(InputCode key) {
		keyMap.remove(key);
		releasedNowMap.add(key);
	}

	public boolean isPressed(InputCode key) {
		return keyMap.contains(key);
	}

	public boolean wasPressed(InputCode key) {
		return pressedNowMap.contains(key);
	}

	public boolean wasReleased(InputCode key) {
		return releasedNowMap.contains(key);
	}

	public void clear() {
		pressedNowMap.clear();
		releasedNowMap.clear();
	}

	public String toString() {
		return "Keymap: [Hold: " + keyMap + ", Pressed: " + pressedNowMap
				+ ", Released: " + releasedNowMap + "]";
	}
}
