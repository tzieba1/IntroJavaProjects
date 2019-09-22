package week11;

import javafx.scene.paint.Color;

/**
 * The parent class for Circle and Rectangle. Comments removed for display in
 * class. Constructors removed because they require a separate discussion.
 * 
 * @author Sam Scott
 */
public class GeometricObject {

    private double x = 60, y = 100;
    private Color strokeColor = Color.DARKBLUE, fillColor = Color.CADETBLUE;
    private int lineWidth = 5;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }
   
    @Override
    public String toString() {
        return "GeometricObject{" + "x=" + x + ", y=" + y + ", strokeColor=" + strokeColor + ", fillColor=" + fillColor + ", lineWidth=" + lineWidth + '}';
    }

}
