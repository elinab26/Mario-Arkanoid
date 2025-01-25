package Geometry;
import Collidables.Collidable;

/**
 * The Geometry.Point class represents a point in a 2D coordinate system.
 */
public class Point {
    private double x, y; // the x-coordinate and y-coordinate of the point
    private final double epsilon = 0.000001d;

    /**
     * Constructs a new Geometry.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point
     * @return the Euclidean distance between this point and the other point
     */
    public double distance(Point other) {
        double distance = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
        return Math.sqrt(distance);
    }

    /**
     * Checks whether this point is equal to another point.
     *
     * @param other the other point to compare with
     * @return true if the points have the same coordinates, false otherwise
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) <= epsilon && Math.abs(this.y - other.y) <= epsilon;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if the point is within the given collidable object.
     *
     * @param collidable the collidable object to check against
     * @return true if the point is within the collidable object, false otherwise
     */
    public boolean checkIfInBlock(Collidable collidable) {
        double x = this.getX();
        double y = this.getY();
        return ((x >= collidable.getCollisionRectangle().getUpperLeft().getX()
                && x <= collidable.getCollisionRectangle().getUpperLeft().getX()
                + collidable.getCollisionRectangle().getWidth())
                && (y >= collidable.getCollisionRectangle().getUpperLeft().getY()
                && y <= collidable.getCollisionRectangle().getUpperLeft().getY()
                + collidable.getCollisionRectangle().getHeight()));
    }

}
