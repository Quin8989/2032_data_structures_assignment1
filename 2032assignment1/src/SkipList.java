import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

public class SkipList<T> implements List<T> {
	private TreeMap <Integer, T> map;
	private int size;
	private int listHeight;
	private Node<T> head;

	private static class Node<T>{
		T item;
		Node<T> forward;
		Node<T> down;
		int dist;
		
		Node(T item){
			this.item = item;
		}
		
		private void addNode() {
			
		}

	}

	/**
	 * Adds item to end of list
	 * @param item		item to be added
	 * @return true 	item has been added
	 */
	@Override
	public boolean add(T item) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * Adds item to specific index on list. Previous item in index gets moved forward
	 * @param index		position in list item is added to.
	 * @param item		item to be added
	 */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Returns item inside specified index
	 * @param index		position in the list the item to be returned has
	 * @return item		item to be returned
	 */
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		T item = null;
		return item;
	}
	
	/**
	 * Returns size of list
	 * @return size		size of list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Removes all items in list
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Describes contents of list in string form
	 * @return report		contents inside list
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String report = null;
		return report;

	}
	
	private void assignNodeHeight() {
		
	}
	
	/* Below are any unused methods that aren't needed for the implementation,
	 * but are necessary for using the List interface.
	 */

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int arg0, T arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

}
