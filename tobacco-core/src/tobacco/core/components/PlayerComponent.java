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

import tobacco.core.datatypes.GInputEvent;
import tobacco.core.util.Command;

public class PlayerComponent implements Component, Iterable<GInputEvent> {

	private Map<GInputEvent, Command> actionMap = new HashMap<GInputEvent, Command>();

	@Override
	public Type getComponentType() {
		return PLAYER_C;
	}

	@Override
	public String toString() {
		return "Player: " + actionMap.keySet().toString();
	}

	public void put(GInputEvent key, Command value) {
		actionMap.put(key, value);
	}

	public Command get(GInputEvent key) {
		return actionMap.get(key);
	}

	public boolean contains(GInputEvent key) {
		return actionMap.containsKey(key);
	}

	@Override
	public Iterator<GInputEvent> iterator() {
		return actionMap.keySet().iterator();
	}
}
