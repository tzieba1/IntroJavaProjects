package Assignment8_000797152;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface used to draw different GeometricObjects from the GraphicsContext class. Contains a single .draw() method.
 */
public interface Drawable
  {
  abstract void draw(GraphicsContext gc); //Method implemented in each subclass object to draw itself.
  }
