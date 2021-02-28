import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.TreeMap;



/* This implementation of the skip list uses parts of code shared in class lecture 05 slide 33*/

public class SkipList<E> implements List<E> {
	private int size = 0;
	private int height = 1;
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
		Node<E> node = new Node<>(null); // create head node on lowest level
		node.forward = null;
		node.down = null;
		node.dist = size + 1;
		head = node;
		random = new Random();
	}

	/**
	 * Adds item to end of list
	 * 
	 * @param item item to be added
	 * @return true item has been added
	 */
	@Override
	public boolean add(E item) {
		add(this.size(), item);
		return true;
	}

	/**
	 * Inserts a new item immediately after specified index
	 * 
	 * @param index position in list item is added to.
	 * @param item  item to be added
	 * 
	 * @throws IndexOutOfBoundsException if index is out of list range
	 */
	@Override
	public void add(int index, E item) {
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
		int pos = 0; // pos = pos(x) - the element after which we insert
		int currentLevel = height;
		Node<E> lastInserted = null; // the subsequent, lower new copies will be attached to this
		for (Node<E> x = head; x != null; x = x.down, currentLevel--) { // for each head node in column except base
																		// layer
			while (pos + x.dist <= index) { // looks at where node in question's forward node goes
				pos = pos + x.dist; // go to new position if N.I.Q's forward node is less than wanted index
				x = x.forward; // update N.I.Q
			}
			if (currentLevel > lvl) {
				x.dist = x.dist + 1;

			} else { // insert y between x and z
				Node<E> y = new Node<>(item);
				Node<E> z = x.forward;
				y.forward = z;
				x.forward = y;
				y.dist = pos + x.dist - index;
				x.dist = index + 1 - pos;

				if (lastInserted != null) {
					lastInserted.down = y;

				}
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
	 * 
	 * @throws IndexOutOfBoundsException if index is out of list range
	 */

	@Override
	public E get(int index) {
		int pos = 0;
		if (index < 0 || index >= size) { // bad index
			throw new IndexOutOfBoundsException("Index " + index + " doesnt exist!");
		}
		for (Node<E> i = head; i != null; i = i.down) {
			while (pos + i.dist <= index) {
				pos += i.dist;
				i = i.forward;
			}
			if (pos == index) {
				Node<E> item = null;
				for (Node<E> j = i; j != null; j = j.down) {
					item = j;
				}
				item = item.forward;
				return item.item;
			}
		}
		return null;
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
		size = 0;
		height = 1;

		head = new Node <E> (null);
		head.dist = 1;
		head.forward = null;
		head.down = null;
	}

	/**
	 * Describes contents of list in string form
	 * 
	 * @return report contents inside list
	 */
	@Override
	public String toString(){ //Code used is from the assignment solutions, author is Prof. Andriy Pavlovych
		if (size == 0)
			return "[]";

		Node<E> node = head;
		while(node.down != null) node = node.down; //go to the bottom level
		node = node.forward; //skip the head sentinel

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (; node != null; node = node.forward){
			sb.append(node.item);
			if (node.forward == null)
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
		return null; //this line should never be reached
	}

	@Override
	public E remove(int index) { //Code used is from the assignment solutions, author is Prof. Andriy Pavlovych
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}

		int pos = -1;
		E deletedItem = null;
		for (Node <E> x = head; x != null; x = x.down){
			while (pos + x.dist < index ){//stop just before the element being removed 
				pos = pos + x.dist;
				x = x.forward;
			}
			if (pos + x.dist == index){ //the element exists at the current level, remove it
				deletedItem = x.forward.item;
				x.dist = x.dist + x.forward.dist - 1; 
				x.forward = x.forward.forward;
			}
			else x.dist--; //the element does not exist at this level, shrink the distance only.
		}
		size--;
		//now remove the empty top levels, if any were created
		if (head.down != null)//if there is more than one level
			for (Node <E> x = head; x != null; x = x.down){
				if (x.forward == null){
					head = x.down;
					height--;
				}
			}		
		return deletedItem;
	}
	

	/**
	 * Gives a number from 1 to 20 with each increment being half as common as the
	 * one before
	 * 
	 * @return i a number between 1 and 20
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
