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

import tobacco.core.collision.CollisionStrategy;
import tobacco.core.collision.QuadTree;
import tobacco.core.components.CollisionMapComponent;
import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.components.Type;
import tobacco.core.util.Vector2D;

/* Writes on its own cMapComp, similarly to what the InputListener
 * does with keyMapComp. In the future it may be positive to respect
 * data access methods and read cMapComp from the root entity everytime
 * instead of keeping a reference, which could accidentally become unlinked
 * from root.
 */
public class CollisionSystem extends AbstractListSystem {

	private static final Type[] requiredComponents = {Component.SOLIDITY_C, Component.POSITION_C};

	private final QuadTree<Entity> cqt;
	private final CollisionStrategy cStrategy;
	private final CollisionMapComponent cMapComp = new CollisionMapComponent();

	private Set<Entity> checked = new HashSet<Entity>();

	public CollisionSystem(Entity root, CollisionStrategy cStrategy) {
		super(requiredComponents);

		this.cStrategy = cStrategy; 
		
		Vector2D screenSize = ((ScreenComponent) root.get(Component.SCREEN_C)).getScreenSize();
		cqt = new QuadTree<Entity>(Vector2D.ZERO, screenSize.scale(0.55f), 4, 6);
		
		root.add(cMapComp);
	}
	
	private void writeCollision(Entity e1, Entity e2) {
		cMapComp.addCollision(e1, e2);
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity)) {
			Vector2D pos = ((PositionComponent) entity.get(Component.POSITION_C)).getPosition();
			for(Entity e : cqt.query(pos)) {
				if(!checked.contains(e) && !e.equals(entity) && cStrategy.collides(e, entity)) {
					writeCollision(e, entity);
				}
			}
			checked.add(entity);
		}
	}

	@Override
	public void setUp() {
		cMapComp.clear();
		for(Entity e : Entity.getEntityList()) {
			if(qualifies(e)) {
				Vector2D pos = ((PositionComponent) e.get(Component.POSITION_C)).getPosition();
				cqt.insert(e, pos);
			}
		}
	}

	@Override
	public void tearDown() {
		cqt.clear();
		checked.clear();
	}

}
