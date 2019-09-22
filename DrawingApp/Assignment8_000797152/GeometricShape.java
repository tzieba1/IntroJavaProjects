package Assignment8_000797152;

import javafx.scene.paint.Color;

/**
 * Class extends GeometricObject including a fill color, width, and height used to draw GeometricShape subclass objects.
 * This class is an abstract superclass with direct subclasses Rectangle and Ellipse.
 * @author Tommy Zieba
 */
public abstract class GeometricShape extends GeometricObject
  {
  /** The color used to fill in this shape. **/
  private Color fill_color;
  
  /** The width for this shape. **/
  private double width;
  
  /** The height for this shape. **/
  private double height;

  /**
   * Default constructor - not used.
   */
  public GeometricShape() {}

  /**
   * Constructor used to create a GeometricShape parametrized by the starting/ending points, outline width, outline
   * color, and fill color.
   * @param sx Starting x-coordinate.
   * @param sy Starting y-coordinate.
   * @param ex Ending x-coordinate.
   * @param ey Ending y-coordinate.
   * @param line_width Width of the outline for this geometric shape in pixels.
   * @param line_color Color of the outline for this geometric shape.
   * @param fill_color Color filling interior of this geometric shape.
   */
  public GeometricShape(double sx, double sy, double ex, double ey, int line_width, Color line_color, Color fill_color)
    {
    super(sx, sy, ex, ey, line_width, line_color);  //Superconstructor inherited from the GeometricObject class.
    this.fill_color = fill_color; //Setting additional fill color.
    this.width = ex - sx; //Setting additional width.
    this.height = ey - sy; //Setting additional height.
    }

  /**
   * Method gets the fill color for this GeometricShape.
   * @return Returns the fill color.
   */
  public Color getFillColor() { return fill_color; }

  /**
   * Method sets the fill color.
   * @param fillColor The fill color to be set as the new fill color for this shape.
   */
  public void setFillColor(Color fillColor)
    {
    this.fill_color = fillColor;  //Setting the fill color to the color passed into this method.
    }

  /**
   * Method gets this shapes width.
   * @return  Returns the width.
   */
  public double getWidth() { return this.width; }

  /**
   * Method sets the width for this GeometricShape.
   * @param width The width to be set as the new width for this shape.
   */
  public void setWidth(double width)
    {
    this.width = width; //Setting the width to the number passed into this method.
    }

  /**
   * Method gets the height for this GeometricSape.
   * @return Returns the height.
   */
  public double getHeight() { return this.height; }

  /**
   * Method sets the height.
   * @param height  The height to be set as the new height for this GeometricShape.
   */
  public void setHeight(double height)
    {
    this.height = height; //Setting the height to the number passed into this method.
    }

  /**
   * The overridden toString() method which returns a String representation of this GeometricShape.
   * @return  Returns the String representation of this GeometricShape.
   */
  @Override
  public String toString()
    {
    return super.toString() + " --> " + "GeometricShape[ Fill Color = " + fill_color + " ]";
    }
  }
