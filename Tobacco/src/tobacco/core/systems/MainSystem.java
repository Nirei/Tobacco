package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

public class MainSystem implements EngineSystem {
	
	private List<EngineSystem> systemList = new ArrayList<EngineSystem>();

	@Override
	public void work(Entity entity) {
		for(EngineSystem s : systemList) {
			s.work(entity);
		}

		if(entity.contains(Component.CONTAINER_C)) {
			for(Entity child : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				this.work(child);
			}
		}
	}
	
	public void addSystem(EngineSystem system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
