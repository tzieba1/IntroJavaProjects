package PolymorphismAndInterface.DrawShapesApp;

import javafx.scene.canvas.GraphicsContext;

/**
 * Exercise 1c, 2c solution, Week 8. Uncommented.
 *
 * @author Sam Scott
 */
public class Target extends Circle {

@Override
public void draw(GraphicsContext gc) {
super.draw(gc);
gc.strokeOval(getX() - getRadius() / 2, getY() - getRadius() / 2, getRadius(), getRadius());
}

@Override
public String toString() {
return "Target{" + '}'
    + "\n-->" + super.toString(); // Exercise 2a
  
}

}
