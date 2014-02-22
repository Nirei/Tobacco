package tobacco.game.test.main;

import java.util.ArrayList;
import java.util.List;




import tobacco.core.components.ContainerComponent;
import tobacco.core.components.ControlableComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.loader.ManualLoader;
import tobacco.core.systems.ControlSystem;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MainSystem;
import tobacco.core.util.Action;
import tobacco.core.util.Vector2D;
import tobacco.render.pc.systems.PcInputSystem;
import tobacco.render.pc.systems.PcRenderSystem;

public class Main {

	public static void main(String[] args) {
	

		Entity root = new Entity();
		root.putComponent(new DebuggingComponent());
		root.putComponent(new ContainerComponent());
		root.putComponent(new DrawableComponent(null, new Vector2D(32, 32)));
		PositionComponent position = new PositionComponent(new Vector2D(100, 100));
		root.putComponent(position);
		ControlableComponent ccomp = new ControlableComponent();
		for(Action a : ActionMap.actionList())
			ccomp.addAction(a);
		root.putComponent(ccomp);
		
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		systems.add(new InfoSystem());
		PcRenderSystem prs = new PcRenderSystem(root);
		PcInputSystem pis = new PcInputSystem(root,prs);
		systems.add(prs);
		systems.add(pis);
		systems.add(new ControlSystem(pis.getKeyMap()));
		

		Loader loader = new ManualLoader(systems, root);
		MainSystem mainSystem = loader.loadMainSystem();
		root = loader.loadRootEntity();
		
		while(true) {
			mainSystem.work(root);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
