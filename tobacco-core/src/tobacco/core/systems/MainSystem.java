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

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Entity;

/**
 * This is an special system in charge of every other system.
 * It mantains a list of EngineSystem and tells each one to
 * do their job sequentially each tick.
 * @author nirei
 *
 */
// TODO: Concurrency if it was necessary.
public class MainSystem implements EngineSystem {

	private List<EngineSystem> systemList = new ArrayList<EngineSystem>();

	@Override
	public void work(Entity entity) {
		for (EngineSystem s : systemList) {
			s.work(entity);
		}
	}

	public void addSystem(EngineSystem system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
