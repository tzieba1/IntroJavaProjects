package Assignment8_000797152;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a Rectangle that implements a Drawable interface containing abstract draw() method for drawing
 * different lines that are a subclass of the GeometricObject superclass.
 * @author Tommy Zieba
 */
public class Rectangle extends GeometricShape
  {
  public Rectangle() {}

  /**
   * Constructor for an Rectangle parametrized by its starting/ending points, line width, line color, and fill color.
   * @param sx Starting x-coordinate.
   * @param sy Starting y-coordinate.
   * @param ex Ending x-coordinate.
   * @param ey Ending y-coordinate.
   * @param line_width Width of the outline for this rectangle in pixels.
   * @param line_color Color of the outline for this rectangle.
   * @param fill_color Color filling interior of this rectangle.
   */
  public Rectangle(double sx, double sy, double ex, double ey, int line_width, Color line_color, Color fill_color)
    {
    super(sx, sy, ex, ey, line_width, line_color, fill_color); //Using GeometricObject constructor to create Rectangle.
    }

  /**
   * Overridden interface method used to draw this rectangle.
   * @param gc  The GraphicsContext class being used to draw on the canvas.
   */
  @Override
  public void draw(GraphicsContext gc)
    {
    gc.setStroke(getLineColor()); //Setting the stroke color to line_color passed into constructor.
    gc.setFill(getFillColor()); //Setting the fill color using color passed into constructor.
    gc.setLineWidth(getLineWidth()); //Setting the line width using width passed into constructor.
    gc.fillRect(getStartX(), getStartY(), this.getWidth(), this.getHeight()); //Draw this filled rectangle.
    gc.strokeRect(getStartX(), getStartY(), this.getWidth(), this.getHeight()); //Draw this rectangle outline.
    }

  /**
   * The overridden toString() method which returns a String representation of this Rectangle.
   * @return  Returns the String representation of this Rectangle.
   */
  @Override
  public String toString()
    {
    return super.toString() + " --> " + "Rectangle"; //Calling the superclass' .toString() to describe this rectangle.
    }
  }

