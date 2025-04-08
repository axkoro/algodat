public class LinkedList implements List {
    
    private class Node {
        int element;
        Node next;

        Node(int element) {
            this.element = element;
            this.next = null;
        }
    }

    private int numElements;
    private Node head;
    private Node tail;

    public LinkedList() {
        this.numElements = 0;
        this.head = null;
        this.tail = null;
    }

    private boolean posOutOfBounds(int pos) {
        return (pos < 0 || pos >= numElements);
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    public int size() {
        return numElements;
    }

    public void add(int element) {
        Node node = new Node(element);

        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        numElements++;
    }

    public void add(int pos, int element) {
        Node node = new Node(element);
        if (pos < 0 || pos > numElements) throw new IndexOutOfBoundsException(pos);
        if (pos == 0) {
            node.next = head;
            head = node;
        } else if (pos == numElements) {
            tail.next = node;
            tail = node;
        } else {
            Node iterator = head;
            for (int i = 0; i < pos-1; i++) {
                iterator = iterator.next;
            }
            node.next = iterator.next;
            iterator.next = node;
        }
        numElements++;
    }

    public void delete(int pos) {
        if (posOutOfBounds(pos)) throw new IndexOutOfBoundsException(pos);
        if (pos == 0) {
            head = head.next;
        } else {
            Node iterator = head;
            for (int i = 0; i < pos-1; i++) {
                iterator = iterator.next;
            }
            iterator.next = iterator.next.next;

            if (pos == numElements-1) {
                tail = iterator;
            }
        }
        numElements--;
    }

    public int get(int pos) {
        if (posOutOfBounds(pos)) throw new IndexOutOfBoundsException(pos);
        Node iterator = head;
        for (int i = 0; i < pos; i++) {
            iterator = iterator.next;
        }
        return iterator.element;
    }
}
