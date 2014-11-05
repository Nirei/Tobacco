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

/**
 * This contain the game's logic.
 * 
 * @author nirei
 */
public interface EngineSystem {

	/**
	 * Do this system's work.
	 */
	public void work();
	
	/**
	 * Enable or disable this system. Disabled systems will still call setUp()
	 * and tearDown() but won't call their traverse() function.
	 * 
	 * @param enabled <br />
	 * <b>true</b> - to enable this system <br />
	 * <b>false</b> - to disable this system
	 */
	public void enable(boolean enabled);

	/**
	 * Returns this system's status.
	 * @return <b>true</b> - if the system is enabled <br />
	 * <b>false</b> - otherwise
	 */
	public boolean isEnabled();
	
	/**
	 * Returns the number of times this system has been run
	 * @return number of times this system has been run
	 */
	public long getTicks();
}
