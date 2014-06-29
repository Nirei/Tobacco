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

/**
 * Components are storage for all the game data.
 * 
 * @author nirei
 * 
 */
public interface Component {

	public static final String CONTAINER_C = "CONTAINER_C";
	public static final String DEBUGGING_C = "DEBUGGING_C";
	public static final String DRAWABLE_C = "DRAWABLE_C";
	public static final String DURATION_C = "DURATION_C";
	public static final String KEYMAP_C = "KEYMAP_C";
	public static final String MOVEMENT_C = "MOVEMENT_C";
	public static final String PLAYER_C = "PLAYER_C";
	public static final String POSITION_C = "POSITION_C";
	public static final String REMOVE_C = "REMOVE_C";
	public static final String ROTATION_C = "ROTATION_C";
	public static final String SCALE_C = "SCALE_C";
	public static final String TRAJECTORY_C = "TRAJECTORY_C";
	public static final String SOLIDITY_C = "SOLIDITY_C";

	/**
	 * Returns this component's name.
	 * 
	 * @return {@link String} representing the name of the component.
	 */
	public abstract String getComponentType();

}
