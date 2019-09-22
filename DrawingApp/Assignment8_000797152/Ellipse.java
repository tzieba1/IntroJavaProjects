package Assignment8_000797152;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents an Ellipse that implements a Drawable interface containing abstract draw() method for drawing
 * different lines that are a subclass of the GeometricObject superclass.
 * @author Tommy Zieba
 */
public class Ellipse extends GeometricShape
  {
  /**
   * Default constructor - not used.
   */
  public Ellipse() {}

  /**
   * Constructor for an Ellipse parametrized by its starting and ending points, line width, line color, and fill color.
   * @param sx Starting x-coordinate.
   * @param sy Starting y-coordinate.
   * @param ex Ending x-coordinate.
   * @param ey Ending y-coordinate.
   * @param line_width Width of the outline for this ellipse in pixels.
   * @param line_color Color of the outline for this ellipse.
   * @param fill_color Color filling interior of this ellipse.
   */
  public Ellipse(double sx, double sy, double ex, double ey, int line_width, Color line_color, Color fill_color)
      {
      super(sx, sy, ex, ey, line_width, line_color, fill_color); //Using GeometricObject constructor to create Ellipse.
      }

  /**
   * Overridden interface method used to draw this ellipse.
   * @param gc  The GraphicsContext class being used to draw on the canvas.
   */
  @Override
  public void draw(GraphicsContext gc)
    {
    gc.setStroke(getLineColor()); //Setting the stroke color to line_color passed into constructor.
    gc.setFill(getFillColor()); //Setting the fill color using color passed into constructor.
    gc.setLineWidth(getLineWidth()); //Setting the line width using width passed into constructor.
    gc.fillOval(getStartX(), getStartY(), this.getWidth(), this.getHeight()); //Draw the filled ellipse.
    gc.strokeOval(getStartX(), getStartY(), this.getWidth(), this.getHeight()); //Draw the ellipse outline.
    }

  /**
   * The overridden toString() method which returns a String representation of this Ellipse.
   * @return  Returns the String representation of this Ellipse.
   */
  @Override
  public String toString()
    {
    return super.toString() + " --> " + "Ellipse";  //Calling the superclass' toString method to describe this ellipse.
    }
  }
