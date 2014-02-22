package tobacco.core.util;

import tobacco.core.entities.Entity;

public interface Action {

	public void process(RawInputElement rawIn, Entity entity, Entity entity2);

	public RawInputElement getKey();
}
