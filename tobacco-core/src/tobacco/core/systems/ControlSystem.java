package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.ControlableComponent;
import tobacco.core.components.KeyMapComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Action;
import tobacco.core.util.RawInputElement;
public class ControlSystem implements EngineSystem {
	
	private KeyMapComponent keyMap;

	public ControlSystem(KeyMapComponent _keyMap) {
		keyMap = _keyMap;
	}

	private void processTree(Entity entity) {
		if(entity.contains(Component.CONTROLABLE_C)) {
			ControlableComponent ccomp = (ControlableComponent) entity.getComponent(Component.CONTROLABLE_C);
			for(RawInputElement rawIn : keyMap) {
				Action action;
				if((action = ccomp.getAction(rawIn)) != null) {
					action.process(rawIn, entity);
				}
			}
		} else if(entity.contains(Component.CONTAINER_C)) {
			for(Entity child : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				processTree(child);
			}
		}
	}

	@Override
	public void work(Entity entity) {
		processTree(entity);
		
		synchronized (keyMap) 
		{
			keyMap.clear();
		}
	}
	

}
