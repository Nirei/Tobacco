package tobacco.core.entities;

import java.util.Collection;

import tobacco.core.components.Component;

public class EntityFactory {

	public EntityFactory() {}
	
	public Entity create() {
		return new Entity();
	}
	
	public Entity create(Collection<Component> comps) {
		Entity ent = create();
		for(Component c : comps) {
			ent.add(c);
		}
		return ent;
	}
}