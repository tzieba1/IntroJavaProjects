/**
 * ASSIGNMENT #2 - Answers to Part 6 from Requirements
 *
 * o  The sorting methods defined in Main have the following algorithms:
 *        aSort = Quick Sort (recursive with midpoint as pivot)
 *        bSort = Selection Sort
 *        cSort = Insertion Sort
 *        dSort = Merge Sort (recursive)
 *        eSort = Bubble Sort
 *
 * o  The algorithm that has the best speed performance is based on the output of the conductAverageTimeAnalysis()
 *    method used in main(). To conduct a fair performance analysis with iterative testing, 40 iterations has been
 *    chosen. When running the program with 20 iterations there were occasionally differences in speed ranking. In the
 *    case of an array with size of 30 and 20 iterations, Insertion sort algorithm was occasionally faster than Quick
 *    sort, but the times were very close to each other. After doubling the iterations to 40 this did not occur. Note
 *    that if assuming a normal distribution then at least 30 samples (iterations) are needed for a meaningful average,
 *    based on the Central Limit Theorem.
 *
 *    For an array of 30 elements the fastest algorithms (based on average taken over 40 iterations):
 *        1. Quick          [~1,015 ns]
 *        2. Insertion      [~1,510 ns]
 *        3. Merge          [~2,080 ns]
 *        4. Bubble         [~3,735 ns]
 *        5. Selection      [~5,073 ns]
 *        6. Arrays.sort()  [~7,770 ns]
 *
 * o  The algorithm that has the best speed performance is based on the output of the conductAverageTimeAnalysis()
 *    method used in main(). For reasons similar to above, 40 iterations are chosen to be sure of a good estimate of the
 *    average time. The only difference is that there were occasionally differences in speed ranking between
 *    Arrays.sort() and Quick sort. It's suspected that these are similar (or equal) since even after 40 iterations
 *    these two are occasionally oppositely ranked for average runtime (assuming a normal distribution). As a
 *    consequence of 40 iterations and 30000 elements, it takes just over a minute to completely execute this time
 *    analysis. This can easily be tested by adjusting the parameters of the call to conductAverageTimeAnalysis() in
 *    the main() method.
 *
 *    For an array of 30000 elements the fastest algorithms (based on average taken over 40 iterations):
 *        1. Arrays.sort()  [~2,231,488 ns]
 *        2. Quick          [~2,914,643 ns]
 *        3. Merge          [~5.022,005 ns]
 *        4. Insertion      [~88,718,130 ns]
 *        5. Selection      [~186,416,103 ns]
 *        6. Bubble         [~1,361,503,623 ns]
 *
 * o  Time complexity in Big-Oh notation for each algorithm is as follows:
 *        1. Quick Sort      ->  O(nlog(n)) average, O(n^2) worst  ->  30000*log(30000)  = ~446,180      comparisons
 *        2. Selection Sort  ->  O(n^2) [worst = average]          ->  30000^2           = 900,000,000   comparisons
 *        3. Insertion Sort  ->  O(n^2) [worst = average]          ->  30000^2           = 900,000,000   comparisons
 *        4. Merge Sort      ->  O(nlog(n)) [worst = average]      ->  30000*log(30000)  = ~446,180      comparisons
 *        5. Bubble Sort     ->  O(n^2) [worst = average]          ->  30000^2           = 900,000,000   comparisons
 *
 *    The results of the average number of comparisons from the conductAverageTimeAnalysis() method with 40 iterations
 *    and an array of size 30000 are as follows:
 *        1.  Quick       ->  9,780,903   comparisons
 *        2.  Selection   ->  39,488,270  comparisons
 *        3.  Insertion   ->  29,999      comparisons
 *        4.  Merge       ->  408,484     comparisons
 *        5.  Bubble      ->  20,488,270  comparisons
 *
 *    These results show that the Selection, Insertion, and Bubble sorting algorithms do not surpass near their worst
 *    case as expected. The Merge sort algorithm has equal worst and average case and so it does not pass this as well,
 *    which is expected. On the other hand, the Quick sort passes the average case by about 20,900% which would indicate
 *    that sorting 30000 integers with Quick sort lies somewhere between the average and worst case. Note that this is
 *    still only 1.087% of the worst case - so larger arrays can be taken on efficiently. Merge sort is fairly efficient
 *    as well, but will reach its limit far sooner than Quick sort with respect to array size.
 *
 * o  The algorithm that has the best performance of the basic step is based on the output of the
 *    conductAverageTimeAnalysis() method used in main(). An average is taken over several iterations of counting each
 *    sorting algorithms number of comparisons while timing the algorithm runtime. This does not have an impact on
 *    my rankings here of fastest algorithm when using an array of size 30 or 30000 - since my choice is based strictly
 *    on average runtime. But, there is indeed a relationship between the number of comparisons required per algorithm
 *    and the resulting runtime when array size is small vs. large (e.g. 30 vs. 30000 tested here). For this reason, one
 *    may choose a specific sorting algorithm over another based on this performance and expected size of array to sort.
 *
 *    For 40 iterations on an array of size 30, the basic step time ranking is as follows:
 *        1. Quick          [~4.6 ns]
 *        2. Bubble         [~10.6 ns]
 *        3. Selection      [~16.3 ns]
 *        4. Merge          [~37.9 ns]
 *        5. Insertion      [~69.6 ns]
 *
 *    For 40 iterations on an array of size 30000, the basic step time ranking is as follows:
 *        1. Quick          [~0.2 ns]
 *        2. Selection      [~0.5 ns]
 *        3. Bubble         [~3.0 ns]
 *        4. Merge          [~12.5 ns]
 *        5. Insertion      [~2923.3 ns]
 *
 * o  Based on the results of timing each sorting algorithm above, it appears that the Quick sort is much like the
 *    Arrays.sort() method provided by Java. The average runtime behaviour can be analyzed further by comparing
 *    results between arrays of size 30 and 30000 (for sufficiently large sample sizes, i.e. iterations). In the limit
 *    as the size goes to infinity (tested up to 30000 here) it appears that these two approach a similar value for the
 *    average runtime. The difference in these two algorithms is seen for small array sizes, where Quick Sort has a
 *    runtime that is approximately 7x better on average.
 */

