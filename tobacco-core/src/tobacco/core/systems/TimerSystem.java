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
package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.RemoveComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;

public class TimerSystem extends AbstractListSystem {

	private static final Type[] requiredComponents = { Component.DURATION_C };
	
	public TimerSystem() {
		super(requiredComponents);
	}

	private void checkDuration(Entity entity, long delta) {
		DurationComponent durComp = (DurationComponent) entity.get(Component.DURATION_C);
		long left = durComp.getDuration() - delta;

		// Update duration left with delta
		synchronized (durComp) {
			durComp.setDuration(left);
		}
		// If time has expired, mark Entity for removal :(
		if (left <= 0)
			entity.add(new RemoveComponent());
	}

	@Override
	public void setUp() {}

	@Override
	public void process(Entity entity, long delta) {
		if(qualifies(entity)) {
			checkDuration(entity, delta);
		}
	}

	@Override public void tearDown() {}
}
