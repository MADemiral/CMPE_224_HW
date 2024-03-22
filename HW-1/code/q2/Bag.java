package q2;
//-----------------------------------------------------
// Title: Bag Class
// Author: Mehmet Alp Demiral
// Description: This class contains all methods and attributes for Bag Class used in Graph class
//-----------------------------------------------------


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<T> implements Iterable<T> {
    
    private Node first_node;
    private int size;

    public class Node {
        T generic_item;
        Node next;
    }

    public Bag() {
        first_node = null;
        size = 1;
    }

    public void add(T item) {
        Node old_first_node = first_node;
        first_node = new Node();
        first_node.generic_item = item;
        first_node.next = old_first_node;
        size++;
    }

    public int get_size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {
        private Node current = first_node;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.generic_item;
            current = current.next;
            return item;
        }
    }
}
