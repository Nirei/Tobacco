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
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;

public class TimerSystem extends AbstractListSystem {

	private static final String[] requiredComponents = { Component.DURATION_C };
	private long lastCall = System.currentTimeMillis();
	private long delta;
	
	public TimerSystem() {
		super(requiredComponents);
	}

	private void checkDuration(Entity entity, long delta) {
		DurationComponent durComp = (DurationComponent) entity.getComponent(Component.DURATION_C);
		long left = durComp.getDuration() - delta;

		// Update duration left with delta
		synchronized (durComp) {
			durComp.setDuration(left);
		}
		// If time has expired, mark Entity for removal :(
		if (left <= 0)
			entity.putComponent(new RemoveComponent());
	}

	@Override
	public void setUp() {
		long now = System.currentTimeMillis();
		delta = now - lastCall;
		lastCall = now;
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity)) {
			checkDuration(entity, delta);
		}
	}

	@Override public void tearDown() {}
}