package com.tzieba;

import java.util.Arrays;

public class Main
{
  /**
   * The swap method swaps the contents of two elements in an int array.
   *
   * @param array containing the two elements.
   * @param a     The subscript of the first element.
   * @param b     The subscript of the second element.
   */
  private static void swap(int[] array, int a, int b)
  {
    int temp;
    
    temp = array[a];
    array[a] = array[b];
    array[b] = temp;
    
  }
  
  /**
   * A recursive implementation of the Quick Sort Algorithm.
   *
   * @param array an unsorted array that will be sorted upon method completion
   * @return
   */
  public static int aSort(int array[], Counter counter)
  {
    //Return the result of the recursive count - finally ending with total count returned.
    return doASort(array, 0, array.length - 1, counter);
  }
  
  /**
   * The doASort method uses a recursive algorithm to sort each half of an array and its sub-arrays. Note that the
   * pivot can be chosen differently, but it is commonly implemented to partition each recursively partitioned array
   * at their respective midpoints (beginning with the array to be sorted).
   *
   * @param array The array to sort.
   * @param start The starting subscript of the list to sort
   * @param end   The ending subscript of the list to sort
   */
  private static int doASort(int array[], int start, int end, Counter counter)
  {
    int pivotPoint;
    
    if (start < end)
    {
      // Get the pivot point.
      pivotPoint = part(array, start, end, counter);
      // Note - only one +/=
      // Sort the first sub list.
      doASort(array, start, pivotPoint - 1, counter);
      
      // Sort the second sub list.
      doASort(array, pivotPoint + 1, end, counter);
    }
    return counter.getCount();
  }
  
