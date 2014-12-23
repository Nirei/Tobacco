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
	
	public final static Type COLLISIONMAP_C = new Type("COLLISIONMAP_C", CollisionQueueComponent.class);
	public final static Type CONTAINER_C = new Type("CONTAINER_C", ContainerComponent.class);
	public final static Type DEBUGGING_C = new Type("DEBUGGING_C", DebuggingComponent.class);
	public final static Type DURATION_C = new Type("DURATION_C", DurationComponent.class);
	public final static Type KEYMAP_C = new Type("KEYMAP_C", KeymapComponent.class);
	public final static Type MOVEMENT_C = new Type("MOVEMENT_C", MovementComponent.class);
	public final static Type PLAYER_C = new Type("PLAYER_C", PlayerComponent.class);
	public final static Type POSITION_C = new Type("POSITION_C", PositionComponent.class);
	public final static Type REMOVE_C = new Type("REMOVE_C", RemoveComponent.class);
	public final static Type ROTATION_C = new Type("ROTATION_C", RotationComponent.class);
	public final static Type SCALE_C = new Type("SCALE_C", ScaleComponent.class);
	public final static Type SCREEN_C = new Type("SCREEN_C", ScreenComponent.class);
	public final static Type SIZE_C = new Type("SIZE_C", SizeComponent.class);
	public final static Type SOLIDITY_C = new Type("SOLIDITY_C", SolidityComponent.class);
	public final static Type TRAJECTORY_C = new Type("TRAJECTORY_C", TrajectoryComponent.class);

	/**
	 * Returns this component's name.
	 * 
	 * @return {@link String} representing the name of the component.
	 */
	public abstract Type getComponentType();

}
