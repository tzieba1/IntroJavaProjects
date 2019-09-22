package FXGUI.CircleManager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is the same as Circle2, but properly encapsulated.
 *
 * @author Sam Scott
 */
public class Circle4
{
  /**
   * The radius of the circle
   */
  private double radius;

  /**
   * The x-coordinate of the center
   */
  private double x;

  /**
   * The y-coordinate of the center
   */
  private double y;

  /**
   * Constructor.
   *
   * @param radius the initial radius
   * @param x      the initial x value
   * @param y      the initial y value
   */
  public Circle4(double radius, double x, double y)
  {
    this.radius = radius;
    this.x = x;
    this.y = y;
  }

  /**
   * Computes the circle area.
   *
   * @return The area of the circle.
   */
  public double getArea()
  {
    return Math.PI * radius * radius;
  }

  /**
   * Test whether two circles are equal.
   *
   * @param other The other circle.
   * @return True if the location and radius are the same.
   */
  public boolean equals(Circle4 other)
  {
    return (this.x == other.x && this.y == other.y && this.radius == other.radius);
  }

  /**
   * Draws the circle on a GraphicsContext object.
   *
   * @param gc The GraphicsContext to draw on.
   */
  public void draw(GraphicsContext gc)
  {
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(radius / 4);
    gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
  }

  /**
   * @return The circle's radius.
   */
  public double getRadius()
  {
    return radius;
  }

  /**
   * @param radius The new radius for the circle.
   */
  public void setRadius(double radius)
  {
    if (radius <= 0)
    {
      System.out.println("ERROR! Radius must be greater than 0. Not changed.");
    } else
    {
      this.radius = radius;
    }
  }

  /**
   * @param radius The new location for the circle.
   */
  public void setLocation(double x, double y)
  {
    if (x <= 0)
    {
      System.out.println("ERROR! X coordinate must be greater than 0. Not changed.");
    } else
    {
      this.x = x;
    }
    if (y <= 0)
    {
      System.out.println("ERROR! Y coordinate must be greater than 0. Not changed.");
    } else
    {
      this.y = y;
    }
  }

  /**
   * Determines whether or not this circle is inside another one. This circle
   * is considered "inside" the other if its center is inside the other
   * circle.
   *
   * @param other The other Circle
   * @return True if the this circle is "inside" the other one.
   */
  public boolean isInside(Circle4 other)
  {
    if (distance(other) < other.radius)
    {
      return true;
    }
    return false;
  }

  /**
   * Compute the distance from the the center of another circle to the center
   * of the circle.
   *
   * @param Circle5 The other circle.
   * @return Distance between the centers of the two circles.
   */
  private double distance(Circle4 other)
  {
    return Math.sqrt((this.x - other.x) * (this.x - other.x)
            + (this.y - other.y) * (this.y - other.y));
  }

  /**
   * @return A String representation of a Circle4
   */
  @Override
  public String toString()
  {
    return String.format("Circle4: radius %.0f, location (%.0f,%.0f)", radius, x, y);
    //return "Circle4: radius " + radius + ", location (" + x + "," + y + ")";
  }
}
