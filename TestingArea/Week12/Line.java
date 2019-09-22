package Week12;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line
  {
  private double sx, sy;
  private double ex, ey;
  private Color lineColor;
  
  public Line() {}
  
  public Line(double sx, double sy, double ex, double ey, Color lineColor)
    {
    this.ex = ex;
    this.ey = ey;
    this.sx = sx;
    this.sy = sy;
    this.lineColor = lineColor;
    }
  
  public void setLineColor(Color nc) { this.lineColor = nc;}
  
  public void draw(GraphicsContext gc)
    {
    gc.setStroke(lineColor);
    gc.setLineWidth(5.0);
    gc.strokeLine(sx,sy,ex,ey);
    }
  }
