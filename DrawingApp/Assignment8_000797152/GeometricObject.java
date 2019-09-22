package Assignment8_000797152;

import javafx.scene.paint.Color;

/**
 * This class represents the superclass for all geometric objects to be drawn. Direct subclasses of this superclass
 * are the classes GeometricShape and Line.
 *
 * @author Tommy Zieba
 */
public abstract class GeometricObject implements Drawable
  {
  /** x-coordinate of the top-lefthand starting point of this GeometricObject. **/
  private double sx;

  /** y-coordinate of the top-left-hand starting point of this GeometricObject. **/
  private double sy;

  /** x-coordinate of the bottom-right-hand ending point of this GeometricObject. **/
  private double ex;

  /** y-coordinate of the bottom-right-hand ending point of this GeometricObject. **/
  private double ey;
  
  /** The (out)line color for this GeometricObject. **/
  private Color line_color;
  
  /** The (out)line width for this GeometricObject. **/
  private int line_width;

  /**
   * Default constructor - not used.
   */
  public GeometricObject() {};

  /**
   * Constructor for a GeometricObject parametrized by the starting/ending coordinates, line width, and line color.
   * @param sx Starting x-coordinate.
   * @param sy Starting y-coordinate.
   * @param ex Ending x-coordinate.
   * @param ey Ending y-coordinate.
   * @param line_width Width of this GeometricObject in pixels.
   * @param line_color Color of this GeometricObject.
   */
  public GeometricObject(double sx, double sy, double ex, double ey, int line_width, Color line_color)
      {
      this.sx = sx; //Initializing the starting x-coordinate.
      this.sy = sy; //Initializing the starting y-coordinate.
      this.ex = ex; //Initializing the ending x-coordinate.
      this.ey = ey; //Initializing the ending y-coordinate.
      this.line_width = line_width; //Initializing the (out)line width.
      this.line_color = line_color; //Initializing the (out)line color.
      }

  /**
   * Method gets the starting x-coordinate for this GeometricObject.
   * @return Returns the starting x-coordinate.
   */
  public double getStartX() { return sx; }

  /**
   * Method sets the starting x-coordinate for this GeometricObject.
   * @param sx The desired starting x-coordinate.
   */
  public void setStartX(double sx) { this.sx = sx; }

  /**
   * Method gets the starting y-coordinate for this GeometricObject.
   * @return Returns the starting y-coordinate
   */
  public double getStartY() { return sy; }

  /**
   * Method sets the starting y-coordinate for this GeometricObject.
   * @param sy The desired starting y-coordinate.
   */
  public void setStartY(double sy) { this.sy = sy; }

  /**
   * Method gets the ending x-coordinate for this GeometricObject.
   * @return Returns the ending x-coordinate.
   */
  public double getEndX() { return ex; }

  /**
   * Method sets the starting y-coordinate for this GeometricObject.
   * @param ex The desired starting y-coordinate.
   */
  public void setEndX(double ex) { this.ex = ex; }

  /**
   * Method gets the ending y-coordinate for this GeometricObject.
   * @return Retruns the ending y-coordinate.
   */
  public double getEndY() { return ey; }

  /**
   * Method sets the ending y-coordinate for this GeometricObject.
   * @param ey The desired ending y-coordinate.
   */
  public void setEndY(double ey) { this.ey = ey; }

  /**
   * Method gets the (out)line color for this GeometricObject.
   * @return Returns the (out)line color.
   */
  public Color getLineColor() { return line_color; }

  /**
   * Method sets the (out)line color for this GeometricObject
   * @param line_color The desired (out)line color.
   */
  public void setLineColor(Color line_color) { this.line_color = line_color; }

  /**
   * Method gets the (out)line with for this GeometricObject.
   * @return Returns the width.
   */
  public int getLineWidth() { return line_width; }

  /**
   * Method sets the (out)line width for this GeometricObject.
   * @param lineWidth The desired (out)line width.
   */
  public void setLineWidth(int lineWidth) { this.line_width = lineWidth; }

  /**
   * Method checks if an (x,y) coordinate lies on the rectangle containing this GeometricObject
   * in the event it is selected.
   * @param x The x-coordinate to be checked.
   * @param y The y-coordinate to be checked.
   * @return  Returns true if the (x,y) coordinate lies on the rectangle containing the object and false otherwise.
   */
  public boolean selectedCheck(double x, double y)
    {
    //*** Checking if the inputted x and y values lie within the bounds of the rectangle
    //    formed by starting and ending points.
    return this.getStartX() < x && x < this.getEndX() && this.getStartY() < y && y < this.getEndY();
    }

  /**
   * The overridden toString() method which returns a String representation of this GeometricObject.
   * @return  Returns the String representation of this GeometricObject.
   */
  @Override
  public String toString()
    {
    return "GeometricObject[ x=" + sx + ", y=" + sy + ", Outline Color=" + line_color +
        ", Outline Width=" + line_width + " ]";
    }
  }
