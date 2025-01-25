package Collidables;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;

/**
 * The Collidables.Collidable interface represents an object that can be collided with.
 * It includes methods to get the collision shape and handle the collision event.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified point
     * with the given velocity. The method calculates and returns the new velocity
     * expected after the hit, based on the force the object inflicted.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object that hit
     * @param hitter          the ball that hit the block
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
