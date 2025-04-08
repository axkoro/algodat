import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Diese Klasse implementiert die Datenstruktur d-närer Min-Heap als heapgeordnetes Array.
 *
 * @param <T>
 *            Klasse der Objekte, die im d-nären Min-Heap abgelegt werden sollen
 */
public class DaryHeap<T extends Comparable<T>> {

	// das heapgeordnete Array, in dem die Elemente des Heaps gespeichert werden
	private List<T> elements;
	// die Obergrenze für die Anzahl der Kinder eines Elements des Heaps
	private final int d;

	public DaryHeap(int d) {
		this.elements = new ArrayList<>();
		this.d = d;
	}

	/**
	 * Hilfsmethode zum Tauschen zweier Elemente mit Indizes i und j im heapgeordneten Array.
	 * 
	 * @param i
	 *            Index des ersten zu tauschenden Elements im heapgeordneten Array
	 * @param j
	 *            Index des zweiten zu tauschenden Elements im heapgeordneten Array
	 */
	protected void swap(int i, int j) {
		T elementI = elements.get(i);
		T elementJ = elements.get(j);
		elements.set(j, elementI);
		elements.set(i, elementJ);
	}

	/**
	 * Hilfsmethode zum "Heruntersickern" des Elements mit Index i im heapgeordneten Array.
	 * 
	 * @param i
	 *            Index des Elements, das im heapgeordneten Array heruntersickern soll
	 */
	protected void siftDown(int i) {
		// zunächst wird das aktuelle Element (Index i) mit all seinen Kindern verglichen und das kleinste Element wird ermittelt
		int smallest = i;
		for (int pos = 1; pos <= d; pos++) {
			int child = d * i + pos;
			if (child < size() && elements.get(child).compareTo(elements.get(smallest)) < 0) {
				smallest = child;
			}
		}

		// wenn das kleinste ermittelte Element ein Kind ist, dann wird mit ihm getauscht und von dort weiter heruntergesickert
		if (smallest != i) {
			swap(i, smallest);
			siftDown(smallest);
		}
	}

	/**
	 * Methode zum Ausgeben und Löschen des kleinsten Elements (also des Wurzelelements) im heapgeordneten Array.
	 * 
	 * @return das kleinste Element (also das Wurzelelement) des heapgeordneten Array
	 */
	public T deleteMin() {
		// Das Wurzelelement wird ermittelt, mit dem letzten Element vertauscht und gelöscht
		if (this.size() == 0) {
			return null;
		}
		T min = this.elements.get(0);
		int n = this.size();

		swap(0, n - 1);
		this.elements.remove(n - 1);

		// anschließend sickert das neue Wurzelelement herunter und das alte Wurzelelement wird zurückgegeben
		siftDown(0);
		return min;
	}

