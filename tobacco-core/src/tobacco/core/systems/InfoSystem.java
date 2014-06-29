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

public class InfoSystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = { Component.DEBUGGING_C };
	private int tick = 0;

	public InfoSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		String str = (String) data;
		if (str == null)
			str = "";

		if (qualifies(entity)) {
			System.out.println(str + entity + " {");
			for (Component c : entity)
				if (c.getComponentType() != Component.DEBUGGING_C)
					System.out.println(str + "\t" + c);
			System.out.println(str + "}");
		}

		return str + "\t";
	}

	@Override
	public void setUp() {
		System.out.println("Tick: " + tick++ + " ---");
	}

	@Override
	public void tearDown() {
	}
}
