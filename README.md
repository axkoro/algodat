# Algorithms and Data Structures

This project provides custom implementations of several fundamental data structures and algorithms in Java. It includes implementations for dynamic array and linked list structures, a d-ary min-heap, as well as sorting and sorted search algorithms.

I did this to practise for the module "[Algorithmen und Datenstrukturen](https://agnes.hu-berlin.de/lupo/rds?state=verpublish&status=init&vmfile=no&publishid=201103&moduleCall=webInfo&publishConfFile=webInfo&publishSubDir=veranstaltung)" (_algorithms and data structures_) in my Computer Science bachelor's program.

## Project Contents

- **List.java:** Interface defining list operations.
- **ArrayList.java:** Custom implementation of a dynamic array.
- **LinkedList.java:** Custom implementation of a singly linked list.
- **Sorting.java:** Various sorting algorithm implementations.
- **SortedSearch.java:** Search algorithms operating on sorted data.
- **DaryHeap.java:** Implementation of a d-ary min-heap.

## Compilation and Execution

1. **Compilation:**  
   Compile all the Java files with:
   ```bash
   javac -d bin src/*.java
   ````

2. **Execution:**  
   Run the class you want to test:
   ```bash
   java -cp bin DaryHeap
   ```
   Note: The class to be run needs to have a main function. Unfortunately, I didn't write those for every class back when I worked on this.