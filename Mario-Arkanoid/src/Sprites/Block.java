package Sprites;

import Collidables.Collidable;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Screens.GameLevel;
import ListenersAndNotifier.HitListener;
import ListenersAndNotifier.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.Block class represents a rectangular block that can be collided with and drawn on a surface.
 * It includes methods for handling collisions, drawing the block on a given DrawSurface, and managing hit listeners.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();
    private boolean specialBlock1;
    private boolean specialBlock2;

    /**
     * Constructs a Sprites.Block with the specified upper-left corner, width, height, and color.
     *
     * @param upperLeft the upper-left corner of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        specialBlock1 = false;
        specialBlock2 = false;
    }

    /**
     * Activates the special feature for Block 1.
     */
    public void doSpecialBlock1() {
        specialBlock1 = true;
    }

    /**
     * Checks if special feature 1 is activated.
     *
     * @return true if special feature 1 is activated, false otherwise
     */
    public boolean getSpecialBlock1() {
        return specialBlock1;
    }

    /**
     * Activates the special feature for Block 2.
     */
    public void doSpecialBlock2() {
        specialBlock2 = true;
    }

    /**
     * Checks if special feature 2 is activated.
     *
     * @return true if special feature 2 is activated, false otherwise
     */
    public boolean getSpecialBlock2() {
        return specialBlock2;
    }

    /**
     * Gets the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the rectangle representing the block's collision shape.
     *
     * @return the rectangle representing the block's collision shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Handles the hit event when the ball collides with the block.
     * Changes the velocity of the ball based on the collision point.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the block
     * @return the new velocity of the ball after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] edges = rectangle.getEdges();
        if (this.color != Color.darkGray) {
            this.notifyHit(hitter);
        }
        if (hitter.getCenter().getY() == 598) {
            hitter.notifyHit(this);
        }
        if (edges[0].onLine(collisionPoint) || edges[1].onLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (edges[2].onLine(collisionPoint) || edges[3].onLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        return currentVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the block
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.getCollisionRectangle().getUpperLeft().getX() == 0
                || this.getCollisionRectangle().getUpperLeft().getX() == 770) {
            d.setColor(color);
            d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
            d.setColor(Color.black);
            d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        } else {
            drawBlock(d, this.color, this.getCollisionRectangle().getUpperLeft());
        }
    }

    /**
     * Draws a block with a specific design on the given DrawSurface.
     *
     * @param d         the DrawSurface on which to draw
     * @param color     the color of the block
     * @param upperLeft the upper-left corner of the block
     */
    public static void drawBlock(DrawSurface d, Color color, Point upperLeft) {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        d.setColor(color);
        d.fillRectangle((int) x, (int) y, 40, 40);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) x, (int) y, 40, 40);

        d.setColor(new Color(153, 124, 22));
        d.fillCircle((int) (x + 4), (int) (y + 4), 2);
        d.fillCircle((int) (x + 4), (int) (y + 36), 2);
        d.fillCircle((int) (x + 36), (int) (y + 4), 2);
        d.fillCircle((int) (x + 36), (int) (y + 36), 2);

        d.setColor(Color.white);
        d.fillRectangle((int) (x + 17), (int) (y + 30), 6, 6);
        d.fillRectangle((int) (x + 17), (int) (y + 20), 6, 6);

        d.fillRectangle((int) (x + 17), (int) (y + 20), 18, 2);
        d.fillRectangle((int) (x + 17), (int) (y + 18), 18, 2);

        d.fillRectangle((int) (x + 29), (int) (y + 12), 6, 6);

        d.fillRectangle((int) (x + 10), (int) (y + 12), 5, 4);

        d.fillRectangle((int) (x + 10), (int) (y + 8), 25, 4);
    }

    /**
     * Notifies the block that time has passed. This method is currently empty.
     */
    @Override
    public void timePassed() {
        // No behavior defined for this method in the current implementation
    }

    /**
     * Adds the block to the game by adding it to both the collidable and sprite collections.
     *
     * @param g the game to which the block will be added
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Checks if the color of the ball matches the color of the block.
     *
     * @param ball the ball to check
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes the block from the game.
     *
     * @param game the game from which to remove the block
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
