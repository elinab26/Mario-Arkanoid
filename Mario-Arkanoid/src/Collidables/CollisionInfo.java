package Collidables;
import Geometry.Point;

/**
 * The Collidables.CollisionInfo class stores information about a collision event,
 * including the point of collision and the collidable object involved.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable c;

    /**
     * Constructs a Collidables.CollisionInfo object with the specified collidable object and collision point.
     *
     * @param c         the collidable object involved in the collision
     * @param collision the point at which the collision occurs
     */
    public CollisionInfo(Collidable c, Point collision) {
        this.c = c;
        this.collision = collision;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point of collision
     */
    public Point collisionPoint() {
        return collision;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return c;
    }
}
