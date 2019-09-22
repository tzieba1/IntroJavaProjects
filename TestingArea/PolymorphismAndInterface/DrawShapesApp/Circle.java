package PolymorphismAndInterface.DrawShapesApp;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Circle class after inheritance. Comments removed for
 * display in class. Constructors removed because they require a separate
 * discussion.
 *
 * @author Sam Scott
 */
public class Circle extends GeometricObject {

    private double radius = 50;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            System.out.println("ERROR! Radius must be greater than 0. Not changed.");
        } else {
            this.radius = radius;
        }
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getStrokeColor());
        gc.setFill(getFillColor());
        gc.setLineWidth(getLineWidth());
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
        gc.strokeOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + '}';
    }

}
