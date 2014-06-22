package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;

/**
 * This system checks which events are player entities listening for keystrokes
 * then asks the KeymapComponent if those events have been received and subsequently
 * executes the associated Commands.  
 * @author nirei
 *
 */
public class InputSystem extends AbstractTreeSystem {

	private final static String[] requiredComponents = {Component.PLAYER_C};

	private KeymapComponent keyMap;
	
	public InputSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if(qualifies(entity)) {
			PlayerComponent playerComp = (PlayerComponent) entity.getComponent(Component.PLAYER_C);

			for(InputEvent event : playerComp) {
				Command command = playerComp.get(event);
				switch (event.getInputType()) {
				case TYPE_HOLD:
					if(keyMap.isPressed(event.getInputCode()))
						command.execute(getRootEntity(), entity);
					break;
				case TYPE_PRESS:
					if(keyMap.wasPressed(event.getInputCode()))
						command.execute(getRootEntity(), entity);
					break;
				case TYPE_RELEASE:
					if(keyMap.wasReleased(event.getInputCode()))
						command.execute(getRootEntity(), entity);
					break;
				}
			}
		}
		return null;
	}

	@Override
	public void setUp() {
		keyMap = (KeymapComponent) getRootEntity().getComponent(Component.KEYMAP_C);
	}

	@Override
	public void tearDown() {
		keyMap.clear();		
	}

}
