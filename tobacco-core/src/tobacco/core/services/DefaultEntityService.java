package tobacco.core.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.entities.RootEntityFactory;

public class DefaultEntityService implements EntityService {
	
	private Entity root = null;
	private List<Entity> entList = new ArrayList<Entity>();
	private EntityFactory eFactory = new EntityFactory();

	@Override
	public synchronized Entity getRoot() {
		if(root == null) root = RootEntityFactory.create();
		return root;
	}

	@Override
	public List<Entity> getEntityList() {
		return new ArrayList<Entity>(entList);
	}

	@Override
	public Entity create() {
		Entity created = eFactory.create();
		entList.add(created);
		return created;
	}

	@Override
	public Entity create(Collection<Component> components) {
		return eFactory.create(components);
	}

	@Override
	public void remove(Entity entity) {
		entList.remove(entity);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

}
