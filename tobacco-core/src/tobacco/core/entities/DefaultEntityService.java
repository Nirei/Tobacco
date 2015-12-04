package tobacco.core.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import tobacco.core.components.Type;
import tobacco.core.services.EntityService;

public class DefaultEntityService implements EntityService {
	
	private Entity root = null;
	private Map<Long, Entity> entities = new ConcurrentHashMap<Long, Entity>(128);

	@Override
	public Entity getRoot() {
		return root;
	}
	
	@Override
	public void setRoot(Entity root) {
		this.root = root;
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
		List<Entity> all = entities.values().stream()
			.filter(e -> e.has(type))
			.collect(Collectors.toCollection(LinkedList::new));
		return all;
	}

	@Override
	public Entity findEntityById(long id) {
		return entities.get(id);
	}
}
