public class PrimitiveTypesAndStrings
  {

  public static void main(String[] args) {

    String myString = "This is a string";
    System.out.println("myString = " + myString);

    myString = myString + ", and this is more.";
    System.out.println("myString = " + myString);

    // Primitive data types in mixed expressions assigned to a String are
    // converted to a String before the expression is handled.
    // EXAMPLE:
    String lastString = "10";
    int myInt = 50;
    lastString = lastString + myInt;
    System.out.println("lastString has the value: " + lastString);

  }
}
