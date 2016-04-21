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
package tobacco.core.collision;

import tobacco.core.entities.Entity;

/**
 * Holds the collision checking code for any two given entities
 * @author nirei
 */
public interface CollisionStrategy {
	
	/**
	 * Check if two entities collide.
	 * @param e1 - First entity
	 * @param e2 - Second entity
	 * @return Truth value of a collision happening.
	 */
	public boolean collides(Entity e1, Entity e2);

}
