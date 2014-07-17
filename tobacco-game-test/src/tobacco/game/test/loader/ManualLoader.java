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
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.loader.Loader;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MainSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.InputSystem;
import tobacco.core.systems.MovementResetSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.systems.TrajectorySystem;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.entities.EnemyEntityFactory;
import tobacco.game.test.systems.GunSystem;
import tobacco.game.test.systems.HealthSystem;
import tobacco.game.test.util.BulletData;
import tobacco.render.pc.input.PcInputListener;
import tobacco.render.pc.renderers.AbstractRenderer;
import tobacco.render.pc.systems.PcRenderSystem;
import static tobacco.render.pc.input.PcInputCode.*;
import static tobacco.core.util.InputType.*;

public class ManualLoader implements Loader {

	@Override
	public MainSystem loadMainSystem(Entity root) {
		// TODO: Window should have an entity on its own which holds window info
		// so when prs loads, it reads from that entity.
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		PcRenderSystem prs = new PcRenderSystem(root);
		// TODO: Listener adds itself but this looks kinda ugly
		new PcInputListener(root, (AbstractRenderer) prs.getRenderer());
		systems.add(new InfoSystem());
		systems.add(new MovementSystem());
		systems.add(new MovementResetSystem());
		systems.add(new GunSystem());
		systems.add(new HealthSystem());
		systems.add(new TimerSystem());
		systems.add(new EntityRemovalSystem());
		systems.add(new TrajectorySystem());
		systems.add(new InputSystem());
		systems.add(prs);

		MainSystem main = new MainSystem();
		for (EngineSystem s : systems)
			main.addSystem(s);
		return main;
	}

	private Command moveCommand(final float x, final float y) {
		return new Command() {

			@Override
			public void execute(Entity rootEntity, Entity entity) {
				if (entity.has(Component.MOVEMENT_C)) {
					Vector2D direction;
					MovementComponent movComp = (MovementComponent) entity.getComponent(Component.MOVEMENT_C);
					direction = Vector2D.sum(movComp.getDirection(), new Vector2D(x, y));
					movComp.setDirection(direction);
				}
			}
		};
	}

	@Override
	public Entity loadEntityTree() {
		Entity root, player;

		root = new Entity();
		root.putComponent(new DebuggingComponent());
		root.putComponent(new ContainerComponent());

		/* Player */
		player = new Entity();
		player.putComponent(new DebuggingComponent());
		player.putComponent(new DrawableComponent("/tobacco/game/test/textures/reimuholder.png", new Vector2D(32f, 48f)));
		player.putComponent(new PositionComponent(new Vector2D(0f, -200f)));

		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);
		Command suicide = new Command() {

			@Override
			public void execute(Entity rootEntity, Entity entity) {
				((HealthComponent) entity.getComponent(GameComponent.HEALTH_C)).setHealth(0f);
			}
		};

		playerComp.put(new InputEvent(KEY_UP, TYPE_HOLD), up);
		playerComp.put(new InputEvent(KEY_DOWN, TYPE_HOLD), down);
		playerComp.put(new InputEvent(KEY_LEFT, TYPE_HOLD), left);
		playerComp.put(new InputEvent(KEY_RIGHT, TYPE_HOLD), right);
		playerComp.put(new InputEvent(KEY_ESCAPE, TYPE_RELEASE), suicide);

		playerComp.put(new InputEvent(KEY_SPACE, TYPE_PRESS), new Command() {
			@Override
			public void execute(Entity rootEntity, Entity entity) {
				((GunComponent) entity.getComponent(GameComponent.GUN_C)).setShooting(true);
			}
		});

		playerComp.put(new InputEvent(KEY_SPACE, TYPE_RELEASE), new Command() {
			@Override
			public void execute(Entity rootEntity, Entity entity) {
				((GunComponent) entity.getComponent(GameComponent.GUN_C)).setShooting(false);
			}
		});

		player.putComponent(playerComp);

		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		BulletData bullet = new BulletData("/tobacco/game/test/textures/reimubullet.png", new Vector2D(52f, 12f));

		BulletComponent bulletComp1 = new BulletComponent(bullet);
		bulletComp1.setBulletDirection(new Vector2D(0f, 1f));
		bulletComp1.setBulletSpeed(2000f);
		BulletComponent bulletComp2 = new BulletComponent(bullet);
		bulletComp2.setBulletDirection(new Vector2D(1f, 5f));
		bulletComp2.setBulletSpeed(2000f);
		BulletComponent bulletComp3 = new BulletComponent(bullet);
		bulletComp3.setBulletDirection(new Vector2D(-1f, 5f));
		bulletComp3.setBulletSpeed(2000f);

		Entity bullet1 = new Entity();
		bullet1.putComponent(bulletComp1);
		Entity bullet2 = new Entity();
		bullet2.putComponent(bulletComp2);
		Entity bullet3 = new Entity();
		bullet3.putComponent(bulletComp3);

		containerComponent.addChild(bullet1);
		containerComponent.addChild(bullet2);
		containerComponent.addChild(bullet3);

		player.putComponent(gunComponent);
		player.putComponent(containerComponent);
		player.putComponent(new MovementComponent(500f));
		player.putComponent(new HealthComponent(100f));

		ContainerComponent rootContainer = (ContainerComponent) root.getComponent(GameComponent.CONTAINER_C);
		rootContainer.addChild(player);
		EnemyEntityFactory eeFactory = new EnemyEntityFactory("/tobacco/game/test/textures/fairy_blue.png", new Vector2D(26f, 28f));
		rootContainer.addChild(eeFactory.create());
		
		Entity debugAxisX = new Entity();
		Entity debugAxisY = new Entity();
		
		DrawableComponent drawComp = new DrawableComponent("/tobacco/game/test/textures/white_pixel.png", new Vector2D(500f,1f));
		PositionComponent posComp = new PositionComponent(Vector2D.ZERO, 1f);
		debugAxisX.putComponent(posComp);
		debugAxisX.putComponent(drawComp);
		
		drawComp = new DrawableComponent("/tobacco/game/test/textures/white_pixel.png", new Vector2D(1f, 1000f));
		posComp = new PositionComponent(Vector2D.ZERO, 1f);
		debugAxisY.putComponent(posComp);
		debugAxisY.putComponent(drawComp);
		
		rootContainer.addChild(debugAxisX);
		rootContainer.addChild(debugAxisY);

		return root;
	}

}
