/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.game.test.components;

import tobacco.core.components.Type;
import tobacco.render.pc.components.RendererComponent;

public interface GameComponent extends RendererComponent {

	public static final Type BULLET_C = new Type("BULLET_C", BulletComponent.class);
	public static final Type BULLET_DATA_C = new Type("BULLET_DATA_C", BulletDataComponent.class);
	public static final Type DAMAGE_C = new Type("DAMAGE_C", DamageComponent.class);
	public static final Type DIRECTION_C = new Type("DIRECTION_C", DirectionComponent.class);
	public static final Type ENEMY_C = new Type("ENEMY_C", EnemyComponent.class);
	public static final Type GUN_C = new Type("GUN_C", GunComponent.class);
	public static final Type HEALTH_C = new Type("HEALTH_C", HealthComponent.class);
	public static final Type TEAM_C = new Type("TEAM_C", TeamComponent.class);
}
