package InstanceOfSubAndSuperClass;

public class View
  {
  public static void main(String[] args)
    {
    Super superclass_object = new SubSub();
    Super other_superclass_object = new OtherSubSub();
    
    String[] testString = {"Ay", "Bee", "Cee"};
    System.out.println(testString[0]);
    System.out.println(testString[1]);
    System.out.println(testString[2]);
    
    System.out.println(testString.length);
    }
  }