	/**
	 * Methode zum Erstellen eines neuen heapgeordneten Arrays aus einer übergebenen Liste.
	 * 
	 * @param list
	 *            Liste von Elementen, aus der ein neues heapgeordnetes Array erstellt werden soll
	 */
	public void build(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			elements.add(i, list.get(i));
		}
		if (elements.size() > 1) {
			for (int i = (elements.size()-2)/d; i >= 0; i--) {
				siftDown(i);
			}
		}
	}

	/**
	 * Hilfsmethode zum "Hochsickern" des Elements mit Index i im heapgeordneten Array.
	 * 
	 * @param i
	 *            Index des Elements, das im heapgeordneten Array hochsickern soll
	 */
	protected void siftUp(int i) {
		while (i > 0) {
			int parentPos = (i-1)/d;
			T element = elements.get(i);
			T parent = elements.get(parentPos);
			if (element.compareTo(parent) < 0) swap(i, parentPos);
			i = parentPos;
		}
	}

	/**
	 * Methode zum Hinzufügen des neuen Element element zum heapgeordneten Array.
	 * 
	 * @param element
	 *            das neu hinzuzufügende Element
	 */
	public void add(T element) {
		elements.add(element);
		siftUp(elements.size()-1);
	}

	/**
	 * Methode zum Ermitteln aller Elemente im heapgeordneten Array, die kleiner als ein übergebenes Element sind.
	 * 
	 * @param element
	 *            das Element, das mit Elementen im heapgeordneten Array verglichen werden soll
	 * @return Elemente im heapgeordneten Array, die kleiner als das übergebene Element sind
	 */
	public List<T> smallerAs(T element) {
		List<T> elementsSmaller = new ArrayList<>();
		Queue<Integer> nodes = new LinkedList<Integer>();

		if (elements.isEmpty()) return elementsSmaller;
		
		if (elements.get(0).compareTo(element) < 0) nodes.add(0);
		while (!nodes.isEmpty()) {
			int node = nodes.remove();
			elementsSmaller.add(elements.get(node));

			for (int i = 1; i <= d; i++) { // add children
				int childIndex = node*d + i;
				if (childIndex >= elements.size()) break;
				T child = elements.get(childIndex);
				if (child.compareTo(element) < 0) nodes.add(childIndex);
			}
		}

		return elementsSmaller;
	}

	/**
	 * Hilfsmethode, die überprüft, ob die Heap-Eigenschaft erfüllt ist.
	 */
	protected void check() {
		for (int i = 0; i < size(); i++) {
			for (int pos = 1; pos <= d; pos++) {
				int offset = d * i + pos;
				if (offset < size() && elements.get(i).compareTo(elements.get(offset)) > 0) {
					System.err.println("Min-Heap-Error. Vater-Knoten " + elements.get(i) + " ist kleiner als sein Kind " + elements.get(offset));
				}
			}
		}
	}

	@Override
	public String toString() {
		return this.elements.toString();
	}

	/**
	 * Methode, die die Größe des heapgeordneten Arrays ausgibt.
	 * 
	 * @return die Größe des heapgeordneten Arrays
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Methode, die ausgibt, ob das heapgeordnete Array leer ist.
	 * 
	 * @return true, gdw das heapgeordnete Array leer ist.
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public static void main(String[] argv) {
		// Generiere eine Liste der Zahlen von 30 bis 21 und 1 bis 10
		List<Integer> list = new ArrayList<>();
		for (int i = 30; i > 20; i--) {
			list.add(i);
		}
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		System.out.println("Erstelle Liste:         " + list.toString());

		// Erstelle daraus einen 4-nären Min-Heap
		DaryHeap<Integer> minHeap = new DaryHeap<>(4);
		minHeap.build(list);

		// Prüfe, ob der Min-Heap korrekt erstellt wurde.
		minHeap.check();
		System.out.println("Baue Heap aus Liste:    " + minHeap.toString());
		System.out.println("Er sollte so aussehen:  [1, 22, 2, 4, 8, 25, 24, 23, 29, 21, 28, 30, 3, 27, 5, 6, 7, 26, 9, 10]");
		System.out.println();

		// Ermittle alle Elemente kleiner als 5 und kleiner als 10
		List<Integer> smaller = minHeap.smallerAs(5);
		System.out.println("Zahlen kleiner als 5:   " + smaller);
		smaller = minHeap.smallerAs(10);
		System.out.println("Zahlen kleiner als 10:  " + smaller);
		System.out.println();

		// Extrahiere zwei mal das Minimum aus dem Min-Heap
		for (int i = 0; i < 2; i++) {
			System.out.println("Extrahiere Minimum:     " + minHeap.deleteMin());
		}

		// Prüfe, ob der Min-Heap noch korrekt ist.
		minHeap.check();
		System.out.println("Heap nach Extraktion:   " + minHeap.toString());
		System.out.println("Er sollte so aussehen:  [3, 22, 9, 4, 8, 25, 24, 23, 29, 21, 28, 30, 10, 27, 5, 6, 7, 26]");
		System.out.println();

		// Füge die Zahlen von 11 bis 20 zum Min-Heap hinzu
		for (int i = 11; i <= 20; i++) {
			minHeap.add(i);
		}

		// Prüfe, ob der Min-Heap noch korrekt ist.
		minHeap.check();
		System.out.println("Füge 11 bis 20 hinzu:   " + minHeap.toString());
		System.out.println("Er sollte so aussehen:  [3, 14, 9, 4, 8, 15, 18, 23, 29, 21, 28, 30, 10, 27, 5, 6, 7, 26, 11, 12, 13, 25, 22, 16, 17, 24, 19, 20]");
		System.out.println();

		System.out.println("Extrahiere alle Werte.");
		int lastMin = Integer.MIN_VALUE;
		while (!minHeap.isEmpty()) {
			minHeap.check();
			int min = minHeap.deleteMin();
			if (min < lastMin) {
				System.out.println("Minimum nicht korrekt:     " + min + "<" + lastMin);
			} else {
				System.out.print(".");
			}
			lastMin = min;
		}
		System.out.println("\n");

	}
}
