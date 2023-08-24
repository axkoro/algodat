public class Sorting {
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minPos = i;
            for (int j = i; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minPos = j;
                }
            }
            array[minPos] = array[i];
            array[i] = min;
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j = i-1;
            while (j >= 0 && array[j] > value) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = value;
        }
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i] > array[i+1]) {
                    int tmp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = tmp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void mergeSort(int[] array) {
        if (array.length == 1) return;
        if (array.length == 2) {
            if (array[0] > array[1]) { // swap
                int tmp = array[1];
                array[1] = array[0];
                array[0] = tmp;
            }
        }

        int[] left = new int[array.length/2];
        int[] right = new int[array.length - array.length/2];

        for (int i = 0; i < left.length; i++) { // copy to left sub-array
            left[i] = array[i];
        }
        for (int i = 0; i < right.length; i++) { // copy to right sub-array
            right[i] = array[array.length/2+i];
        }

        mergeSort(left);
        mergeSort(right);

        int[] merged = merge(left, right);
        for (int i = 0; i < array.length; i++) {
            array[i] = merged[i];
        }
    }

    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];

        int leftPointer = 0;
        int rightPointer = 0;
        int mergedPointer = 0;

        while (leftPointer < left.length && rightPointer < right.length) { // merge until either left/right is fully copied
            if (left[leftPointer] <= right[rightPointer]) {
                merged[mergedPointer] = left[leftPointer];
                leftPointer++;
            } else {
                merged[mergedPointer] = right[rightPointer];
                rightPointer++;
            }
            mergedPointer++;
        }

        while (leftPointer < left.length) { // copy remaining contents of "bigger" array
            merged[mergedPointer] = left[leftPointer];   
            leftPointer++;
            mergedPointer++;
        }
        while (rightPointer < right.length) {
            merged[mergedPointer] = right[rightPointer];   
            rightPointer++;
            mergedPointer++;
        }

        return merged;
    }
    
    public static void quickSort(int[] array) {
        
    }

    public static void radixSort(int[] array) {
        
    }

    public static void bucketSort(int[] array) {
        
    }

    public static void countingSort(int[] array) {

    }
}
