package tobacco.core.loader;

import tobacco.core.components.Entity;
import tobacco.core.systems.MainSystem;

public interface Loader {
	public MainSystem loadMainSystem(Entity root);
	public Entity loadEntityTree();
}
