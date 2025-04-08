import java.util.Comparator;
import java.util.Random;

public class SortedSearch {

  public static abstract class Search {
    protected CountingComparator comparator;

    public Search() {
      this.comparator = new CountingComparator();
    }

    public abstract boolean search(Long[] sortedList, Long key);

    public int getNumberOfComparisons() {
      return this.comparator.getNumberOfComparisons();
    }
  }

  public static class LinearSearch extends Search {

    @Override
    public boolean search(Long[] sortedList, Long key) {
      for (Long element : sortedList) {
        // compare returns a negative integer, zero, or a positive integer if
        // the first argument is less than, equal to, or greater than the
        // second.
        int comparison = this.comparator.compare(element, key);
        if (comparison == 0) {
          return true;
        }
      }
      return false;
    }
  }

  public static class BinarySearch extends Search {

    @Override
    public boolean search(Long[] sortedList, Long key) {
      int low = 0; // untere Begrenzung fuer Array
      int high = sortedList.length - 1; // obere Begrenzung fuer Array

      while (true) {
        int mid = low + ((high - low) / 2);
        int comparison = this.comparator.compare(sortedList[mid], key);

        if (comparison == 0) return true;
        if ((high - low) <= 1) return false; // bricht Suche ab, falls |Subarray| <= 1 ist
        if (comparison < 0) low = mid + 1;
        if (comparison > 0) high = mid - 1;
      }
    }
  }

  public static class InterpolationSearch extends Search {

    @Override
    public boolean search(Long[] sortedList, Long key) {
      int low = 0;
      int high = sortedList.length - 1;

      if (this.comparator.compare(sortedList[0], key) > 0 // prueft ob key im moeglichen Wertebereich liegt
              || this.comparator.compare(sortedList[high], key) < 0) return false;

      while (true) {
        int rank = (int) (low + (high - low) * ((double) (key - sortedList[low]) / (sortedList[high] - sortedList[low]))); // schaetzt Index bei Gleichverteilung (nach Formel aus der Vorlesung)
        int comparison = this.comparator.compare(sortedList[rank], key);

        if (comparison == 0) return true;
        if ((high - low) <= 1) return false; // bricht Suche ab, falls |Subarray| <= 1 ist
        if (comparison < 0) low = rank + 1;
        if (comparison > 0) high = rank - 1;
      }
    }
  }

  public static class CountingComparator implements Comparator<Long> {

    private int numberOfComparisons;

    public CountingComparator() {
      this.numberOfComparisons = 0;
    }

    @Override
    public int compare(Long o1, Long o2) {
      this.numberOfComparisons++;
      return o1.compareTo(o2);
    }

    public int getNumberOfComparisons() {
      return this.numberOfComparisons;
    }
  }

  public static void main(String[] args) {
    Search search;
    Random random = new Random(1l);

    System.out.println("Preliminary testing. Initializing list [1, 2, 3].");
    Long[] list = { 1l, 2l, 3l };
    System.out.println("Searching for Element \"0\", which should not be found.");
    search = new LinearSearch();
    boolean located = search.search(list, 0l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 0l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 0l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");
    System.out.println("Searching for Element \"1\", which should be found.");
    search = new LinearSearch();
    located = search.search(list, 1l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"1\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 1l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"1\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 1l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"1\" (" + search.getNumberOfComparisons() + " comparisons).");
    System.out.println("Searching for Element \"2\", which should be found.");
    search = new LinearSearch();
    located = search.search(list, 2l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"2\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 2l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"2\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 2l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"2\" (" + search.getNumberOfComparisons() + " comparisons).");
    System.out.println("Searching for Element \"3\", which should be found.");
    search = new LinearSearch();
    located = search.search(list, 3l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"3\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 3l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"3\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 3l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"3\" (" + search.getNumberOfComparisons() + " comparisons).");
    System.out.println("Searching for Element \"4\", which should not be found.");
    search = new LinearSearch();
    located = search.search(list, 4l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"4\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 4l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"4\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 4l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"4\" (" + search.getNumberOfComparisons() + " comparisons).");

    System.out
    .println("\nInitializing pseudo-randomized, sorted list with one million elements. The list starts with a zero and neighboring elements have a difference of 1-3.");
    list = new Long[1000000];
    list[0] = 0l;
    for (int i = 1; i < list.length; i++) {
      list[i] = list[i - 1] + random.nextInt(3) + 1;
    }
    System.out.println("Searching for Element \"0\", which should be the first element.");
    search = new LinearSearch();
    located = search.search(list, 0l);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, 0l);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, 0l);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"0\" (" + search.getNumberOfComparisons() + " comparisons).");

    System.out.println("Searching for the 300.000th element of the list.");
    search = new LinearSearch();
    located = search.search(list, list[300000 - 1]);
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"" + list[300000 - 1].intValue() + "\" (" + search.getNumberOfComparisons()
        + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, list[300000 - 1]);
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"" + list[300000 - 1].intValue() + "\" (" + search.getNumberOfComparisons()
        + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, list[300000 - 1]);
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"" + list[300000 - 1].intValue() + "\" (" + search.getNumberOfComparisons()
        + " comparisons).");

    System.out
    .println("\nInitializing list with 63 elements. The list starts with a one and following elements have 2 times the value of its predecessor.");
    list = new Long[63];
    list[0] = 1l;
    for (int i = 1; i < list.length; i++) {
      list[i] = list[i - 1] * 2l;
    }
    System.out.println("Searching for Element \"2^31\", which should be right in the middle.");
    search = new LinearSearch();
    located = search.search(list, (long) Math.pow(2d, 31d));
    System.out.println("\tLinear search:        " + (located ? "located" : "did not locate")
        + " element \"2^31\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new BinarySearch();
    located = search.search(list, (long) Math.pow(2d, 31d));
    System.out.println("\tBinary search:        " + (located ? "located" : "did not locate")
        + " element \"2^31\" (" + search.getNumberOfComparisons() + " comparisons).");
    search = new InterpolationSearch();
    located = search.search(list, (long) Math.pow(2d, 31d));
    System.out.println("\tInterpolation search: " + (located ? "located" : "did not locate")
        + " element \"2^31\" (" + search.getNumberOfComparisons() + " comparisons).");
  }

}
