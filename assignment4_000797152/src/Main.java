/**
 * I, Tommy Zieba, 000797152 certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 *
 * DISCUSSION:
 *    Note that an average was not taken to test the performance for add/sort SortedLinkedList versus ArrayList. An
 *    average would take much longer to perform the test, but an analysis is offered here by describing how tests
 *    can differ from one execution to another. The results for adding/sorting are relatively consistent. Testing shows
 *    SortedLinkedList is approximately 1.4 to 2 times faster for large arrays of random Strings. Results for removing
 *    from a SortedLinkedList or ArrayList is analysed here by taking an average across 30 iterations of removing first,
 *    middle, and last elements from large and small lists. The results for testing performance for removing are not as
 *    consistent - refer to commentary for the reason. One thing is certain, that LinkedList removal is always faster
 *    if removing the first element. Note that additional testing was done for extremely large lists, but was commented
 *    out (during reading in names) since it takes a long time to run. The results of testing extremely large lists for
 *    removal is commented on nonetheless.
 *
 *    COMMENTARY ON ADD/SORT PERFORMANCE:
 *    According to "https://howtodoinjava.com/java/collections/arraylist/linkedlist-vs-arraylist/" ArrayList's add
 *    operation has time performance bounded by O(1) unless its internal array has to be resized, in which case it is
 *    bounded by O(log(n)). LinkedList is always an O(1) operation for adding since nodes are normally added to the
 *    first element by reference. These observations do not consider sorting - which will add to the overhead. With
 *    sorting, the overhead of O(nlog(n)) must be accounted for with Collections.sort() which is implemented with a
 *    Merge Sort algorithm (according to the Oracle documentation). On the other hand, the add/sort implemented with
 *    SortedLinkedList here has a single while loop and some nested comparisons. This means the worst case for add/sort
 *    for SortedLinkedList is O(1) + O(n) = O(n). Therefore, timed performance for sorting an ArrayList has a bound much
 *    greater than SortedLinkedList at O(nlog(n)) + O(log(n)) = O(nlog(n)). Note that there is a chance (with a
 *    partially sorted list) that ArrayList performs the same as SortedLinkedList because Merge Sort can be O(n) when
 *    elements are partially sorted before beginning the algorithm. But, this defeats the purpose of sorting. Moreover,
 *    SortedLinkedList is the obvious choice for adding/sorting operations as opposed to ArrayList with Collections.
 *
 *    COMMENTARY ON REMOVE PERFORMANCE:
 *    According to "https://howtodoinjava.com/java/collections/arraylist/linkedlist-vs-arraylist/" ArrayList's remove
 *    operation uses an algorithm that moves all the elements to the right of the one being removed. This means that
 *    removal from an ArrayList is bounded by O(n) in the worst case, which occurs when removing the first element. On
 *    the other hand, removal for a LinkedList (consisting of Nodes) is O(1) because references are changed based on
 *    pointers. But, the SortedLinkedList class here has a remove algorithm bounded by O(n) in the worst case because
 *    all nodes up to the node to be removed must be traversed for elements of generic type that must be comparable.
 *    Based on these observations, the test observations should show that the first element is removed the fastest with
 *    a SortedLinkedList and should linearly reach the same time performance towards the last element. However, the
 *    test is not consistent and the removal of the middle and/or last element is sometimes faster for ArrayList (with
 *    a speed factor < 1 compared to SortedLinkedList. Consider the following commentary on memory allocation to
 *    address this issue and explain why it occurs.
 *
 *    COMMENTARY ON MEMORY ALLOCATION AND REMOVE PERFORMANCE:
 *    According to "https://techdifferences.com/difference-between-contiguous-and-non-contiguous-memory-allocation.html"
 *    fragmented memory (such as that in a LinkedList) suffers overhead of translating memory addresses as opposed to
 *    contiguously allocated memory which takes the next address. There is a subtle trade-off of memory vs. comparisons
 *    at play here during testing. SortedLinkedList dominates for removal of first elements (since it is the first
 *    memory address). In contrast, there is commented code during construction of test data (during reading) that reads
 *    the list repeatedly to produce almost 10000 elements. Testing a massive list shows that the likelihood of more
 *    fragmented memory increases when there are many elements to be placed in memory. The result is observing a greater
 *    drop off in performance approaching removal of the last element and an increased chance that ArrayList performs
 *    better. Recall that both data structures approach the same time performance for removing the last element with
 *    respect to comparisons. Fragmented memory throws a wrench in the cogs. This can happen for lists both small and
 *    large, but the effect of memory allocation inhibiting SortedLinkedLists performance increases as list size does,
 *    but also the performance differential increases too and the point at which this occurs will be sooner in the list.
 *
 *    CONCLUSIONS:
 *    Testing and analysis of algorithms used revealed that SortedLinkedList is faster than the ArrayList when adding
 *    and sorting elements except if lists are already sorted (which defeats the purpose). On the other hand, removing
 *    elements is always faster at the start of a SortedLinkedList and performance approaches a similar result with
 *    respect to number of comparisons near the end of the list. But, due to fragmented memory allocation there is a
 *    non-zero probability that LinkedList will suffer losses due to resolving memory addresses. These losses are more
 *    likely to occur for extremely large lists, but never at the first element. As the size becomes more extreme, so do
 *    the losses (which occur sooner in the list and at a greater cost). Therefore, SortedLinkedList can occasionally
 *    perform not as well, but nearly the same as ArrayList for removal when the list size is reasonable and removing
 *    elements away from the first element with memory closely allocated. The same is not true for extremely large
 *    lists, in which case ArrayList removal is more performant with exception to first element (or elements - depending
 *    on memory allocation vs. closeness to first).
 * */


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //REQUIRED:  Read in baby names to be used for testing SortedLinkedList with strings.
    final String bNamesFilePath = "src/bnames.txt";
    ArrayList<String> babyNames = new ArrayList<>();
    try {
      Scanner bNameScanner = new Scanner(new File(bNamesFilePath));
      bNameScanner.useDelimiter("\r\n");
      while (bNameScanner.hasNext()) {
        String next = bNameScanner.next();
        babyNames.add(next);
        //FOR TESTING EXTRA LARGE DATASETS
        //babyNames.add(next);
        //babyNames.add(next);
        //babyNames.add(next);
        //babyNames.add(next);
      }
    } catch (Exception ex) {
      System.out.println("Exception message: " + ex.getMessage());
    }
    ArrayList<String> testStrings = new ArrayList<>(babyNames);
    SortedLinkedList<String> stringSortedLinkedList = new SortedLinkedList<>();

    //REQUIRED: Generate a random array of integers to be used for testing SortedLinkedList with integers.
    int maxRandom = babyNames.size() + 1;
    ArrayList<Integer> randomNumbers = new ArrayList<>();
    for (int i = 0; i < babyNames.size(); i++)
      randomNumbers.add((int) (Math.random() * maxRandom));
    ArrayList<Integer> testNumbers = new ArrayList<>(randomNumbers);
    SortedLinkedList<Integer> integerSortedLinkedList = new SortedLinkedList<>();


    //---- STRING TESTS ----//

    //TEST 1 -> Adding strings without repetition
    String addString = "[";
    int num;
    for (int i = 0; i < 3; i++) {
      num = i + 1;
      stringSortedLinkedList.add(testStrings.get(i));
      addString += num + ": " + testStrings.get(i) + ", ";
    }
    stringSortedLinkedList.add(testStrings.get(3));
    addString += "4: " + testStrings.get(3) + "]";
    System.out.println("\nTEST 1: Adding strings without repetition");
    System.out.println("Added: " + addString);
    System.out.println("Result:" + stringSortedLinkedList.toString());

    //TEST 2 -> Adding strings with repetition
    addString = "[";
    for (int i = 1; i < 15; i++) {
      stringSortedLinkedList.add(testStrings.get(i));
      addString += i + ": " + testStrings.get(i) + ", ";
    }
    stringSortedLinkedList.add(testStrings.get(16));
    addString += "15: " + testStrings.get(16) + "]";
    System.out.println("\nTEST 2: Adding more strings with repetition");
    System.out.println("Added: " + addString);
    System.out.println("Result: " + stringSortedLinkedList.toString());

    //TEST 3 -> Removing string Node
    stringSortedLinkedList.remove("Davi");
    System.out.println("\nTEST 3: Removing the string 'Davi'");
    System.out.println(stringSortedLinkedList.toString());

    //TEST 4 -> Attempting to remove Node
    System.out.println("\nTEST 4: Attempting to remove non-existing String = 'Tommy'");
    System.out.println("Operation returns: " + stringSortedLinkedList.remove("Tommy"));
    //System.out.println( stringSortedLinkedList.remove(0)); //Uncomment beginning of line to test exception message.


    //---- INTEGER TESTS ----//

    //TEST 5 -> Adding integers without repetition
    addString = "[";
    for (int i = 0; i < 3; i++) {
      num = i + 1;
      integerSortedLinkedList.add(testNumbers.get(i));
      addString += num + ": " + testNumbers.get(i) + ", ";
    }
    integerSortedLinkedList.add(testNumbers.get(3));
    addString += "4: " + testNumbers.get(3) + "]";
    System.out.println("\nTEST 5: Adding integers without repetition");
    System.out.println("\tAdded: " + addString);
    System.out.println("\tResult:" + integerSortedLinkedList.toString());

    //TEST 6 -> Adding integers with repetition
    addString = "[";
    for (int i = 1; i < 15; i++) {
      integerSortedLinkedList.add(testNumbers.get(i));
      addString += i + ": " + testNumbers.get(i) + ", ";
    }
    integerSortedLinkedList.add(testNumbers.get(16));
    addString += "15: " + testNumbers.get(16) + "]";
    System.out.println("\nTEST 6: Adding more integers with repetition");
    System.out.println("\tAdded: " + addString);
    System.out.println("\tResult: " + integerSortedLinkedList.toString());

    //TEST 7 -> Removing integer Node
    System.out.println("\nTEST 7: Removing the repeated integer = " + testNumbers.get(2));
    integerSortedLinkedList.remove(testNumbers.get(2));
    System.out.println(integerSortedLinkedList);

    //TEST 8 -> Attempting to remove Node
    System.out.println("\nTEST 8: Attempting to remove non-existing integer = 20");
    System.out.println("\tOperation returns: " + integerSortedLinkedList.remove(20));


    //---- PERFORMANCE TESTS: SortedLinkedList VS. ArrayList ----//

    //TEST 9 -> SortedLinkedList.add() vs. ArrayList.add() + ArrayList.sort().
    System.out.println("\nTEST 9: Timed performance of SortedLinkedList.add() vs. ArrayList.add() + ArrayList.sort()");
    SortedLinkedList<String> sortedNames_LinkedList = new SortedLinkedList<>();
    ArrayList<String> sortedNames_ArrayList = new ArrayList<>();

    long startTime = System.nanoTime();
    for (String name : testStrings)
      sortedNames_LinkedList.add(name);
    double linkedListTime = (double) (System.nanoTime() - startTime) / 1000;
    System.out.println("\tTime to add/sort " + testStrings.size() + " Strings for a SortedLinkedList: " +
        linkedListTime + " microseconds");

    startTime = System.nanoTime();
    for (String name : testStrings) {
      sortedNames_ArrayList.add(name);
      Collections.sort(sortedNames_ArrayList);
    }
    double arrayListTime = (double) (System.nanoTime() - startTime) / 1000;
    System.out.println("\tTime to add/sort " + testStrings.size() + " Strings for an ArrayList: " +
        arrayListTime + " microseconds");

    System.out.printf("\tSortedLinkedList was %1.2f times faster than ArrayList\n", arrayListTime / linkedListTime);

    SortedLinkedList<String> sortedNames_LinkedList_Short = new SortedLinkedList<>();
    ArrayList<String> sortedNames_ArrayList_Short = new ArrayList<>();

    startTime = System.nanoTime();
    for (int i = 0; i < 100; i++)
      sortedNames_LinkedList_Short.add(testStrings.get(i));
    linkedListTime = (double) (System.nanoTime() - startTime) / 1000;
    System.out.println("\n\tTime to add/sort 50 Strings for a SortedLinkedList: " +
        linkedListTime + " microseconds");

    startTime = System.nanoTime();
    for (int i = 0; i < 100; i++) {
      sortedNames_ArrayList_Short.add(testStrings.get(i));
      Collections.sort(sortedNames_ArrayList);
    }
    arrayListTime = (double) (System.nanoTime() - startTime) / 1000;
    System.out.println("\tTime to add/sort 50 Strings for an ArrayList: " +
        arrayListTime + " microseconds");

    System.out.printf("\tSortedLinkedList was %1.2f times faster than ArrayList\n", arrayListTime / linkedListTime);

    //TEST 10 -> SortedLinkedList.remove() vs. ArrayList.remove() for first, middle, and last element
    System.out.println("\nTEST 10A: Timed performance of SortedLinkedList.remove() vs. ArrayList.remove() for large list");
    //Need to find the first, middle, and last element.
    String first;
    String middle;
    String last;

    // Perform removal from both large lists for 30 iterations.
    double firstLinked = 0, middleLinked = 0, lastLinked = 0, firstArray = 0, middleArray = 0, lastArray = 0;
    for (int i = 0; i < 30; i++) {
      first = sortedNames_ArrayList.get(0);
      middle = sortedNames_ArrayList.get(sortedNames_ArrayList.size() / 2);
      last = sortedNames_ArrayList.get(sortedNames_ArrayList.size() - 1);

      startTime = System.nanoTime();
      sortedNames_LinkedList.remove(first);
      firstLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_LinkedList.remove(middle);
      middleLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_LinkedList.remove(last);
      lastLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList.remove(first);
      firstArray += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList.remove(middle);
      middleArray += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList.remove(last);
      lastArray += (double) (System.nanoTime() - startTime) / 1000;
    }

    System.out.println("\tAverage time (30 iterations) to remove first String from SortedLinkedList: " + firstLinked/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove middle String from SortedLinkedList: " + middleLinked/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove last String from SortedLinkedList: " + lastLinked/30 + " microseconds");

    System.out.println("\n\tAverage time (30 iterations) to remove first String from ArrayList: " + firstArray/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove middle String from ArrayList: " + middleArray/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove last String from ArrayList: " + lastArray/30 + " microseconds");

    System.out.println("\n\tlinkedList is " + firstArray/firstLinked + " times faster than ArrayList for removing the " +
        "first element from a list of 18500 elements");
    System.out.println("\tlinkedList is " + middleArray/middleLinked + " times faster than ArrayList for removing the " +
        "middle element from a list of 18500 elements");
    System.out.println("\tlinkedList is " + lastArray/lastLinked + " times faster than ArrayList for removing the " +
        "last element from a list of 18500 elements");

    System.out.println("\nTEST 10B: Timed performance of SortedLinkedList.remove() vs. ArrayList.remove() for short list");
    // Perform removal from both short lists for 30 iterations.
    firstLinked = 0; middleLinked = 0; lastLinked = 0; firstArray = 0; middleArray = 0; lastArray = 0;
    for (int i = 0; i < 12; i++) {

      //Since list is short, must re-initialize each time for testing.
      sortedNames_LinkedList_Short = new SortedLinkedList<>();
      sortedNames_ArrayList_Short = new ArrayList<>();

      for (int j = 0; j < 50; j++)
        sortedNames_LinkedList_Short.add(testStrings.get(j));

      for (int j = 0; j < 50; j++) {
        sortedNames_ArrayList_Short.add(testStrings.get(j));
        Collections.sort(sortedNames_ArrayList);
      }

      first = sortedNames_ArrayList_Short.get(0);
      middle = sortedNames_ArrayList_Short.get(sortedNames_ArrayList_Short.size() / 2);
      last = sortedNames_ArrayList_Short.get(sortedNames_ArrayList_Short.size() - 1);

      startTime = System.nanoTime();
      sortedNames_LinkedList_Short.remove(first);
      firstLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_LinkedList_Short.remove(middle);
      middleLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_LinkedList_Short.remove(last);
      lastLinked += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList_Short.remove(first);
      firstArray += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList_Short.remove(middle);
      middleArray += (double) (System.nanoTime() - startTime) / 1000;

      startTime = System.nanoTime();
      sortedNames_ArrayList_Short.remove(last);
      lastArray += (double) (System.nanoTime() - startTime) / 1000;
    }
    System.out.println("\tAverage time (30 iterations) to remove first String from a short SortedLinkedList " +
        "(50 elements): " + firstLinked/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove middle String from a short SortedLinkedList " +
        "(50 elements): " + middleLinked/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove last String from a short SortedLinkedList " +
        "(50 elements): " + lastLinked/30 + " microseconds");

    System.out.println("\n\tAverage time (30 iterations) to remove first String from a short ArrayList " +
        "(50 elements): " + firstArray/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove middle String from a short ArrayList " +
        "(50 elements): " + middleArray/30 + " microseconds");
    System.out.println("\tAverage time (30 iterations) to remove last String from a short ArrayList " +
        "(50 elements): " + lastArray/30 + " microseconds");

    System.out.println("\n\tlinkedList is " + firstArray/firstLinked + " times faster than ArrayList for removing the " +
        "first element in a list of 50 elements");
    System.out.println("\tlinkedList is " + middleArray/middleLinked + " times faster than ArrayList for removing the " +
        "middle element in a list of 50 elements");
    System.out.println("\tlinkedList is " + lastArray/lastLinked + " times faster than ArrayList for removing the " +
        "last element in a list of 50 elements");
  }
}