  /**
   * The partition method selects a pivot value in an array and arranges the
   * array into two sub lists. All the values less than the pivot will be
   * stored in the left sub list and all the values greater than or equal to
   * the pivot will be stored in the right sub list.
   *
   * @param array The array to partition.
   * @param start The starting subscript of the area to partition.
   * @param end   The ending subscript of the area to partition.
   * @return The subscript of the pivot value.
   */
  private static int part(int array[], int start, int end, Counter counter)
  {
    int pivotValue;    // To hold the pivot value
    int endOfLeftList; // Last element in the left sub list.
    int mid;           // To hold the mid-point subscript
    
    // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
    // for discussion of middle point
    // Find the subscript of the middle element.
    // This will be our pivot value.
    mid = (start + end) / 2;
    
    // Swap the middle element with the first element.
    // This moves the pivot value to the start of
    // the list.
    swap(array, start, mid);
    
    // Save the pivot value for comparisons.
    pivotValue = array[start];
    
    // For now, the end of the left sub list is
    // the first element.
    endOfLeftList = start;
    
    // Scan the entire list and move any values that
    // are less than the pivot value to the left
    // sub list.
    for (int scan = start + 1; scan <= end; scan++)
    {
      //Incrementing the static Counter object that has been passed around by reference.
      counter.increment();
      if (array[scan] < pivotValue)
      {
        endOfLeftList++;
        swap(array, endOfLeftList, scan);
      }
    }
    
    // Move the pivot value to end of the
    // left sub list.
    swap(array, start, endOfLeftList);
    
    // Return the subscript of the pivot value.
    return endOfLeftList;
  }
  
  /**
   * An implementation of the Selection Sort Algorithm.
   *
   * @param array an unsorted array that will be sorted upon method completion
   * @return
   */
  
  public static int bSort(int[] array, Counter counter)
  {
    int startScan;   // Starting position of the scan
    int index;       // To hold a subscript value
    int minIndex;    // Element with smallest value in the scan
    int minValue;    // The smallest value found in the scan
    
    // The outer loop iterates once for each element in the
    // array. The startScan variable marks the position where
    // the scan should begin.
    for (startScan = 0; startScan < (array.length - 1); startScan++)
    {
      // Assume the first element in the scannable area
      // is the smallest value.
      minIndex = startScan;
      minValue = array[startScan];
      
      // Scan the array, starting at the 2nd element in
      // the scannable area. We are looking for the smallest
      // value in the scannable area.
      for (index = startScan + 1; index < array.length; index++)
      {
        counter.increment();
        if (array[index] < minValue)
        {
          minValue = array[index];
          minIndex = index;
        }
      }
      
      // Swap the element with the smallest value
      // with the first element in the scannable area.
      array[minIndex] = array[startScan];
      array[startScan] = minValue;
    }
    return counter.getCount();
  }
  
  /**
   * An implementation of the Insertion Sort Algorithm.
   *
   * @param array an unsorted array that will be sorted upon method completion
   */
  public static int cSort(int[] array, Counter counter)
  {
    int unsortedValue;  // The first unsorted value
    int scan;           // Used to scan the array
    
    // The outer loop steps the index variable through
    // each subscript in the array, starting at 1. The portion of
    // the array containing element 0  by itself is already sorted.
    for (int index = 1; index < array.length; index++)
    {
      // The first element outside the sorted portion is
      // array[index]. Store the value of this element
      // in unsortedValue.
      unsortedValue = array[index];
      
      // Start scan at the subscript of the first element
      // outside the sorted part.
      scan = index;
      
      counter.increment();
      
      // Move the first element in the still unsorted part
      // into its proper position within the sorted part.
      while (scan > 0 && array[scan - 1] > unsortedValue)
      {
        array[scan] = array[scan - 1];
        scan--;
      }
      
      // Insert the unsorted value in its proper position
      // within the sorted subset.
      array[scan] = unsortedValue;
    }
    return counter.getCount();
  }
  
  
  /**
   * An implementation of the Merge Sort algorithm.
   *
   * @param array the unsorted elements - will be sorted on completion
   */
  public static int dSort(int array[], Counter counter)
  {
    int length = array.length;
    return doDSort(array, 0, length - 1, counter);
  }
  
