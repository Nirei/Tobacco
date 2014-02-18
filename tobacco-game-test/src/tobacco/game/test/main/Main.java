package tobacco.game.test.main;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.entities.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.loader.ManualLoader;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.InfoSystem;
import tobacco.core.systems.MainSystem;

public class Main {

	public static void main(String[] args) {
		List<EngineSystem> systems = new ArrayList<EngineSystem>();
		systems.add(new InfoSystem());
		
		Entity root = new Entity();
		root.putComponent(new DebuggingComponent());
		root.putComponent(new ContainerComponent());
		
		Loader loader = new ManualLoader(systems, root);
		MainSystem mainSystem = loader.loadMainSystem();
		root = loader.loadRootEntity();
		
		while(true) {
			mainSystem.work(root);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
