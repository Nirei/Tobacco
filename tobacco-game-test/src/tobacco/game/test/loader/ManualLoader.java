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
package tobacco.game.test.loader;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.loader.Loader;
import tobacco.core.services.DefaultDataService;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractMainSystem;
import tobacco.core.systems.CollisionSystem;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.InputSystem;
import tobacco.core.systems.MovementResetSystem;
import tobacco.core.systems.ThreadedMainSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.systems.TrajectorySystem;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Line2D;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;
import tobacco.game.test.entities.EnemyEntityFactory;
import tobacco.game.test.systems.DamageSystem;
import tobacco.game.test.systems.GunSystem;
import tobacco.game.test.systems.HealthSystem;
import tobacco.game.test.util.HitCircleCollisionStrategy;
import tobacco.render.pc.components.TextureComponent;
import tobacco.render.pc.input.PcInputListener;
import tobacco.render.pc.renderers.CustomGLEventListener;
import tobacco.render.pc.renderers.DebuggingRendererDecorator;
import tobacco.render.pc.renderers.NewtGLEventListener;
import tobacco.render.pc.renderers.LegacyRenderer;
import static tobacco.render.pc.input.PcInputCode.*;
import static tobacco.core.util.InputType.*;

public class ManualLoader implements Loader {

	@Override
	public AbstractMainSystem loadMainSystem(Entity root) {
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		
		// Load rendering
		DebuggingRendererDecorator debugging = new DebuggingRendererDecorator(new LegacyRenderer());
		CustomGLEventListener customGLEL = new NewtGLEventListener("The Game", debugging);
		customGLEL.addListener(new PcInputListener(root));
		Directory.setDebuggingService(debugging);

		// Test debugging
		Line2D axisX = new Line2D(new Vector2D(-1000f, 0f), new Vector2D(1000f, 0f));
		Line2D axisY = new Line2D(new Vector2D(0f, -1000f), new Vector2D(0f, 1000f));
		Directory.getDebuggingService().displayVector("axisX", axisX);
		Directory.getDebuggingService().displayVector("axisY", axisY);
		
		// Load systems
		systems.add(new InfoSystem());
		systems.add(new MovementSystem());
		systems.add(new MovementResetSystem());
		systems.add(new CollisionSystem(root, HitCircleCollisionStrategy.getSingleton()));
		systems.add(new GunSystem());
		systems.add(new DamageSystem());
		systems.add(new HealthSystem());
		systems.add(new TimerSystem());
		systems.add(new EntityRemovalSystem());
		systems.add(new TrajectorySystem());
		systems.add(new InputSystem());

		AbstractMainSystem main = new ThreadedMainSystem();
		for (EngineSystem s : systems)
			main.addSystem(s);
		return main;
	}

	private Command moveCommand(final float x, final float y) {
		return (rootEntity, entity) -> {
				if (entity.has(Component.MOVEMENT_C)) {
					Vector2D direction;
					MovementComponent movComp = (MovementComponent) entity.get(Component.MOVEMENT_C);
					direction = Vector2D.sum(movComp.getDirection(), new Vector2D(x, y));
					movComp.setDirection(direction);
				}
			};
	}

	@Override
	public void loadEntityTree() {
		Entity root, player;

		/* Root */
		ContainerComponent rootContainer = new ContainerComponent();
		root = new Entity();
		root.add(new DebuggingComponent());
		root.add(new ScreenComponent(new Vector2D(480,640)));
		root.add(rootContainer);

		/* Player */
		player = new Entity();
//		player.add(new DebuggingComponent());
		player.add(new TextureComponent("/tobacco/game/test/textures/reimuholder.png"));
		player.add(new SizeComponent(new Vector2D(32f, 48f)));
		player.add(new PositionComponent(new Vector2D(0f, -200f)));

		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);
		Command suicide = (rootEntity, entity) -> ((HealthComponent) entity.get(GameComponent.HEALTH_C)).setHealth(0f);

		playerComp.put(new InputEvent(KEY_UP, TYPE_HOLD), up);
		playerComp.put(new InputEvent(KEY_DOWN, TYPE_HOLD), down);
		playerComp.put(new InputEvent(KEY_LEFT, TYPE_HOLD), left);
		playerComp.put(new InputEvent(KEY_RIGHT, TYPE_HOLD), right);
		playerComp.put(new InputEvent(KEY_ESCAPE, TYPE_RELEASE), suicide);

		playerComp.put(new InputEvent(KEY_Z, TYPE_PRESS),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(true));

		playerComp.put(new InputEvent(KEY_Z, TYPE_RELEASE),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(false));
		
		player.add(playerComp);

		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		SizeComponent bSizeComp = new SizeComponent(new Vector2D(52f, 12f));
		
		Entity bullet1 = new Entity();
		BulletComponent bulletComp1 = new BulletComponent("/tobacco/game/test/textures/reimubullet.png", 150, 2000f);
		bullet1.add(bulletComp1);
		bullet1.add(new DirectionComponent(new Vector2D(0f, 1f)));
		bullet1.add(bSizeComp);
		bullet1.add(new DamageComponent(50f));

		Entity bullet2 = new Entity();
		BulletComponent bulletComp2 = new BulletComponent("/tobacco/game/test/textures/reimubullet.png", 150, 2000f);
		bullet2.add(bulletComp2);
		bullet2.add(new DirectionComponent(new Vector2D(1f, 5f)));
		bullet2.add(bSizeComp);
		bullet2.add(new DamageComponent(50f));

		Entity bullet3 = new Entity();
		BulletComponent bulletComp3 = new BulletComponent("/tobacco/game/test/textures/reimubullet.png", 150, 2000f);
		bullet3.add(bulletComp3);
		bullet3.add(new DirectionComponent(new Vector2D(-1f, 5f)));
		bullet3.add(bSizeComp);
		bullet3.add(new DamageComponent(50f));

		containerComponent.addChild(bullet1);
		containerComponent.addChild(bullet2);
		containerComponent.addChild(bullet3);

		player.add(gunComponent);
		player.add(containerComponent);
		player.add(new MovementComponent(500f));
		player.add(new HealthComponent(100f));
		player.add(new SolidityComponent(10f));
		player.add(new TeamComponent("PLAYER"));

		rootContainer.addChild(player);
		EnemyEntityFactory eeFactory = new EnemyEntityFactory("/tobacco/game/test/textures/fairy_blue.png", new Vector2D(26f, 28f));
		rootContainer.addChild(eeFactory.create());

		Directory.setDataService(new DefaultDataService(root));
	}
}
