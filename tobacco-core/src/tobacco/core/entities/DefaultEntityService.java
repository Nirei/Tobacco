package tobacco.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.Type;
import tobacco.core.services.EntityService;

public class DefaultEntityService implements EntityService {
	
	private Entity root = null;
	private Map<Long, Entity> entities = new HashMap<Long, Entity>(128);

	@Override
	public synchronized Entity getRoot() {
		if(root == null) {
			root = new RootEntityFactory().create();
			root.add(new DebuggingComponent());
		}
		return root;
	}

	@Override
	public List<Entity> getEntityList() {
		return new ArrayList<Entity>(entities.values());
	}

	/**
	 * Creates a new Entity and adds it to the storage.
	 */
	@Override
	public Entity create() {
		Entity created = new Entity();
		entities.put(created.getID(), created);
		return created;
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
