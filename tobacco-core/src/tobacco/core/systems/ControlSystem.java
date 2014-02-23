package tobacco.core.systems;

import tobacco.core.actions.Action;
import tobacco.core.actions.Command;
import tobacco.core.components.CommandBufferComponent;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.ControlableComponent;
import tobacco.core.entities.Entity;

public class ControlSystem implements EngineSystem {
	
	private Entity rootEntity;

	private void processTree(Entity entity) {
		if(entity.contains(Component.CONTROLABLE_C)) {
			if(entity.contains(Component.COMMAND_BUFFER_C)) {
				ControlableComponent ctrlComp = (ControlableComponent) entity.getComponent(Component.CONTROLABLE_C);
				CommandBufferComponent cbComp = (CommandBufferComponent) entity.getComponent(Component.COMMAND_BUFFER_C);
				Command call;
				synchronized(cbComp) {
					while((call = cbComp.poll()) != null) {
						Action action;
						if((action = ctrlComp.get(call.getCommand())) != null)
							action.process(call, rootEntity, entity);
					}
				}
			}
		} if(entity.contains(Component.CONTAINER_C)) {
			for(Entity child : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				processTree(child);
			}
		}
	}

	@Override
	public void work(Entity entity) {
		rootEntity = entity;
		processTree(entity);
	}
	

}
