package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;

public class InfoSystem implements EngineSystem {

	@Override
	public void work(Entity entity) {
		if(entity.contains(Component.DEBUGGING_C));
		System.out.println(entity);
		for(Component c : entity) {
			System.out.println(c);
		}
	}
}
