package tobacco.core.actions;

import tobacco.core.entities.Entity;

public interface Action {

	public void process(Command call, Entity root, Entity entity);
}
