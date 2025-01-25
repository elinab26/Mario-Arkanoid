package Sprites;

import Geometry.Point;

/**
 * The Sprites.Velocity class represents the velocity of an object with
 * horizontal and vertical components (dx, dy).
 */
public class Velocity {
    private double dx, dy;

    /**
     * Constructs a Sprites.Velocity with specified horizontal and vertical components.
     *
     * @param dx the horizontal component of the velocity
     * @param dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Sets the velocity of the object.
     *
     * @param v the new velocity to set
     */
    public void setVelocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * Creates a Sprites.Velocity instance from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a new Sprites.Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        Velocity v = new Velocity(dx, -dy);
        return v;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the original point
     * @return a new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets the horizontal component of the velocity.
     *
     * @return the horizontal component (dx)
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the vertical component of the velocity.
     *
     * @return the vertical component (dy)
     */
    public double getDy() {
        return dy;
    }
}
