/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.core.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Type;

/**
 * Default implementation of the {@link EntityService}.
 * @author nirei
 */
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

	@Override
	public Entity create() {
		Entity created = new Entity();
		entities.put(created.getID(), created);
		return created;
	}
	
	@Override
	public Entity createInRoot() {
		Entity created = new Entity();
		entities.put(created.getID(), created);
		((ContainerComponent) root.get(Component.CONTAINER_C)).addChild(created);
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
