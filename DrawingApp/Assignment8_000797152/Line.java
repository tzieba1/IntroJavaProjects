package Assignment8_000797152;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a Line that implements a Drawable interface containing abstract draw() method for drawing
 * different lines that are a subclass of the GeometricObject superclass.
 * @author Tommy Zieba
 */
public class Line extends GeometricObject
  {
  /**
   * Default constructor - not used.
   */
  public Line() {}
  /**
   * Constructor for a Line parametrized by the starting and ending coordinates, line width, and line color.
   * @param sx Starting x-coordinate.
   * @param sy Starting y-coordinate.
   * @param ex Ending x-coordinate.
   * @param ey Ending y-coordinate.
   * @param line_width Width of this line in pixels.
   * @param line_color Color of this line.
   */
 
  public Line(double sx, double sy, double ex, double ey, int line_width, Color line_color)
    {
    super(sx, sy, ex, ey, line_width, line_color);  //Using the GeometricObject constructor to create a Line.
    }

  /**
   * Overridden interface method used to draw this line.
   * @param gc  The GraphicsContext class being used to draw on the canvas.
   */
  @Override
  public void draw(GraphicsContext gc)
    {
    gc.setStroke( this.getLineColor() );  //Setting the stroke color to line_color passed into constructor.
    gc.setLineWidth( this.getLineWidth() ); //Setting the line width passed into constructor.
    gc.strokeLine( this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY() );  //Drawing a line.
    }
  
  /**
   * The overridden toString() method which returns a String representation of this Line.
   * @return  Returns the String representation of this Line.
   */
  @Override
  public String toString()
    {
    //*** Returning the String representing this Line.
    return super.toString() + " --> " + "Line"; //Calling the superclass' toString method to describe this line.
    }
  }
