package PolymorphismAndInterface.DrawShapesApp;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Rectangle class after inheritance. Comments removed for display in class.
 * Constructors removed because they require a separate discussion.
 *
 * @author Sam Scott
 */
public class Rectangle extends GeometricObject {

private double width = 200, height = 100;

public double getWidth() {
return width;
}

public void setWidth(double width) {
if (width <= 0) {
System.out.println("ERROR! Width must be greater than 0. Not changed.");
} else {
this.width = width;
}
}

public double getHeight() {
return height;
}

public void setHeight(double height) {
if (height <= 0) {
System.out.println("ERROR! Height must be greater than 0. Not changed.");
} else {
this.height = height;
}
}

public double getPerimeter() {
return width * 2 + height * 2;
}

public void draw(GraphicsContext gc) {
gc.setStroke(getStrokeColor());
gc.setFill(getFillColor());
gc.setLineWidth(getLineWidth());
gc.fillRect(getX() - width / 2.0, getY() - height / 2.0, width, height);
gc.strokeRect(getX() - width / 2.0, getY() - height / 2.0, width, height);
}

@Override
public String toString() {
return "Rectangle{" + "width=" + width + ", height=" + height + '}';
}

}

