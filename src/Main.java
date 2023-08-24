public class Main {
    public static void main(String[] args) {
        int[] numbers = {0, 4, 3, 2, 1, 66, 4444, 234, 2, 8};
        Sorting.mergeSort(numbers);
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}