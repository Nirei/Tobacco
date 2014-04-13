package tobacco.core.systems;

import tobacco.core.components.Entity;

public abstract class InputSystem implements EngineSystem {

	@Override abstract public void work(Entity entity);

}
