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
package tobacco.game.test.entities;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;

public class BulletEntityFactory {

	public Entity create(Vector2D pos, BulletComponent b) {
		Entity entity = new Entity();
		TextureComponent textureComp = new TextureComponent(b.getBulletData().getTexture());
		SizeComponent sizeComp = new SizeComponent(b.getBulletData().getSize());
		entity.put(textureComp);
		entity.put(sizeComp);
		entity.put(new PositionComponent(pos));
		Vector2D dir = b.getBulletDirection();
		entity.put(new MovementComponent(dir, b.getBulletSpeed()));
		entity.put(new RotationComponent(90f + Vector2D.angle(Vector2D.VERTICAL, dir).getDegrees()));
		entity.put(new DebuggingComponent());
		entity.put(new DurationComponent(1000));

		return entity;
	}

}
