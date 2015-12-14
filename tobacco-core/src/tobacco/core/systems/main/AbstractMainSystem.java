/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.core.systems.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tobacco.core.systems.AbstractSystem;

/**
 * This is an special system in charge of every other system.
 * It mantains a list of EngineSystem and tells each one to
 * do their job when necessary.
 * @author nirei
 *
 */
public abstract class AbstractMainSystem extends AbstractSystem {

	private static final Logger LOGGER = Logger.getLogger(AbstractMainSystem.class.getName());
	
	protected List<AbstractSystem> systemList = new ArrayList<AbstractSystem>();
	
	public void add(AbstractSystem system) {
		LOGGER.log(Level.INFO, "Loaded system: " + system.getClass().getSimpleName());
		systemList.add(system);
	}
	
	public List<AbstractSystem> getSystems() {
		return new ArrayList<AbstractSystem>(systemList);
	}

	public void clear() {
		systemList.clear();
	}
}
