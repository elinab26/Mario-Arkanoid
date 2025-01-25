package Sprites;

import Collidables.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Screens.GameLevel;
import Level.GameEnvironment;
import ListenersAndNotifier.HitNotifier;
import ListenersAndNotifier.HitListener;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The Sprites.Ball class represents a ball with a center point, radius, color, and velocity.
 * It includes methods for drawing the ball, setting its velocity, and moving it within
 * a specified surface or within specific boundaries.
 * The ball's movement is constrained by collision detection with the edges of the surface
 * or defined boundaries, and it can respond to intersections with lines and corners.
 */
public class Ball implements Sprite, HitNotifier {
    private int radius;
    private Point center;
    private Color color;
    private Velocity v;
    private Line verticalLine;
    private Line horizontalLine;
    private GameEnvironment game;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructs a Sprites.Ball with a specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param game   the game environment in which the ball moves
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        // Create a vertical line that intersects the center of the ball
        verticalLine = new Line(center.getX(), center.getY() - radius, center.getX(), center.getY() + radius);
        // Create a horizontal line that intersects the center of the ball
        horizontalLine = new Line(center.getX() - radius, center.getY(), center.getX() + radius, center.getY());
        this.game = game;
    }

    /**
     * Gets the X coordinate of the ball's center.
     *
     * @return the X coordinate of the ball's center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets the Y coordinate of the ball's center.
     *
     * @return the Y coordinate of the ball's center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Sets the X coordinate of the ball's center.
     *
     * @param x the new X coordinate of the ball's center
     */
    public void setX(int x) {
        center = new Point(x, getY());
    }

    /**
     * Sets the Y coordinate of the ball's center.
     *
     * @param y the new Y coordinate of the ball's center
     */
    public void setY(int y) {
        center = new Point(getX(), y);
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
         drawChamp(surface, this.color, this.center);
//        surface.setColor(this.color);
//        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Draws the mushroom on the given DrawSurface.
     *
     * @param d      the DrawSurface on which to draw
     * @param color  the color of the champion
     * @param center the center point of the champion
     */
    public static void drawChamp(DrawSurface d, Color color, Geometry.Point center) {
        Color white = Color.WHITE;
        Color black = Color.BLACK;
        Color beige = new Color(254, 232, 173);
        double x = center.getX();
        double y = center.getY();

        d.setColor(color);
        d.fillOval((int) (x - 10), (int) (y - 10), 20, 16);

        d.setColor(beige);
        d.fillOval((int) (x - 7), (int) y, 15, 11);

        d.setColor(black);
        d.fillOval((int) (x - 4), (int) (y + 1), 3, 5);
        d.fillOval((int) (x + 1), (int) (y + 1), 3, 5);

        d.setColor(white);
        d.fillCircle((int) x, (int) y - 5, 3);
        d.fillOval((int) (x - 11), (int) (y - 5), 3, 5);
        d.fillOval((int) (x + 7), (int) (y - 5), 3, 5);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Gets the current velocity of the ball.
     *
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center the new center point of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Moves the ball one step, bouncing off the edges of the surface if necessary.
     */
    public void moveOneStep() {
        Line trajectory = new Line(getCenter(), new Point(getX()
                + getVelocity().getDx(), getY() + getVelocity().getDy()));
        if (game.getClosestCollision(trajectory) == null
                || game.getClosestCollision(trajectory).collisionPoint() == null) {
            setCenter(trajectory.end());
        } else {
            CollisionInfo collision = game.getClosestCollision(trajectory);
            if (getCenter().checkIfInBlock(collision.collisionObject())) {
                adjustBall(collision);
                return;
            }
            if (getVelocity().getDx() > 0) {
                setX((int) (collision.collisionPoint().getX() - 2));
            } else if (getVelocity().getDx() < 0) {
                setX((int) (collision.collisionPoint().getX() + 2));
            }
            if (getVelocity().getDy() > 0) {
                setY((int) (collision.collisionPoint().getY() - 2));
            } else if (getVelocity().getDy() < 0) {
                setY((int) (collision.collisionPoint().getY() + 2));
            }
            setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), getVelocity()));
        }
    }

    /**
     * Adjusts the position of the ball based on the collision information.
     *
     * @param collidable the collision information
     */
    public void adjustBall(CollisionInfo collidable) {
        setY((int) (collidable.collisionObject().getCollisionRectangle().getUpperLeft().getY() - 2));
        setVelocity(collidable.collisionObject().hit(this, collidable.collisionPoint(), getVelocity()));
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Removes the ball from the game.
     *
     * @param g the game from which to remove the ball
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);

    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the ball.
     *
     * @param hl the hit listener to remove
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies hit listeners about a hit event.
     *
     * @param hitter the block that was hit
     */
    public void notifyHit(Block hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(hitter, this);
        }
    }
}
