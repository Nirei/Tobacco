package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;

public class InfoSystem implements EngineSystem {

	@Override
	public void work(Entity entity) {
		if(entity.contains(Component.DEBUGGING_C));
		for(Component c : entity) {
			c.toString();
		}
	}
}
