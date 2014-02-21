package tobacco.core.util;

import tobacco.core.entities.Entity;

public interface Action {

	public void process(RawInputElement rawIn, Entity entity);

	public RawInputElement getKey();

}
