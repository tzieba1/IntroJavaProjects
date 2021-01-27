/**
 * I, Tommy Zieba, 000797152 certify that this material is my original work. No other person's work has been used
 * without due acknowledgement.
 */

package com.tzieba;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
  /**
   * Enumerator used for PART B which lists any Names of characters for testing proximity to the ring.
   */
  public enum Name {
    frodo, sam, bilbo, gandalf, boromir, aragorn, legolas, gollum,
    pippin, merry, gimli, sauron, saruman, faramir, denethor, treebeard, elrond, galadriel
  }

  public static void main(String[] args) {
    /* PART B INITIALIZATION */
    ArrayList<BookCharacter> bookCharacters = new ArrayList<>();
    TheRing theRing = new TheRing();
    for (Name name : Name.values()) {
      bookCharacters.add(new BookCharacter(name.toString()));
    }


    /* PART A INITIALIZATION */
    //  Object created to store every BookWork appearing in a text file only once (with a count in the BookWord class)
    ArrayList<BookWord> bookWords = new ArrayList<>();
    //  Object created to store every word in the dictionary from the US.txt file as a BookWord.
    ArrayList<BookWord> dictionaryWords = new ArrayList<>();
    // Initialize the filepath for the book and dictionary.
    final String bookFileName = "src/com/tzieba/TheLordOfTheRings.txt";
    final String dictionaryFileName = "src/com/tzieba/US.txt";


    /*  FILE READING INITIALIZATION  */
    long partAStart = System.nanoTime();
    //  Read the files for the book and dictionary with some additional steps taken to prepare for PART A and PART B.
    int bookWordCount = 0;
    int dictionaryWordCount = 0;
    int totalWordCount = 0;
    try {
      Scanner bookScanner = new Scanner(new File(bookFileName));
      bookScanner.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
      Scanner dictionaryScanner = new Scanner(new File(dictionaryFileName));
      dictionaryScanner.useDelimiter("\n");
      while (bookScanner.hasNext() || dictionaryScanner.hasNext()) {
        if (bookScanner.hasNext()) {
          BookWord bookWord = new BookWord(bookScanner.next().toLowerCase());
          if (bookWord.getText().length() > 0) {
            totalWordCount++;
            //  If a bookWord has already been added, increment its count. Otherwise, add the bookWord to bookWords.
            if (bookWords.contains(bookWord)) {
              bookWords.get(bookWords.indexOf(bookWord)).incrementCount();
            } else {
              bookWord.incrementCount();
              bookWords.add(bookWord);
              bookWordCount++;
            }
            // Iterate through bookCharacters to check if bookWord is equal to an enumerated BookCharacter name.
            breakLoop:
            for (BookCharacter bookCharacter : bookCharacters) {
              //If a bookWord is Enum.Name, then add the position to the positions for that bookCharacter.
              if (bookCharacter.getName().equals(bookWord.getText())) {
                bookCharacter.addPosition(totalWordCount);
                break breakLoop;
              }
            }
            //If a bookWord is 'ring', then add the position to the positions for theRing.
            if (bookWord.getText().equals("ring"))
              theRing.addPosition(totalWordCount);
          }
        }
        if (dictionaryScanner.hasNext()) {
          String dictionaryWord = dictionaryScanner.next().toLowerCase();
          if (dictionaryWord.length() > 0) {
            //  Add each word from the dictionary file to the dictionary words ArrayList.
            dictionaryWords.add(new BookWord(dictionaryWord));
            dictionaryWordCount++;
          }
        }
      }
      bookScanner.close();
      dictionaryScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
    }


    //  PART A - HASHING AND SEARCH PERFORMANCE
    System.out.println("\nFILE ANALYSIS:");
    System.out.println("There are " + bookWordCount + " words in the file: " + bookFileName);
    System.out.println("There are " + dictionaryWordCount + " words in the file: " + dictionaryFileName);

    //  Sorting the dictionary by text to be hashed.
    Collections.sort(dictionaryWords, (t1, t2) -> t1.getText().compareTo(t2.getText()));

    //  Determining the number of unique words with a count of 1 in the ArrayList of BookWords.
    int uniqueWords = 0;
    for (BookWord bookWord : bookWords) {
      if (bookWord.getCount() == 1)
        uniqueWords++;
    }
    System.out.println("Number of unique words = " + uniqueWords);

    // Search for top 10 most frequent words and associated counts using a lambda expression only depending on count.
    // Using a two-keyed sorting algorithm by implementing a lambda function that compares based first on count,
    // and based second on the text for each BookWord (i.e. two-keyed comparison).
    long start = System.nanoTime();
    Collections.sort(bookWords, (t1, t2) -> {
      int totalOrder;
      int countOrder = t1.getCount().compareTo(t2.getCount());
      int textOrder = t1.getText().compareTo(t2.getText());
      // Condition for multi-keyed ordering where first object is ordered BEFORE the second (has lesser order).
      if (countOrder == 0)
        totalOrder = textOrder;
      else
        totalOrder = countOrder;
      return totalOrder;
    });
    long end = System.nanoTime();
    System.out.println("Time to sort book words = " + (double) (end - start) / 1000000 + " ms");
    System.out.println("\n TOP TEN WORDS:");
    for (int i = 1; i < 11; i++)
      System.out.println(i + ". " + bookWords.get(bookWords.size() - i).toString());

    //  Search for words with count of 64 (listed alphabetically)
    System.out.println("\n WORDS OCCURRING 64 TIMES:");
    for (BookWord bookWord : bookWords) {
      if (bookWord.getCount() == 64) {
        System.out.println(bookWord.toString());
      }
    }

    // Search for words not in the dictionary with ArrayList.contains() and measure time.
    start = System.nanoTime();
    int misspelledWordCount = 0;
    for (BookWord bookWord : bookWords)
      if (!dictionaryWords.contains(bookWord))
        misspelledWordCount++;
    end = System.nanoTime();
    double linearSearchTime = (double) (end - start) / 1000000;
    System.out.println("\n LINEAR SEARCH:\nArrayList.contains() method took " + linearSearchTime +
        " ms to find " + misspelledWordCount + " misspelled words");

    // Search for words not in the dictionary with Collections.binarySearch() and measure time.
    start = System.nanoTime();
    misspelledWordCount = 0;
    for (BookWord bookWord : bookWords)
      if (Collections.binarySearch(dictionaryWords, bookWord, (t1, t2) -> t1.getText().compareTo(t2.getText())) < 0)
        misspelledWordCount++;
    end = System.nanoTime();
    double binarySearchTime = (double) (end - start) / 1000000;
    System.out.println("\n BINARY SEARCH:\nCollections.binarySearch() method took " + binarySearchTime +
        " ms to find " + misspelledWordCount + " misspelled words");

    //  Creating a SimpleHashSet of dictionary book words.
    start = System.nanoTime();
    SimpleHashSet<BookWord> dictionaryHashed = new SimpleHashSet<>();
    for (BookWord word : dictionaryWords)
      dictionaryHashed.insert(word);
    end = System.nanoTime();
    double hashTime = (double) (end - start) / 1000000;

    // Search for words not in the dictionary with SimpleHashSet.contains() and measure time.
    start = System.nanoTime();
    misspelledWordCount = 0;
    for (BookWord bookWord : bookWords)
      if (!dictionaryHashed.contains(bookWord))
        misspelledWordCount++;
    end = System.nanoTime();
    double hashedSearchTime = (double) (end - start) / 1000000;

    System.out.println("\n HASHSET SEARCH:\nSimpleHashSet.contains() method took " + hashedSearchTime +
        " ms to find " + misspelledWordCount + " misspelled words");
    System.out.println("Time to hash the dictionary = " + hashTime + " ms");
    System.out.println("Number of buckets = " + dictionaryHashed.getNumberOfBuckets());
    System.out.println("Largest bucket size = " + dictionaryHashed.getLargestBucketSize());
    System.out.println("Number of empty buckets = " + dictionaryHashed.getNumberOfEmptyBuckets());
    System.out.println("% empty buckets = " +
        (double) dictionaryHashed.getNumberOfEmptyBuckets() / dictionaryHashed.getNumberOfBuckets() * 100);

    System.out.println("\nRatio of Linear to Hash = " + linearSearchTime / hashedSearchTime);
    System.out.println("Ratio of Binary to Hash = " + binarySearchTime / hashedSearchTime);
    System.out.println("\nTime for PART A = " + (double) (System.nanoTime() - partAStart) / 1000000 + " ms\n");


    //  PART B - PROXIMITY SEARCH
    long startPartB = System.nanoTime();
    final int cutOff = 42;  //Proximity cutoff.
    ArrayList<Integer> ringPositions = theRing.getPositions();
    //  Iterate through positions for each character or word of interest in the book.
    for (BookCharacter bookCharacter : bookCharacters) {
      ArrayList<Integer> characterPositions = bookCharacter.getPositions();
      //First, loop through ringPositions
      for (int ringPosition : ringPositions) {
        //Second, loop through characterPositions
        for (int characterPosition : characterPositions) {
          //Check the proximity for every characterPosition with each ringPosition
          if (ringPosition - characterPosition + cutOff < 0) {
            break;  //Save time iterating through unnecessary characterPositions (positions are always increasing)
          } else if (Math.abs(ringPosition - characterPosition) <= cutOff) {
            bookCharacter.incrementClosenessCount();
          }
        }
      }
      System.out.println(bookCharacter.toString());
    }
    System.out.println("\nTime for PART B = " + (double) (System.nanoTime() - startPartB)/1000000 + " ms");
  }
}
