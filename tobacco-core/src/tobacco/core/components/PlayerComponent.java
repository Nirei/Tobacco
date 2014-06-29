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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;

public class PlayerComponent implements Component, Iterable<InputEvent> {

	private Map<InputEvent, Command> actionMap = new HashMap<InputEvent, Command>();

	@Override
	public String getComponentType() {
		return PLAYER_C;
	}

	@Override
	public String toString() {
		return "Player: " + actionMap.keySet().toString();
	}

	public void put(InputEvent key, Command value) {
		actionMap.put(key, value);
	}

	public Command get(InputEvent key) {
		return actionMap.get(key);
	}

	public boolean contains(InputEvent key) {
		return actionMap.containsKey(key);
	}

	@Override
	public Iterator<InputEvent> iterator() {
		return actionMap.keySet().iterator();
	}
}
