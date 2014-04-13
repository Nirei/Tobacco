package tobacco.core.util;

import tobacco.core.components.Entity;

public interface Command {
	public void execute(Entity rootEntity, Entity entity);
}
