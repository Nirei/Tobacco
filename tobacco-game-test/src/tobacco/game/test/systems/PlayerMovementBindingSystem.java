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
import tobacco.core.components.ScreenComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractTypedSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;

/**
 * This system prevents the player from being moved out of the
 * screen by querying the root Entity for the screen size, then
 * checking if any player entities have been moved outside its
 * boundary. This should be run right after the movement system
 * has performed it's job to be effective.
 * @author nirei
 *
 */
public class PlayerMovementBindingSystem extends AbstractTypedSystem {

	private static final Type[] requiredComponents = {GameComponent.POSITION_C, GameComponent.SIZE_C};
	private float screenMinX, screenMaxX, screenMinY, screenMaxY;
	
	public PlayerMovementBindingSystem() {
		super(requiredComponents, GameComponent.PLAYER_C);
	}

	@Override
	public void process(Entity entity, long delta) {
		if(qualifies(entity)) {
			PositionComponent posComp = (PositionComponent) entity.get(GameComponent.POSITION_C);
			SizeComponent sizeComp = (SizeComponent) entity.get(GameComponent.SIZE_C);
			Vector2D pos = posComp.getPosition();
			Vector2D size = sizeComp.getSize();
			float posX = pos.getX();
			float posY = pos.getY();
			float halfSizeX = size.getX() / 2f;
			float halfSizeY = size.getY() / 2f;
			
			if(posX - halfSizeX < screenMinX) {
				posX = screenMinX + halfSizeX;
			} else if(posX + halfSizeX > screenMaxX) {
				posX = screenMaxX - halfSizeX;
			}
			
			if(posY - halfSizeY < screenMinY) {
				posY = screenMinY + halfSizeY;
			} else if(posY + halfSizeY > screenMaxY) {
				posY = screenMaxY - halfSizeY;
			}
			
			posComp.setPosition(new Vector2D(posX, posY));
		}
	}

	@Override
	public void setUp() {
		// FIXME Big warning: We're assuming the screen is always
		// centered around (0,0).
		Entity root = Directory.getEntityService().getRoot();
		ScreenComponent scrComp = (ScreenComponent) root.get(GameComponent.SCREEN_C);
		Vector2D scrSize = scrComp.getScreenSize();
		float halfX = scrSize.getX()/2;
		float halfY = scrSize.getY()/2;
		screenMinX = -halfX;
		screenMaxX = halfX;
		screenMinY = -halfY;
		screenMaxY = halfY;
	}

	@Override
	public void tearDown() {}

}
