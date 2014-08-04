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

import java.util.HashSet;
import java.util.Set;

import tobacco.core.collision.CollisionQuadTree2D;
import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.util.Vector2D;

// TODO: Dirty as hell
public class CollisionSystem extends AbstractListSystem {

	private static final String[] requiredComponents = {Component.SOLIDITY_C, Component.POSITION_C};
	private CollisionQuadTree2D cqt;
	Set<Entity> checked = new HashSet<Entity>();

	
	public CollisionSystem(Entity root) {
		super(requiredComponents);
		Vector2D screenSize = ((ScreenComponent) root.get(Component.SCREEN_C)).getScreenSize();
		cqt = new CollisionQuadTree2D(Vector2D.ZERO, screenSize.scale(0.55f), 4, 6);
	}
	
	private boolean collides(Entity e1, Entity e2) {
		return false;
	}
	
	private void writeCollision(Entity e1, Entity e2) {
		System.out.println(e1 + " collides with " + e2);
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity)) {
			Vector2D pos = ((PositionComponent) entity.get(Component.POSITION_C)).getPosition();
			for(Entity e : cqt.query(pos)) {
				if(!checked.contains(e) && !e.equals(entity) && collides(e, entity)) {
					System.out.println(e + " is around " + entity);
					writeCollision(e, entity);
				}
			}
			checked.add(entity);
		}
	}

	@Override
	public void setUp() {
		for(Entity e : Entity.getEntityList()) {
			if(qualifies(e))
				cqt.insert(e);
		}
	}

	@Override
	public void tearDown() {
		System.out.println(cqt.toString());
		cqt.clear();
		checked.clear();
	}

}
