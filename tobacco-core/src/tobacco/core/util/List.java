package tobacco.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class List<E> implements java.util.List<E> {
	
	private final Class<E> contentClass;
	private final java.util.List<E> implementer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List(Class<E> contentClass, Class<? extends java.util.List> storageClass) throws InstantiationException, IllegalAccessException {
		this.contentClass = contentClass;
		implementer = (java.util.List<E>) storageClass.newInstance();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List(Class<E> contentClass, java.util.List implementer) throws InstantiationException, IllegalAccessException {
		this.contentClass = contentClass;
		this.implementer = (java.util.List<E>) implementer;
	}
	
	
	public Class<E> getContentClass() {
		return contentClass;
	}

	@Override
	public boolean add(E e) {
		return implementer.add(e);
	}

	@Override
	public void add(int index, E element) {
		implementer.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> e) {
		return implementer.addAll(e);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return implementer.addAll(index, c);
	}

	@Override
	public void clear() {
		implementer.clear();
	}

	@Override
	public boolean contains(Object o) {
		return implementer.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return implementer.containsAll(c);
	}

	@Override
	public E get(int index) {
		return implementer.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return implementer.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return implementer.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return implementer.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return implementer.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return implementer.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return implementer.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return implementer.remove(o);
	}

	@Override
	public E remove(int index) {
		return implementer.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return implementer.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return implementer.retainAll(c);
	}

	@Override
	public E set(int index, E element) {
		return implementer.set(index, element);
	}

	@Override
	public int size() {
		return implementer.size();
	}

	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		return implementer.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return implementer.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return implementer.toArray(a);
	}

}
