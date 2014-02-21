package tobacco.core.systems;

import tobacco.core.components.KeyMapComponent;
import tobacco.core.entities.Entity;

public abstract class InputSystem implements EngineSystem {

	@Override
	public void work(Entity entity) {}

	abstract public KeyMapComponent getKeyMap();

}
