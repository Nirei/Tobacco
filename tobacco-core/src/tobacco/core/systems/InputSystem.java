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
package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.Type;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;

/**
 * This system checks which events are player entities listening for keystrokes then asks the KeymapComponent if those events have been received and subsequently executes the associated Commands.
 * 
 * @author nirei
 * 
 */
public class InputSystem extends AbstractListSystem {

	private final static Type[] requiredComponents = { Component.PLAYER_C };

	private KeymapComponent keyMap;

	public InputSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			PlayerComponent playerComp = (PlayerComponent) entity.get(Component.PLAYER_C);

			for (InputEvent event : playerComp) {
				Command command = playerComp.get(event);
				switch (event.getInputType()) {
				case TYPE_HOLD:
					if (keyMap.isPressed(event.getInputCode()))
						command.execute(getRoot(), entity);
					break;
				case TYPE_PRESS:
					if (keyMap.wasPressed(event.getInputCode()))
						command.execute(getRoot(), entity);
					break;
				case TYPE_RELEASE:
					if (keyMap.wasReleased(event.getInputCode()))
						command.execute(getRoot(), entity);
					break;
				}
			}
		}
	}

	@Override
	public void setUp() {
		keyMap = (KeymapComponent) getRoot().get(Component.KEYMAP_C);
	}

	@Override
	public void tearDown() {
		keyMap.clear();
	}

}
