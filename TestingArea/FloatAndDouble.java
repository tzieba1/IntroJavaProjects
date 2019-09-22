public class FloatAndDouble
  {

  public static void main(String[] args) {
    int myIntValue = 5 / 3;
    float myFloatValue = 5f / 3f;

    // Java assumes that literals with a decimal are of type double
    // could rewrite as "double myDoubleValue = 5.0;" below
    double myDoubleValue = 5d / 3d;
    System.out.println("myIntValue = " + myIntValue);
    System.out.println("myFloatValue = " + myFloatValue);
    System.out.println("myDoubleValue = " + myDoubleValue);
  }
}
