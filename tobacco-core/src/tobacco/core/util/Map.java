package tobacco.core.util;

import java.util.Collection;
import java.util.Set;

public class Map<K, V> implements java.util.Map<K, V> {
	
	private final Class<K> keyClass;
	private final Class<V> contentClass;
	private final java.util.Map<K, V> implementer;

	public Map(Class<K> keyClass, Class<V> contentClass, Class<? extends Map<K, V>> storageClass) throws InstantiationException, IllegalAccessException {
		this.keyClass = keyClass;
		this.contentClass = contentClass;
		implementer = storageClass.newInstance();
	}
	
	public Class<K> getKeyClass() {
		return keyClass;
	}
	
	public Class<V> getContentClass() {
		return contentClass;
	}

	@Override
	public void clear() {
		implementer.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return implementer.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return implementer.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return implementer.entrySet();
	}

	@Override
	public V get(Object key) {
		return implementer.get(key);
	}

	@Override
	public boolean isEmpty() {
		return implementer.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return implementer.keySet();
	}

	@Override
	public V put(K key, V value) {
		return implementer.put(key, value);
	}

	@Override
	public void putAll(java.util.Map<? extends K, ? extends V> m) {
		implementer.putAll(m);
	}

	@Override
	public V remove(Object key) {
		return implementer.remove(key);
	}

	@Override
	public int size() {
		return implementer.size();
	}

	@Override
	public Collection<V> values() {
		return implementer.values();
	}

}
