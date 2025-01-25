package Sprites;

import Collidables.Collidable;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Level.LevelInformation;
import Screens.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle class represents the paddle in the game.
 * It implements the Sprite and Collidable interfaces, allowing it to be drawn and to respond to collisions.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private LevelInformation levelInformation;

    /**
     * Constructs a Paddle with the given keyboard sensor and level information.
     *
     * @param keyboard         the keyboard sensor to control the paddle
     * @param levelInformation the information about the current level
     */
    public Paddle(KeyboardSensor keyboard, LevelInformation levelInformation) {
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        rectangle = new Rectangle(new Point(290, 557), levelInformation.paddleWidth(), 13);
    }

    /**
     * Moves the paddle to the left, ensuring it stays within game boundaries.
     */
    public void moveLeft() {
        this.rectangle.setUpperLeft(new Point(this.rectangle.getUpperLeft().getX() - 15,
                this.rectangle.getUpperLeft().getY()));
        // Prevent the paddle from moving off the left side
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() <= 30) {
            this.rectangle.setUpperLeft(new Point(770 - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY()));
        }
        this.createEdges();
    }

    /**
     * Moves the paddle to the right, ensuring it stays within game boundaries.
     */
    public void moveRight() {
        this.rectangle.setUpperLeft(new Point(this.rectangle.getUpperLeft().getX() + 15,
                this.rectangle.getUpperLeft().getY()));
        // Prevent the paddle from moving off the right side
        if (this.rectangle.getUpperLeft().getX() >= 770) {
            this.rectangle.setUpperLeft(new Point(30, this.rectangle.getUpperLeft().getY()));
        }
        this.createEdges();
    }

    /**
     * Creates the edges of the paddle's rectangle for collision detection.
     */
    public void createEdges() {
        Line[] edges = new Line[4];
        // Create the four edges of the paddle's rectangle
        edges[0] = new Line(this.rectangle.getUpperLeft(), new Point(this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY())); // Upper edge
        edges[1] = new Line(new Point(this.rectangle.getUpperLeft().getX(), this.rectangle.getUpperLeft().getY()
                + this.rectangle.getHeight()),
                new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                        this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())); // Lower edge
        edges[2] = new Line(new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY()),
                new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                        this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())); // Right edge
        edges[3] = new Line(this.rectangle.getUpperLeft(), new Point(this.rectangle.getUpperLeft().getX(),
                this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())); // Left edge
        this.rectangle.setEdges(edges);
    }

    /**
     * Updates the paddle's position based on the pressed keys.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the rectangle representing the paddle's collision area
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the hit on the paddle and changes the ball's velocity accordingly.
     *
     * @param hitter          the ball that hit the paddle
     * @param collisionPoint  the point where the collision occurs
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double angle = Math.toDegrees(Math.atan2(dy, dx));
        double region = getCollisionRectangle().getWidth() / 5; // Divide the paddle into 5 regions
        double speed = dx / Math.cos(Math.toRadians(angle));
        int x = (int) collisionPoint.getX();

        // Determine which region the collision occurred in and change velocity accordingly
        if (x >= 2 * region + getCollisionRectangle().getUpperLeft().getX() && x < 3 * region
                + getCollisionRectangle().getUpperLeft().getX()) {
            return new Velocity(dx, -dy); // Straight up for the middle region
        }
        // Region 1
        if (x >= getCollisionRectangle().getUpperLeft().getX() && x < region
                + getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setVelocity(Velocity.fromAngleAndSpeed(300, speed));
        }
        // Region 2
        else if (x >= region + getCollisionRectangle().getUpperLeft().getX() && x < 2 * region
                + getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setVelocity(Velocity.fromAngleAndSpeed(330, speed));
        }
        // Region 4
        else if (x >= 3 * region + getCollisionRectangle().getUpperLeft().getX() && x < 4 * region
                + getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setVelocity(Velocity.fromAngleAndSpeed(30, speed));
        }
        // Region 5
        else if (x >= 4 * region + getCollisionRectangle().getUpperLeft().getX() && x < 5 * region
                + getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setVelocity(Velocity.fromAngleAndSpeed(60, speed));
        }
        return currentVelocity;
    }

    /**
     * Adds this paddle to the game, registering it as both a sprite and a collidable.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
