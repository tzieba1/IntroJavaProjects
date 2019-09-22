package InstanceOfSubAndSuperClass;

public abstract class Super
  {
  public Super()
    {
    if ( (this instanceof Sub) && !(this instanceof SubSub) && !(this instanceof OtherSubSub) )
      System.out.print("SUBCLASS INSTANCE");
    else if ( this instanceof SubSub )
      {
      System.out.print("SUBCLASS INSTANCE");
      System.out.print("SUBSUBCLASS INSTANCE");
      }
    else if ( this instanceof OtherSubSub )
      {
      System.out.print("SUBCLASS INSTANCE");
      System.out.print("OTHERSUBSUBCLASS INSTANCE");
      }
    }
  }
