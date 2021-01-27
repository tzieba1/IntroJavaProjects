package com.tzieba;

public class BookWord {

  private String text;
  private Integer count;

  public BookWord(String wordText) {
    this.count = 0;
    this.text = wordText;
  }

  public String getText() {
    return text;
  }

  public Integer getCount() {
    return count;
  }

  public void incrementCount() {
    count++;
  }

  @Override
  public boolean equals(Object wordToCompare) {
    //TODO: implement an equals method.
    if (wordToCompare != null && this.getClass() == wordToCompare.getClass()) {
      BookWord bookWord = (BookWord) wordToCompare;
      if (bookWord.getText().equals(text)) return true;
    }
    return false;
  }

  /**
   * Taken from https://www. geeksforgeeks.org/string-hashing-using-polynomial-rolling-hash-function/
   * @return
   */
  @Override
  public int hashCode() {
    int p = 23;
    int m = (int)Math.pow(10, 9) + 9;
    long power_of_p = 1;
    long hCode = 0;

    for (int i = 0; i < text.length(); i++) {
      //  Note that characters automatically get cast as integers when used with integer operators.
      hCode = (hCode + (text.charAt(i) - 'a' + 1) * power_of_p) % m;
      power_of_p = (power_of_p * p) % m;
    }
    // Can cast safely from long to int because of integer, m, used with modulus operator.
    return (int) hCode;
  }

  @Override
  public String toString() {
    return String.format("[word: %s, count: %d]", text, count);
  }
}
