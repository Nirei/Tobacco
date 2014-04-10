package tobacco.core.util;

import tobacco.core.entities.Entity;

public interface Command {
	public void execute(Entity rootEntity, Entity entity);
}
