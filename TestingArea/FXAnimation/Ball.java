package FXAnimation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * An example of an object that can draw and move itself.
 *
 * @author Sam Scott
 */
public class Ball {

    private double x, y, xSpeed, ySpeed;
    private final int size;
    private final Color c;

    /**
     * Creates a FXAnimation.Ball instance.
     *
     * @param x Initial x position (left)
     * @param y Initial y position (top)
     * @param xSpeed Number of pixels to move horizontally in each step
     * (negative for left, positive for right)
     * @param ySpeed Number of pixels to move vertically in each step (negative
     * for up, positive for down)
     * @param size Diameter of ball
     * @param c Color of ball
     */
    public Ball(double x, double y, double xSpeed, double ySpeed, int size, Color c) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.size = size;
        this.c = c;
    }

    /**
     * Increment x and y using the values of xSpeed and ySpeed
     */
    public void moveOneStep() {
        x += xSpeed;
        y += ySpeed;
    }

    /**
     * Reverses the x direction by multiplying it by -1
     */
    public void bounceX() {
        xSpeed *= -1;
    }

    /**
     * Reverses the y direction by multiplying it by -1
     */
    public void bounceY() {
        ySpeed *= -1;
    }

    /**
     * Draw the ball in its current location on a Graphics object
     *
     * @param g The GraphicsContext object to draw on
     */
    public void draw(GraphicsContext g) {
        g.setFill(c);
        g.fillOval((int) Math.round(x), (int) Math.round(y), size, size);
    }

    /**
     * @return the current x location
     */
    public double getX() {
        return x;
    }

    /**
     * @return the current y location
     */
    public double getY() {
        return y;
    }

    /**
     * @return the size of the ball
     */
    public int getSize() {
        return size;
    }

}
