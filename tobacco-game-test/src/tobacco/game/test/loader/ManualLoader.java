package tobacco.game.test.loader;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.CommandBufferComponent;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.ControlableComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.systems.CommandSystem;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.EntityRemovalSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MainSystem;
import tobacco.core.systems.MovementSystem;
import tobacco.core.systems.TimerSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.main.ActionMap;
import tobacco.render.pc.systems.PcInputSystem;
import tobacco.render.pc.systems.PcRenderSystem;

public class ManualLoader implements Loader {
	
	List<EngineSystem> systems;
	Entity root, player;

	@Override
	public MainSystem loadMainSystem() {
		
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		PcRenderSystem prs = new PcRenderSystem(root);
		PcInputSystem pis = new PcInputSystem(player, prs);
		systems.add(prs);
		systems.add(pis);
		systems.add(new InfoSystem());
		systems.add(new CommandSystem());
		systems.add(new MovementSystem());
		systems.add(new TimerSystem());
		systems.add(new EntityRemovalSystem());
		
		MainSystem main = new MainSystem();
		for(EngineSystem s : systems)
			main.addSystem(s);
		return main;
	}

	@Override
	public Entity loadRootEntity() {
		root = new Entity();
		root.putComponent(new DebuggingComponent());
		root.putComponent(new ContainerComponent());
		player = new Entity();
		player.putComponent(new DebuggingComponent());
		player.putComponent(new DrawableComponent(null, new Vector2D(32f, 32f)));
		player.putComponent(new PositionComponent(new Vector2D(100f, 100f)));
		player.putComponent(new CommandBufferComponent());
		player.putComponent(new ControlableComponent());
		
		GunComponent gunComponent = new GunComponent();
		gunComponent.setBulletDirection(new Vector2D(0f, 1f));
		gunComponent.setBulletSize(new Vector2D(10f, 10f));
		gunComponent.setBulletSpeed(2000f);
		
		player.putComponent(gunComponent);
		
		ActionMap.addLogic(player);

		player.putComponent(new MovementComponent(400f));
		((ContainerComponent) root.getComponent(Component.CONTAINER_C)).addChild(player);
		
		return root;
	}

}
