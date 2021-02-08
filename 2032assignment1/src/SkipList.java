import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.TreeMap;

public class SkipList<T> implements List<T> {
	private int size = 0;
	public int height = 1;
	private Node<T> head;
	private Random random;
	private final double p = 0.5;
	private final int MAX_LEVEL = 20;

	private static class Node<T> {
		T item;
		Node<T> forward;
		Node<T> down;
		int dist;

		Node(T item) {
			this.item = item;
		}

	}

	/**
	 * Creates an empty Skip List
	 */
	public SkipList() {
		//TODO
	}

	/**
	 * Adds item to end of list
	 * 
	 * @param item item to be added
	 * @return true item has been added
	 */
	@Override
	public boolean add(T item) {
		// TODO Auto-generated method stub
		map.put(size, item);
		size++;
		assignNodeHeight();// IDK what to put in as parameters
		return true;
	}

	/**
	 * Adds item to specific index on list. Previous item in index gets moved
	 * forward
	 * 
	 * @param index position in list item is added to.
	 * @param item  item to be added
	 */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: " +size);
		}
		int lvl = assignNodeHeight();
		if( lvl > height) { //grow if necessary
			for (int i = lvl; i > height; i--) {
				Node<T> node = new Node <> (null);
				node.down = head;
				node.forward = //TODO
				node.dist = //TODO
				head = node;
			}
			height = lvl;
		}
		int pos = 0; //pos = pos(x) - the element after which we insert
		int currentLevel = height;
		Node <T> lastInserted = null; // the subsequent, lower new copies will be attached to this
		for (Node <T> x = head; /*TODO*/){
			while(/*TODO*/ <= index) {
				pos = pos + x.dist;
				x = x.forward;
			}
			if (currentLevel > lvl) {
				//TODO -do not insert: just update distances
			}else { //TODO -Insert an item at this level
				Node<T> y = new Node <>(element);
				/*TODO -insert y between x and z*/
				//new pos(z) = pos+old xofDistance[i] +1
				//new pos(y) = k +1
				//new y.distance = new pos(z) - new pos(y)
				//ofDistance = new pos(y) - new pos(x)
				if(lastInserted != null) lastInserted.down = y;
				lastInserted = y;
			}
		}
		size++;
				

	}

	/**
	 * Returns item inside specified index
	 * 
	 * @param index position in the list the item to be returned has
	 * @return item item to be returned
	 */
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		T item = null;
		return item;
	}

	/**
	 * Returns size of list
	 * 
	 * @return size size of list
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
	 * 
	 * @return report contents inside list
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String report = null;
		return report;

	}

	private int assignNodeHeight() {
		int i = 1;
		while (random.nextDouble() < 0.5 && i < 20) {
			i++;
			}
		return i;
	}

	/*
	 * Below are any unused methods that aren't needed for the implementation, but
	 * are necessary for using the List interface.
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
