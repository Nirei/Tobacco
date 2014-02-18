package tobacco.core.loader;

import java.util.List;

import tobacco.core.entities.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.core.systems.MainSystem;

public class ManualLoader implements Loader {
	
	List<EngineSystem> systems;
	Entity root;
	
	public ManualLoader(List<EngineSystem> _systems, Entity _root) {
		systems = _systems;
		root = _root;
	}

	@Override
	public MainSystem loadMainSystem() {
		MainSystem main = new MainSystem();
		for(EngineSystem s : systems)
			main.addSystem(s);
		return main;
	}

	@Override
	public Entity loadRootEntity() {
		return root;
	}

}