  /**
   * private recursive method that splits array until we start at array sizes of 1
   *
   * @param array       starting array
   * @param lowerIndex  lower bound of array to sort
   * @param higherIndex upper bound of array to sort
   */
  
  private static int doDSort(int[] array, int lowerIndex, int higherIndex, Counter counter)
  {
    if (lowerIndex < higherIndex)
    {
      int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
      // Below step sorts the left side of the array
      doDSort(array, lowerIndex, middle, counter);
      // Below step sorts the right side of the array
      doDSort(array, middle + 1, higherIndex, counter);
      // Now put both parts together
      part1(array, lowerIndex, middle, higherIndex, counter);
    }
    return counter.getCount();
  }
  
  /**
   * Puts two smaller sorted arrays into one sorted array
   *
   * @param array       provided in two sorted parts (1,4,9,2,3,11)
   * @param lowerIndex  the location of the first index
   * @param middle      - the middle element
   * @param higherIndex - the upper bound of the sorted elements
   */
  private static void part1(int[] array, int lowerIndex, int middle, int higherIndex, Counter counter)
  {
    
    int[] mArray = new int[higherIndex - lowerIndex + 1];
    for (int i = lowerIndex; i <= higherIndex; i++)
    {
      mArray[i - lowerIndex] = array[i];
    }
    // Part A - from lowerIndex to middle
    // Part B - from middle + 1 to higherIndex
    int partAIndex = lowerIndex;
    int partBIndex = middle + 1;
    int m = lowerIndex;
    while (partAIndex <= middle && partBIndex <= higherIndex)
    {
      counter.increment();
      // Place items back into Array in order
      // Select the lowest of the two elements
      if (mArray[partAIndex - lowerIndex] <= mArray[partBIndex - lowerIndex])
      {
        array[m] = mArray[partAIndex - lowerIndex];
        partAIndex++;
      }
      else
      {
        array[m] = mArray[partBIndex - lowerIndex];
        partBIndex++;
      }
      m++;
    }
    // Copy the remainder of PartA array (if required)
    while (partAIndex <= middle)
    {
      array[m] = mArray[partAIndex - lowerIndex];
      m++;
      partAIndex++;
    }
  }
  
  /**
   * Sorting using the Bubble Sort algorithm.
   *
   * @param array an unsorted array that will be sorted upon method completion
   */
  public static int eSort(int[] array, Counter counter)
  {
    int lastPos;     // Position of last element to compare
    int index;       // Index of an element to compare
    
    // The outer loop positions lastPos at the last element
    // to compare during each pass through the array. Initially
    // lastPos is the index of the last element in the array.
    // During each iteration, it is decreased by one.
    for (lastPos = array.length - 1; lastPos >= 0; lastPos--)
    {
      // The inner loop steps through the array, comparing
      // each element with its neighbor. All of the elements
      // from index 0 through lastPos are involved in the
      // comparison. If two elements are out of order, they
      // are swapped.
      for (index = 0; index <= lastPos - 1; index++)
      {
        counter.increment();
        // Compare an element with its neighbor.
        if (array[index] > array[index + 1])
        {
          // Swap the two elements.
          
          swap(array, index, index + 1);
        }
      }
    }
    return counter.getCount();
  }
  
  /**
   * Print an array to the Console
   *
   * @param A array to be printed
   */
  public static void printArray(int[] A)
  {
    for (int i = 0; i < A.length; i++)
    {
      System.out.printf("%5d ", A[i]);
    }
    System.out.println();
  }
  
