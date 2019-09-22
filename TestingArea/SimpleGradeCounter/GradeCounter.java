package SimpleGradeCounter;

public class GradeCounter
  {
  //private int[] grades;
  private Grade[] grades;
  
  public GradeCounter( int topMark )
    {
    //grades = new int[topMark + 1];
    grades = new Grade[topMark + 1];
    
    for( int i = 0; i < grades.length; i++)
      grades[i] = new Grade();
    }
  
  public void addGrade( int newGrade )
    {
    //grades[newGrade] = grades[newGrade] + 1;
    //grades[newGrade].grade = grades[newGrade].grade + 1;
    Grade a = grades[newGrade];
    a.grade = a.grade + 1;
    }
  
  public void printHistogram()
    {
    for( int i = 0; i < grades.length; i++)
      {
      System.out.printf("Number of %d's (%s): ", i, grades[i].toString());
      for( int j = 0; j < grades[i].grade; j++ )
        {
        System.out.print("*");
        }
      System.out.println(" ");
      }
    }
  
  }
