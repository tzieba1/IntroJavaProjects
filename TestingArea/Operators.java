public class Operators
  {

  public static void main(String[] args) {
    int result = 1 + 2;
    System.out.println("1 + 2 = " + result);

    int previousResult = result;
    result--;
    System.out.println(previousResult + " - 1 = " + result);

    previousResult = result;

    result *= 10;
    System.out.println(previousResult + " * 10 = " + result);

    previousResult = result;

    result /= 5;
    System.out.println(previousResult + " / 5 = " + result);

    previousResult = result;
    result %= 3;
    System.out.println(previousResult + " % 3 = " +result);

    boolean isAlien = false;
    if (isAlien == false)
      System.out.println("It is not an alien!");

    boolean isCar = false;
    if (isCar == true)
      System.out.println("This is not supposed to happen");

    // The ternary operator tests a boolean and assigns the first value before
    // colon  or otherwise assigns the value after the colon.

    // isCar = true;
    boolean wasCar = isCar ? true : false;
    if (wasCar)
      System.out.println("wasCar is true");
  }
}
