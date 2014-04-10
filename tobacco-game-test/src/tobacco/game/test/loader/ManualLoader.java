package tobacco.game.test.loader;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MainSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.PlayerControlSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.systems.GunSystem;
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
		systems.add(new GunSystem());
		systems.add(new TimerSystem());
		systems.add(new EntityRemovalSystem());
		systems.add(new PlayerControlSystem());
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
	public Entity loadRootEntity() {
		Entity root, player;
		
		root = new Entity();
		root.putComponent(new DebuggingComponent());
		root.putComponent(new ContainerComponent());

		/* Player */
		player = new Entity();
		player.putComponent(new DebuggingComponent());
		player.putComponent(new DrawableComponent(null, new Vector2D(32f, 32f)));
		player.putComponent(new PositionComponent(new Vector2D(100f, 100f)));
		
		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);

		playerComp.put(new InputEvent(KEY_UP, TYPE_PRESS), up);
		playerComp.put(new InputEvent(KEY_DOWN, TYPE_PRESS), down);
		playerComp.put(new InputEvent(KEY_LEFT, TYPE_PRESS), left);
		playerComp.put(new InputEvent(KEY_RIGHT, TYPE_PRESS), right);
		playerComp.put(new InputEvent(KEY_UP, TYPE_RELEASE), down);
		playerComp.put(new InputEvent(KEY_DOWN, TYPE_RELEASE), up);
		playerComp.put(new InputEvent(KEY_LEFT, TYPE_RELEASE), right);
		playerComp.put(new InputEvent(KEY_RIGHT, TYPE_RELEASE), left);

		playerComp.put(new InputEvent(KEY_SPACE, TYPE_HOLD), new Command() {
			@Override public void execute(Entity rootEntity, Entity entity) {
				((GunComponent) entity.getComponent(GameComponent.GUN_C)).setShooting(true);
			}
		});
		
		player.putComponent(playerComp);
		
		GunComponent gunComponent = new GunComponent();
		gunComponent.setBulletDirection(new Vector2D(0f, 1f));
		gunComponent.setBulletSize(new Vector2D(10f, 10f));
		gunComponent.setBulletSpeed(2000f);
		
		player.putComponent(gunComponent);

		player.putComponent(new MovementComponent(800f));
		((ContainerComponent) root.getComponent(GameComponent.CONTAINER_C)).addChild(player);

		return root;
	}

}
