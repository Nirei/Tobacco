package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

public abstract class MainSystem implements System {
	
	private List<System> systemList = new ArrayList<System>();

	@Override
	public void work(Entity entity) {
		for(System s : systemList) {
			s.work(entity);
		}

		if(entity.getComponents().containsKey(Component.CONTAINER)) {
			for(Entity child : (ContainerComponent) entity.getComponents().get(Component.CONTAINER)) {
				this.work(child);
			}
		}
	}
	
	public void addSystem(System system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
