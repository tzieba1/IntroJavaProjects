package SimpleGradeCounter;

public class View
  {
  public static void main(String[] args)
    {
    GradeCounter gc = new GradeCounter( 5 );
    
    gc.addGrade(3);
    gc.addGrade(3);
    gc.addGrade(1);
    gc.addGrade(2);
    gc.addGrade(0);
    gc.addGrade(5);
    
    gc.printHistogram();
    }
  }
