package tobacco.core.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tobacco.core.components.Component;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.entities.RootEntityFactory;

public class DefaultEntityService implements EntityService {
	
	private Entity root = null;
	private Map<Long, Entity> entities = new HashMap<Long, Entity>();
	private EntityFactory eFactory = new EntityFactory();

	@Override
	public synchronized Entity getRoot() {
		if(root == null) {
			root = RootEntityFactory.create();
			root.add(new DebuggingComponent());
		}
		return root;
	}

	@Override
	public List<Entity> getEntityList() {
		return new ArrayList<Entity>(entities.values());
	}

	@Override
	public Entity create() {
		Entity created = eFactory.create();
		entities.put(created.getID(), created);
		return created;
	}

	@Override
	public Entity create(Collection<Component> components) {
		return eFactory.create(components);
	}

	@Override
	public void remove(Entity entity) {
		entities.remove(entity.getID());
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entity> findAll(Type type) {
		List<Entity> all = new ArrayList<>();
		for(Entity e : entities.values())
			if(e.has(type)) all.add(e);
		return all;
	}

	@Override
	public Entity findEntityById(long id) {
		return entities.get(id);
	}
}
