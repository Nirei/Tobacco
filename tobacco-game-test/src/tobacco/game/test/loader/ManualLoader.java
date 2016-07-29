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
package tobacco.game.test.loader;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.KeymapComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.DefaultEntityService;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityService;
import tobacco.core.game.DefaultGameService;
import tobacco.core.game.GameService;
import tobacco.core.game.GameState;
import tobacco.core.serialization.Loader;
import tobacco.core.services.RenderingService;
import tobacco.core.services.ConfigurationService;
import tobacco.core.services.Directory;
import tobacco.core.systems.AnimationSystem;
import tobacco.core.systems.CollisionDetectionSystem;
import tobacco.core.systems.CollisionHandlerSystem;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.InputSystem;
import tobacco.core.systems.MovementResetSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.systems.TrajectorySystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.collisions.BulletRemovalCollisionHandler;
import tobacco.game.test.collisions.DamageCollisionHandler;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.entities.EnemyEntityFactory;
import tobacco.game.test.entities.PlayerEntityFactory;
import tobacco.game.test.systems.BulletRemovalSystem;
import tobacco.game.test.systems.EnemyControlSystem;
import tobacco.game.test.systems.GunSystem;
import tobacco.game.test.systems.HealthSystem;
import tobacco.game.test.systems.PlayerMovementBindingSystem;
import tobacco.game.test.util.HitCircleCollisionStrategy;
import tobacco.render.pc.components.MouseComponent;
import tobacco.render.pc.components.ZIndexComponent;
import tobacco.render.pc.input.PcInputListener;
import tobacco.render.pc.renderers.CustomGLEventListener;
import tobacco.render.pc.renderers.DebuggingRendererDecorator;
import tobacco.render.pc.renderers.LegacyRenderer;
import tobacco.render.pc.renderers.NewtGLEventListener;

import static tobacco.render.pc.input.PcInputCode.*;

import java.util.ArrayList;
import java.util.List;

public class ManualLoader extends Loader {
	
	@Override
	public RenderingService loadRenderingService() {
		DebuggingRendererDecorator renderer = new DebuggingRendererDecorator(new LegacyRenderer());
		Directory.setDebuggingService(renderer);
		CustomGLEventListener renderServ = new NewtGLEventListener("The Game", renderer);
		KeymapComponent keyMapComp = (KeymapComponent) Directory.getEntityService().getRoot().get(GameComponent.KEYMAP_C);
		MouseComponent mouseComp = (MouseComponent) Directory.getEntityService().getRoot().get(GameComponent.MOUSE_C);
		renderServ.addListener(new PcInputListener(keyMapComp, mouseComp));
		return renderServ;
	}

	@Override
	public GameService loadGameService() {		
		GameService gameServ = new DefaultGameService();
		
		// Set up collision handling
		CollisionHandlerSystem colHandlerSys = new CollisionHandlerSystem();
		colHandlerSys.addHandler(new DamageCollisionHandler());
		colHandlerSys.addHandler(new BulletRemovalCollisionHandler());
		
		// Systems necessary in more than one state
		InputSystem inputSystem = new InputSystem();
		
		// Load NORMAL state systems
		List<EngineSystem> normalSystems = new ArrayList<>();
		normalSystems.add(new TrajectorySystem());
		normalSystems.add(new MovementSystem());
		normalSystems.add(new PlayerMovementBindingSystem());
		normalSystems.add(new MovementResetSystem());
		normalSystems.add(new CollisionDetectionSystem(HitCircleCollisionStrategy.getSingleton()));
		normalSystems.add(colHandlerSys);
		normalSystems.add(new EnemyControlSystem());
		normalSystems.add(new GunSystem());
		normalSystems.add(new HealthSystem());
		normalSystems.add(new TimerSystem());
		normalSystems.add(new BulletRemovalSystem(Vector2D.ZERO, new Vector2D(250,350)));
		normalSystems.add(new EntityRemovalSystem());
		normalSystems.add(inputSystem);
		normalSystems.add(new AnimationSystem());
		
		gameServ.setSystems(GameState.NORMAL, normalSystems);

		// Load PAUSED state systems
		List<EngineSystem> pausedSystems = new ArrayList<>();
		
		pausedSystems.add(inputSystem);
		
		gameServ.setSystems(GameState.PAUSED, pausedSystems);
		
		// Load MENU state systems
		List<EngineSystem> menuSystems = new ArrayList<>();

		menuSystems.add(inputSystem);
		
		gameServ.setSystems(GameState.MENU, menuSystems);

		gameServ.setState(GameState.MENU);
		return gameServ;
	}

	@Override
	public EntityService loadEntityService() {

		EntityService eServ = new DefaultEntityService();
		Entity root = eServ.create();
		eServ.setRoot(root);
		ContainerComponent rootContainer = new ContainerComponent();
		root.add(new ScreenComponent(new Vector2D(480,640)));
		root.add(new KeymapComponent());
		root.add(new MouseComponent());
		root.add(rootContainer);
		
		TextureComponent playerTexture = new TextureComponent("/tobacco/game/test/textures/reimusprite.png", 128, 48, 4, 1, 4);
		PlayerEntityFactory pef = new PlayerEntityFactory(eServ, playerTexture, new Vector2D(30, 44));
		pef.setMovementKeys(KEY_UP, KEY_DOWN, KEY_LEFT, KEY_RIGHT);
		pef.setActionKeys(KEY_Z, KEY_X, KEY_SHIFT, KEY_ESCAPE);
				
		rootContainer.addChild(pef.create());
		TextureComponent enemyTexture = new TextureComponent("/tobacco/game/test/textures/fairy_blue.png", 26, 28);
		EnemyEntityFactory eeFactory = new EnemyEntityFactory(eServ, enemyTexture);
		rootContainer.addChild(eeFactory.create());
		rootContainer.addChild(eeFactory.create());
		
		Entity background = eServ.create();
		background.add(new PositionComponent(Vector2D.ZERO));
		background.add(new TextureComponent("/tobacco/game/test/textures/bamboo.png", 480, 640));
		background.add(new ZIndexComponent(-10));
		rootContainer.addChild(background);

		return eServ;
	}

	@Override
	public ConfigurationService loadConfigurationService() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
}
