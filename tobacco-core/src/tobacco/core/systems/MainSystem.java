package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.entities.Entity;

public class MainSystem implements EngineSystem {
	
	private List<EngineSystem> systemList = new ArrayList<EngineSystem>();

	@Override
	public void work(Entity entity) {
		for(EngineSystem s : systemList) {
			s.work(entity);
		}
	}
	
	public void addSystem(EngineSystem system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