  /**
   * This method is used to conduct an analysis for time complexity, runtime, and basic step. These results are
   * calculated and printed to the console for each of the sorting algorithms defined in this file. The analysis is
   * performed on an array of random integers with array size passed in as a parameter. Additionally, the runtime of
   * the Arrays.sort() method is printed to console.
   *
   * @param size size of the array of random integers to be tested
   */
  public static void conductAnalysis(int size)
  {
    Counter counter = new Counter();
    int[] A = new int[size];
    int[] B;
    
    // Create random array with elements in the range of 0 to SIZE - 1;
    for (int i = 0; i < size; i++)
    {
      A[i] = (int) (Math.random() * size);
    }
    
    System.out.printf("\nCOMPARISONS FOR ARRAY SIZE OF %d:\n" +
      "################################################\n", size);
    
    B = Arrays.copyOf(A, A.length);      // Make sure you do this before each call to a sort method
    long startTime = System.nanoTime();
    int sortaCompares = aSort(B, counter);
    long elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for aSort() analysis.
    System.out.printf("Number of compares for aSort     = %10d\n", sortaCompares);
    System.out.printf("Time required for aSort          = %10d ns\n", elapsedTime);
    System.out.printf("Basic Step for aSort             =     %6.1f ns\n" +
      "-------------------------------------------------\n", (double) elapsedTime / sortaCompares);
    
    counter.reset();  //Reset the comparison counter for the next algorithm.
    B = Arrays.copyOf(A, A.length);
    startTime = System.nanoTime();
    int sortbCompares = bSort(B, counter);
    elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for bSort() analysis.
    System.out.printf("Number of compares for bSort     = %10d\n", sortbCompares);
    System.out.printf("Time required for bSort          = %10d ns\n", elapsedTime);
    System.out.printf("Basic Step for bSort             =     %6.1f ns\n" +
      "-------------------------------------------------\n", (double) elapsedTime / sortbCompares);
    
    counter.reset();  //Reset the comparison counter for the next algorithm.
    B = Arrays.copyOf(A, A.length);
    startTime = System.nanoTime();
    int sortcCompares = cSort(B, counter);
    elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for cSort() analysis.
    System.out.printf("Number of compares for cSort     = %10d\n", sortcCompares);
    System.out.printf("Time required for cSort          = %10d ns\n", elapsedTime);
    System.out.printf("Basic Step for cSort             =     %6.1f ns\n" +
      "-------------------------------------------------\n", (double) elapsedTime / sortcCompares);
    
    counter.reset();  //Reset the comparison counter for the next algorithm.
    B = Arrays.copyOf(A, A.length);
    startTime = System.nanoTime();
    int sortdCompares = dSort(B, counter);
    elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for dSort() analysis.
    System.out.printf("Number of compares for dSort     = %10d\n", sortdCompares);
    System.out.printf("Time required for dSort          = %10d ns\n", elapsedTime);
    System.out.printf("Basic Step for dSort             =     %6.1f ns\n" +
      "-------------------------------------------------\n", (double) elapsedTime / sortdCompares);
    
    counter.reset();  //Reset the comparison counter for the next algorithm.
    B = Arrays.copyOf(A, A.length);
    startTime = System.nanoTime();
    int sorteCompares = eSort(B, counter);
    elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for eSort() analysis.
    System.out.printf("Number of compares for eSort     = %10d\n", sorteCompares);
    System.out.printf("Time required for eSort          = %10d ns\n", elapsedTime);
    System.out.printf("Basic Step for eSort             =     %6.1f ns\n" +
      "-------------------------------------------------\n", (double) elapsedTime / sorteCompares);
    
    B = Arrays.copyOf(A, A.length);
    startTime = System.nanoTime();
    Arrays.sort(B);
    elapsedTime = System.nanoTime() - startTime;
  
    //Printing results for Java's Arrays.sort() analysis.
    System.out.printf("Time required for Arrays.sort()  = %10d ns\n" +
      "=================================================\n", elapsedTime);
  }
  
