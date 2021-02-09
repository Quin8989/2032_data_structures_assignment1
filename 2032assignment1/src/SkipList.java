import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.TreeMap;

public class SkipList<E> implements List<E> {
	private int size = 0;
	public int height = 1;
	private Node<E> head;
	private Random random;
	private final double p = 0.5;
	private final int MAX_LEVEL = 20;

	private static class Node<E> {
		E item;
		Node<E> forward;
		Node<E> down;
		int dist;

		Node(E item) {
			this.item = item;
		}

	}

	/**
	 * Creates an empty Skip List
	 */
	public SkipList() {
		// TODO
		Node<E> node = new Node<>(null); //create head node on lowest level
		node.forward = null;
		node.down = null;
		node.dist = size + 1;
		head = node;
	}

	/**
	 * Adds item to end of list
	 * 
	 * @param item item to be added
	 * @return true item has been added
	 */
	@Override
	public boolean add(E item) {
		// TODO Auto-generated method stub
		int lvl = assignNodeHeight();
		if (lvl > height) { // grow if necessary
			for (int i = lvl; i > height; i--) { // amount of times run is the difference between lvl and height
				Node<E> node = new Node<>(null); // create new node on top of column of head nodes at the start
				node.down = head;
				node.forward = null;
				node.dist = size + 1;
				head = node;
			}
			height = lvl; // update height
		}
		/*
		 * find where we need to insert item (nodes that are supposed to connect to it)
		 */
		Node<E> x = head; // node in question (N.I.Q)
		int pos = 0; // pos = pos(x) - the element after which we insert
		int currentLevel = height;
		Node<E> lastInserted = null; // the subsequent, lower new copies will be attached to this
		for (int i = height; i > 1; i--) { // for each head node in column except base layer
			while (pos + x.dist <= size) { // looks at where node in question's forward node goes
				pos = pos + x.dist; // go to new position if N.I.Q's forward node is less than size of list
				x = x.forward; // update N.I.Q
			}
			if (currentLevel > lvl) {
				// TODO -do not insert: just update distances
				x.dist = x.dist + 1; // IDK why we need to do this but the pseudo-code in the class notes tell me
										// this
			} else { // TODO -insert y after x
				Node<E> y = new Node<>(item);
				y.forward = null;
				x.forward = y;
				x.dist = pos + 1 - size;

				if (lastInserted != null)
					lastInserted.down = y;
				lastInserted = y;
			}
		}
		size++;
		
		return true;
	}

	/**
	 * Inserts a new item immediately after specified index
	 * 
	 * @param index position in list item is added to.
	 * @param item  item to be added
	 */
	@Override
	public void add(int index, E item) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) { // bad index
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		int lvl = assignNodeHeight();
		if (lvl > height) { // grow if necessary
			for (int i = lvl; i > height; i--) { // amount of times run is the difference between lvl and height
				Node<E> node = new Node<>(null); // create new node on top of column of head nodes at the start
				node.down = head;
				node.forward = null;
				node.dist = size + 1;
				head = node;
			}
			height = lvl; // update height
		}
		/*
		 * find where we need to insert item (nodes that are supposed to connect to it)
		 */
		Node<E> x = head; // node in question (N.I.Q)
		int pos = 0; // pos = pos(x) - the element after which we insert
		int currentLevel = height;
		Node<E> lastInserted = null; // the subsequent, lower new copies will be attached to this
		for (int i = height; i > 1; i--) { // for each head node in column except base layer
			while (pos + x.dist <= index) { // looks at where node in question's forward node goes
				pos = pos + x.dist; // go to new position if N.I.Q's forward node is less than wanted index
				x = x.forward; // update N.I.Q
			}
			if (currentLevel > lvl) {
				// TODO -do not insert: just update distances
				x.dist = x.dist + 1; // IDK why we need to do this but the pseudo-code in the class notes tell me
										// this
			} else { // TODO -insert y between x and z
				Node<E> y = new Node<>(item);
				Node<E> z = new Node<>(item);
				y.forward = z;
				x.forward = y;
				y.dist = pos + x.dist - index;
				x.dist = index + 1 - pos;

				if (lastInserted != null)
					lastInserted.down = y;
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
	public E get(int index) {
		// TODO Auto-generated method stub
		E item = null;
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
	
	/**
	 * Gives a number from 1 to 20 with each increment being half as common as the one before
	 * 
	 * @return i	a number between 1 and 20
	 */
	private int assignNodeHeight() { //
		int i = 1;
		while (random.nextDouble() < p && i < MAX_LEVEL) {
			i++;
		}
		return i;
	}

	/*
	 * Below are any unused methods that aren't needed for the implementation, but
	 * are necessary for using the List interface.
	 */

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
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
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int arg0) {
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
	public E set(int arg0, E arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
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
