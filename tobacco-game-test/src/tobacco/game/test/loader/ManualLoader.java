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
import tobacco.core.systems.MainSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.PlayerInputSystem;
import tobacco.core.systems.PlayerMovementSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.systems.GunSystem;
import tobacco.game.test.util.BulletData;
import tobacco.render.pc.systems.PcInputSystem;
import tobacco.render.pc.systems.PcRenderSystem;
import static tobacco.render.pc.util.PcInputCode.*;
import static tobacco.core.util.InputType.*;

public class ManualLoader implements Loader {
	
	@Override
	public MainSystem loadMainSystem(Entity root) {		
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		PcRenderSystem prs = new PcRenderSystem(root);
		PcInputSystem pis = new PcInputSystem(root, prs);
		//systems.add(new InfoSystem());
		systems.add(new MovementSystem());
		systems.add(new PlayerMovementSystem());
		systems.add(new GunSystem());
		systems.add(new TimerSystem());
		systems.add(new EntityRemovalSystem());
		systems.add(new PlayerInputSystem());
		systems.add(prs);
		systems.add(pis);
		
		MainSystem main = new MainSystem();
		for(EngineSystem s : systems)
			main.addSystem(s);
		return main;
	}
	
	private Command moveCommand(final float x, final float y) {
		return new Command() {
			
			@Override
			public void execute(Entity rootEntity, Entity entity) {
				if(entity.contains(Component.MOVEMENT_C)) {
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
		player.putComponent(new PositionComponent(new Vector2D(100f, 100f)));
		
		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);

		playerComp.put(new InputEvent(KEY_UP, TYPE_HOLD), up);
		playerComp.put(new InputEvent(KEY_DOWN, TYPE_HOLD), down);
		playerComp.put(new InputEvent(KEY_LEFT, TYPE_HOLD), left);
		playerComp.put(new InputEvent(KEY_RIGHT, TYPE_HOLD), right);

		playerComp.put(new InputEvent(KEY_SPACE, TYPE_PRESS), new Command() {
			@Override public void execute(Entity rootEntity, Entity entity) {
				((GunComponent) entity.getComponent(GameComponent.GUN_C)).setShooting(true);
			}
		});
		
		playerComp.put(new InputEvent(KEY_SPACE, TYPE_RELEASE), new Command() {
			@Override public void execute(Entity rootEntity, Entity entity) {
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
		((ContainerComponent) root.getComponent(GameComponent.CONTAINER_C)).addChild(player);

		return root;
	}

}