  /**
   * This method is used to conduct an average analysis for time complexity, runtime, and basic step. These results are
   * calculated and printed to the console for each of the sorting algorithms defined in this file. The analysis is
   * performed on an array of random integers with array size passed in as a parameter. The random array is sorted
   * iteratively and an average for each of the time complexities, runtimes, and basic steps is computed per sorting
   * algorithm defined in this file. Additionally, average runtime of the Arrays.sort() method is printed to console.
   *
   * @param size size of the array of random integers to be tested
   * @param iterations number of iterations for computing average results
   */
  public static void conductAverageTimeAnalysis(int size, int iterations)
  {
    //Cumulative total time elapsed over iterations of each sorting algorithm.
    long aTotalTime = 0, bTotalTime = 0, cTotalTime = 0, dTotalTime = 0, eTotalTime = 0, sTotalTime = 0;
    
    //Cumulative basic step time over iterations of each sorting algorithm.
    double aTotalBasic = 0.0, bTotalBasic = 0.0, cTotalBasic = 0.0, dTotalBasic = 0.0, eTotalBasic = 0.0;
  
    //Cumulative comparison count over iterations of each sorting algorithm.
    int aTotalComp = 0, bTotalComp = 0, cTotalComp = 0, dTotalComp = 0, eTotalComp = 0;
    
    Counter counter = new Counter();  //Counter is needed since methods have been re-written to pass a counter object.
    int[] A = new int[size];  //Array to be initialized with random data.
    int[] B;  // A temporary array to store copies of A such that A does not change.
    
    // Create random array with elements in the range of 0 to SIZE - 1;
    for (int i = 0; i < size; i++)
    {
      A[i] = (int) (Math.random() * size);
    }
    
    System.out.printf("\nAVERAGE TIME ANALYSIS FOR AN ARRAY SIZE OF %d AND %d ITERATIONS:\n" +
      "########################################################\n", size, iterations);
    for (int i = 0; i < iterations; i++)
    {
      //Each 'B' is a copy of random array to be sorted so every iteration and sorting algorithm has same initial data.
      B = Arrays.copyOf(A, A.length);
      long startTime = System.nanoTime(); //Start time gets re-used for each method being timed
      int sortaCompares = aSort(B, counter);  //Executes the aSort algorithm with a counter for basic step.
      long elapsedTime = System.nanoTime() - startTime; //Elapsed time gets re-used for each method being timed.
      aTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of aSort.
      aTotalComp += sortaCompares;  //Increment the total number of comparisons for iterations of aSort.
      aTotalBasic += (double) elapsedTime / sortaCompares;  //Increment total basic comparison time for aSort.
      
      counter.reset();  //Reset the comparison counter for the next algorithm.
      B = Arrays.copyOf(A, A.length);
      startTime = System.nanoTime();
      int sortbCompares = bSort(B, counter);  //Executes the bSort algorithm with a counter for basic step.
      elapsedTime = System.nanoTime() - startTime;
      bTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of bSort.
      bTotalComp += sortbCompares;  //Increment the total number of comparisons for iterations of bSort.
      bTotalBasic += (double) elapsedTime / sortbCompares;  //Increment total basic comparison time for bSort.
      
      counter.reset();  //Reset the comparison counter for the next algorithm.
      B = Arrays.copyOf(A, A.length);
      startTime = System.nanoTime();
      int sortcCompares = cSort(B, counter);  //Executes the cSort algorithm with a counter for basic step.
      elapsedTime = System.nanoTime() - startTime;
      cTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of cSort.
      cTotalComp += sortcCompares;  //Increment the total number of comparisons for iterations of cSort.
      cTotalBasic += (double) elapsedTime / sortcCompares;  //Increment total basic comparison time for cSort.
      
      counter.reset();  //Reset the comparison counter for the next algorithm.
      B = Arrays.copyOf(A, A.length);
      startTime = System.nanoTime();
      int sortdCompares = dSort(B, counter);  //Executes the dSort algorithm with a counter for basic step.
      elapsedTime = System.nanoTime() - startTime;
      dTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of dSort.
      dTotalComp += sortdCompares;  //Increment the total number of comparisons for iterations of dSort.
      dTotalBasic += (double) elapsedTime / sortdCompares;  //Increment total basic comparison time for dSort.
      
      counter.reset();  //Reset the comparison counter for the next algorithm.
      B = Arrays.copyOf(A, A.length);
      startTime = System.nanoTime();
      int sorteCompares = eSort(B, counter);  //Executes the eSort algorithm with a counter for basic step.
      elapsedTime = System.nanoTime() - startTime;
      eTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of eSort.
      eTotalComp += sorteCompares;  //Increment the total number of comparisons for iterations of eSort.
      eTotalBasic += (double) elapsedTime / sorteCompares;  //Increment total basic comparison time for eSort.
      
      B = Arrays.copyOf(A, A.length);
      startTime = System.nanoTime();
      Arrays.sort(B);  //Executes Java's Arrays.sort() algorithm without a counter for basic step.
      elapsedTime = System.nanoTime() - startTime;
      sTotalTime += elapsedTime;  //Increment the total time elapsed for iterations of Java's Arrays.sort().
    }
    
    //Printing results for aSort() average time analysis.
    System.out.printf("Average runtime for aSort [Quick]        = %10.0f ns\n" +
                      "Average number of compares for aSort     = %10.0f\n" +
                      "Average time for aSort basic step        =     %6.1f ns\n" +
                      "--------------------------------------------------------\n",
      (double) aTotalTime / iterations, (double)aTotalComp/iterations, aTotalBasic / iterations);
    
    //Printing results for bSort() average time analysis.
    System.out.printf("Average runtime for bSort [Selection]    = %10.0f ns\n" +
                      "Average number of compares for bSort     = %10.0f\n" +
                      "Average time for bSort basic step        =     %6.1f ns\n" +
                      "--------------------------------------------------------\n",
      (double) bTotalTime / iterations, (double)bTotalComp/iterations, bTotalBasic / iterations);
  
    //Printing results for cSort() average time analysis.
    System.out.printf("Average runtime for cSort [Insertion]    = %10.0f ns\n" +
                      "Average number of compares for cSort     = %10.0f\n" +
                      "Average time for cSort basic step        =     %6.1f ns\n" +
                      "--------------------------------------------------------\n",
      (double) cTotalTime / iterations, (double)cTotalComp/iterations, cTotalBasic / iterations);
  
    //Printing results for dSort() average time analysis.
    System.out.printf("Average runtime for dSort [Merge]        = %10.0f ns\n" +
                      "Average number of compares for dSort     = %10.0f\n" +
                      "Average time for dSort basic step        =     %6.1f ns\n" +
                      "--------------------------------------------------------\n",
      (double) dTotalTime / iterations, (double)dTotalComp/iterations, dTotalBasic / iterations);
  
    //Printing results for eSort() average time analysis.
    System.out.printf("Average runtime for eSort [Bubble]       = %10.0f ns\n" +
                      "Average number of compares for eSort     = %10.0f\n" +
                      "Average time for eSort basic step        =     %6.1f ns\n" +
                      "--------------------------------------------------------\n",
      (double) eTotalTime / iterations, (double)eTotalComp/iterations, eTotalBasic / iterations);
  
    //Printing results for Arrays.sort() average time analysis.
    System.out.printf("Average runtime for Arrays.sort()        = %10.0f ns\n" +
                      "========================================================\n", (double) sTotalTime / iterations);
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    System.out.printf("Assignment#2 Sorting and Performance Analysis\n");
    System.out.printf("Completed by Tommy Zieba, 000797152\n");
    
    //Conducting an analysis on random arrays of integers with size being 30, 300, and 30000 that prints to console.
    conductAnalysis(30);
    conductAnalysis(300);
    conductAnalysis(30000);
    
    //Conducting an average analysis with 40 iterations on random arrays of integers with size being 30 and 30000.
    conductAverageTimeAnalysis(30, 40);
    conductAverageTimeAnalysis(30000, 40);
  }
}
