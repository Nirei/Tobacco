package tobacco.core.services;

import tobacco.core.components.Entity;

// TODO: Make every access to root go through here
public interface DataService {

	public Entity getRoot();
	public void setRoot(Entity root);
}
