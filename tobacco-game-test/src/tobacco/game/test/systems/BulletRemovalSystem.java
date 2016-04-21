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
package tobacco.game.test.systems;

import tobacco.core.components.PositionComponent;
import tobacco.core.components.RemoveComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.systems.AbstractTypedSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;

/**
 * This system removes off-screen bullets
 * @author nirei
 */
public class BulletRemovalSystem extends AbstractTypedSystem {
	
	private static final Type requiredComps[] = {GameComponent.POSITION_C};
	private Vector2D halfSides;
	private Vector2D center;

	public BulletRemovalSystem(Vector2D center, Vector2D halfSides) {
		super(requiredComps,GameComponent.BULLET_C);
		this.center = center;
		this.halfSides = halfSides;
	}

	@Override
	public void process(Entity e, long delta) {
		if(qualifies(e)) {
			PositionComponent posComp = (PositionComponent) e.get(GameComponent.POSITION_C);
			if(!posComp.getPosition().isInsideArea(center, halfSides)) {
				e.add(new RemoveComponent());
			}
		}
	}

	@Override
	public void setUp() {}

	@Override
	public void tearDown() {}

}
