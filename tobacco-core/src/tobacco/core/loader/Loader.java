package tobacco.core.loader;

import tobacco.core.entities.Entity;
import tobacco.core.systems.MainSystem;

public interface Loader {
	public MainSystem loadMainSystem();
	public Entity loadRootEntity();
}
