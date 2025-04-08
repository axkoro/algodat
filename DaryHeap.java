import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements a d-ary min-heap using an array-based structure.
 *
 * @param <T> Type of objects stored in the heap.
 */
public class DaryHeap<T extends Comparable<T>> {
    private List<T> elements;
    private final int d;

    public DaryHeap(int d) {
        this.elements = new ArrayList<>();
        this.d = d;
    }

    protected void swap(int i, int j) {
        T elementI = elements.get(i);
        T elementJ = elements.get(j);
        elements.set(j, elementI);
        elements.set(i, elementJ);
    }

    protected void siftDown(int i) {
        int smallest = i;
        for (int pos = 1; pos <= d; pos++) {
            int child = d * i + pos;
            if (child < size() && elements.get(child).compareTo(elements.get(smallest)) < 0) {
                smallest = child;
            }
        }
        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    public T deleteMin() {
        if (size() == 0) return null;
        T min = elements.get(0);
        swap(0, size() - 1);
        elements.remove(size() - 1);
        siftDown(0);
        return min;
    }

    public void build(List<T> list) {
        elements.clear();
        elements.addAll(list);
        for (int i = (size() - 2) / d; i >= 0; i--) {
            siftDown(i);
        }
    }

    protected void siftUp(int i) {
        while (i > 0) {
            int parentPos = (i - 1) / d;
            if (elements.get(i).compareTo(elements.get(parentPos)) < 0) {
                swap(i, parentPos);
            }
            i = parentPos;
        }
    }

    public void add(T element) {
        elements.add(element);
        siftUp(size() - 1);
    }

    public List<T> smallerThan(T element) {
        List<T> result = new ArrayList<>();
        Queue<Integer> nodes = new LinkedList<>();

        if (elements.isEmpty()) return result;
        if (elements.get(0).compareTo(element) < 0) nodes.add(0);
        while (!nodes.isEmpty()) {
            int index = nodes.remove();
            result.add(elements.get(index));
            for (int pos = 1; pos <= d; pos++) {
                int childIndex = d * index + pos;
                if (childIndex >= size()) break;
                if (elements.get(childIndex).compareTo(element) < 0) {
                    nodes.add(childIndex);
                }
            }
        }
        return result;
    }

    protected void check() {
        for (int i = 0; i < size(); i++) {
            for (int pos = 1; pos <= d; pos++) {
                int childIndex = d * i + pos;
                if (childIndex < size() && elements.get(i).compareTo(elements.get(childIndex)) > 0) {
                    System.err.println("Min-Heap Error: Parent " + elements.get(i) + " is greater than child " + elements.get(childIndex));
                }
            }
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public static void main(String[] argv) {
        // Generate a list containing numbers 30 to 21 and 1 to 10
        List<Integer> list = new ArrayList<>();
        for (int i = 30; i > 20; i--) {
            list.add(i);
        }
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        System.out.println("Created list: " + list);

        // Build a 4-ary min-heap from the list
        DaryHeap<Integer> minHeap = new DaryHeap<>(4);
        minHeap.build(list);

        minHeap.check();
        System.out.println("Heap built from list: " + minHeap);
        System.out.println("Expected structure: [1, 22, 2, 4, 8, 25, 24, 23, 29, 21, 28, 30, 3, 27, 5, 6, 7, 26, 9, 10]");
        System.out.println();

        // Find all elements smaller than 5 and smaller than 10
        List<Integer> smaller = minHeap.smallerThan(5);
        System.out.println("Numbers smaller than 5: " + smaller);
        smaller = minHeap.smallerThan(10);
        System.out.println("Numbers smaller than 10: " + smaller);
        System.out.println();

        // Extract the minimum twice
        for (int i = 0; i < 2; i++) {
            System.out.println("Extracted minimum: " + minHeap.deleteMin());
        }

        minHeap.check();
        System.out.println("Heap after extraction: " + minHeap);
        System.out.println("Expected structure: [3, 22, 9, 4, 8, 25, 24, 23, 29, 21, 28, 30, 10, 27, 5, 6, 7, 26]");
        System.out.println();

        // Add numbers 11 to 20 to the heap
        for (int i = 11; i <= 20; i++) {
            minHeap.add(i);
        }

        minHeap.check();
        System.out.println("After adding 11 to 20: " + minHeap);
        System.out.println("Expected structure: [3, 14, 9, 4, 8, 15, 18, 23, 29, 21, 28, 30, 10, 27, 5, 6, 7, 26, 11, 12, 13, 25, 22, 16, 17, 24, 19, 20]");
        System.out.println();

        // Extract all values
        System.out.println("Extracting all values.");
        int lastMin = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            minHeap.check();
            int min = minHeap.deleteMin();
            if (min < lastMin) {
                System.out.println("Error: " + min + " is less than " + lastMin);
            } else {
                System.out.print(".");
            }
            lastMin = min;
        }
        System.out.println("\n");
    }
}