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

import tobacco.core.collision.CollisionQuadTree2D;
import tobacco.core.components.Entity;

public class CollisionSystem extends AbstractListSystem {

	private static final String[] requiredComponents = {};
	private CollisionQuadTree2D cqt;

	
	public CollisionSystem(Entity screen) {
		super(requiredComponents);
		//cqt = new CollisionQuadTree2D();
	}

	@Override
	public void process(Entity entity) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void setUp() {
		for(Entity e : Entity.getEntityList()) {
			
		}
	}

	@Override
	public void tearDown() {
		// TODO Apéndice de método generado automáticamente
		
	}

}
