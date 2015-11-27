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

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.services.DataService;
import tobacco.core.services.Directory;
import tobacco.core.systems.AnimationSystem;
import tobacco.core.systems.CollisionDetectionSystem;
import tobacco.core.systems.CollisionHandlerSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.InputSystem;
import tobacco.core.systems.MovementResetSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.systems.TrajectorySystem;
import tobacco.core.systems.debugging.InfoSystem;
import tobacco.core.systems.main.AbstractMainSystem;
import tobacco.core.systems.main.SerialMainSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.collisions.BulletRemovalCollisionHandler;
import tobacco.game.test.collisions.DamageCollisionHandler;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.entities.EnemyEntityFactory;
import tobacco.game.test.entities.PlayerEntityFactory;
import tobacco.game.test.systems.EnemyControlSystem;
import tobacco.game.test.systems.GunSystem;
import tobacco.game.test.systems.HealthSystem;
import tobacco.game.test.systems.PlayerMovementBindingSystem;
import tobacco.game.test.util.HitCircleCollisionStrategy;
import tobacco.render.pc.input.PcInputListener;
import tobacco.render.pc.renderers.CustomGLEventListener;
import tobacco.render.pc.renderers.DebuggingRendererDecorator;
import tobacco.render.pc.renderers.NewtGLEventListener;
import tobacco.render.pc.renderers.LegacyRenderer;
import static tobacco.render.pc.input.PcInputCode.*;

public class ManualLoader implements Loader {

	@Override
	public AbstractMainSystem loadMainSystem(Entity root) {
		AbstractMainSystem main = new SerialMainSystem();
		
		// Load rendering
		DebuggingRendererDecorator debugging = new DebuggingRendererDecorator(new LegacyRenderer());
		CustomGLEventListener customGLEL = new NewtGLEventListener("The Game", debugging);
		customGLEL.addListener(new PcInputListener(root));
		Directory.setDebuggingService(debugging);
		
		// Set up collision handling
		CollisionHandlerSystem colHandlerSys = new CollisionHandlerSystem();
		colHandlerSys.addHandler(new DamageCollisionHandler());
		colHandlerSys.addHandler(new BulletRemovalCollisionHandler());
		
		// Load systems
		main.add(new InfoSystem());
		main.add(new TrajectorySystem());
		main.add(new MovementSystem());
		main.add(new PlayerMovementBindingSystem());
		main.add(new MovementResetSystem());
		main.add(new CollisionDetectionSystem(root, HitCircleCollisionStrategy.getSingleton()));
		main.add(colHandlerSys);
		main.add(new EnemyControlSystem());
		main.add(new GunSystem());
		main.add(new HealthSystem());
		main.add(new TimerSystem());
		main.add(new EntityRemovalSystem());
		main.add(new InputSystem());
		main.add(new AnimationSystem());

		DataService dataSrv = Directory.getDataService();
		dataSrv.setMainSystem(main);
		
		return main;
	}

	@Override
	public void loadEntityTree() {
		
		TextureComponent playerTexture = new TextureComponent("/tobacco/game/test/textures/reimusprite.png", 128, 48, 4, 1, 4);
		PlayerEntityFactory pef = new PlayerEntityFactory(playerTexture, new Vector2D(30, 44));
		pef.setMovementKeys(KEY_UP, KEY_DOWN, KEY_LEFT, KEY_RIGHT);
		pef.setActionKeys(KEY_Z, KEY_X, KEY_SHIFT, KEY_ESCAPE);
		
		ContainerComponent rootContainer = ((ContainerComponent) Directory.getEntityService().getRoot().get(GameComponent.CONTAINER_C));
		rootContainer.addChild(pef.create());
		TextureComponent enemyTexture = new TextureComponent("/tobacco/game/test/textures/fairy_blue.png", 26, 28);
		EnemyEntityFactory eeFactory = new EnemyEntityFactory(enemyTexture);
		rootContainer.addChild(eeFactory.create());
		rootContainer.addChild(eeFactory.create());

	}
}
