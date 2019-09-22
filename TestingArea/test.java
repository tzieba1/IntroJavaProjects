import java.util.Scanner;

public class test
  {
 // public static void foo(int[] x) { x[0] = x[0] + 1; }
 public static int[] foo(int[] x) {
 x[0] = x[0] + 1;
 return x;
 }
  public static void main(String[] args)
    {
//    int[] a = new int[4];
//
//    for (int num : a )
//      num = -1;
//
//    for (int num : a )
//      System.out.println(num);
//
//
//    int[] a2 = new int[10];
//    int[] b = a2;
//    foo(a2);
//    System.out.println(a2[0] +" "+b[0]);
    
//    double a = 5;
//    System.out.println(a);
    
   
    int[] a = new int[10];
    int[] b = foo(a);
    System.out.println(a[0]+" "+b[0]);
    
    //throw new IllegalArgumentException("Nice try!");
   
    
//    Scanner sc = new Scanner(System.in);
//    double c = sc.nextDouble();
//    double d = c==0 ? 1 : c;
//    System.out.println(d);
    
    
    }
  }