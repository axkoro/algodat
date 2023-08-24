public class ArrayList implements List {

    private int numElements;
    private int[] array;

    public ArrayList() {
        this.numElements = 0;
        this.array = new int[1];
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    private boolean posOutOfBounds(int pos) {
        return (pos < 0 || pos >= numElements);
    }

    private int[] resize(int[] array) {
        int[] newArray = new int[2*array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public void add(int element) {
        if (numElements == array.length) array = resize(array);
        array[numElements] = element;
        numElements++;
    }

    public void add(int pos, int element) {
        if (pos < 0 || pos > numElements) {
            throw new IndexOutOfBoundsException();
        }
        if (numElements == array.length) array = resize(array);
        for (int i = numElements; i > pos; i--) {
            array[i] = array[i-1];
        }
        array[pos] = element;
        numElements++;
    }

    public void delete(int pos) {
        if (isEmpty() || posOutOfBounds(pos)) throw new IndexOutOfBoundsException();
        if (numElements < array.length / 4) {
            int[] newArray = new int[array.length/2];
            for (int i = 0; i < numElements; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        for (int i = pos; i < numElements-1; i++) {
            array[i] = array[i+1];
        }
        numElements--;
    }

    public int get(int pos) {
        if (isEmpty() || posOutOfBounds(pos)) throw new IndexOutOfBoundsException();
        return array[pos];
    }
    
    public int size() {
        return numElements;
    }
}
