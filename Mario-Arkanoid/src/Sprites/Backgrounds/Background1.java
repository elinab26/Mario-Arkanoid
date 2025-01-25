package Sprites.Backgrounds;

import Screens.GameLevel;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Background1 represents the first level's background in the game.
 * It contains various elements such as hills, bushes, clouds, and a Piranha plant.
 * This class extends the Background class and implements the drawing and animation logic
 * specific to this background.
 */
public class Background1 extends Background {
    // Piranha plant's vertical position
    private int piranhaY = PIPE_Y;
    private boolean movingUp = true; // Flag to control the direction of the piranha's movement

    private static final int WIDTH = 800; // Width of the background
    private static final int HEIGHT = 600; // Height of the background
    private static final int PIPE_X = 300; // X position for the pipe
    private static final int PIPE_Y = HEIGHT - 170; // Y position for the pipe
    private static final int PIRANHA_HEIGHT = 60; // Height of the Piranha plant

    /**
     * Draws the background on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the background
     */
    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
        drawHills(d);
        drawBushes(d);
        drawGround(d);
        drawPipes(d);
        drawPiranha(d, PIPE_X, piranhaY);
        drawClouds(d);
    }

    /**
     * Updates the Piranha plant's position based on its movement logic.
     */
    @Override
    public void timePassed() {
        if (movingUp) {
            piranhaY -= 2;
            if (piranhaY <= PIPE_Y - PIRANHA_HEIGHT) {
                movingUp = false; // Switch direction to down
            }
        } else {
            piranhaY += 2;
            if (piranhaY >= PIPE_Y) {
                movingUp = true; // Switch direction to up
            }
        }
    }

    /**
     * Adds this background to the specified GameLevel.
     *
     * @param g the GameLevel to which this background will be added
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    // The following methods handle the drawing of various elements in the background...

    /**
     * Draws the background color of the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawBackground(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
    }

    /**
     * Draws the ground of the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawGround(DrawSurface d) {
        d.setColor(new Color(124, 252, 0)); // Green color for ground
        d.fillRectangle(0, HEIGHT - 100, WIDTH, 100);
    }

    /**
     * Draws the pipes in the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawPipes(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(600, HEIGHT - 150, 50, 50); // Pipe base
        d.fillRectangle(590, HEIGHT - 170, 70, 20); // Pipe top
        d.fillRectangle(PIPE_X, HEIGHT - 150, 50, 50); // Additional pipe base
        d.fillRectangle(PIPE_X - 10, HEIGHT - 170, 70, 20); // Additional pipe top
    }

    /**
     * Draws the Piranha plant in the game.
     *
     * @param d the DrawSurface on which to draw
     * @param x the horizontal position of the Piranha
     * @param y the vertical position of the Piranha
     */
    public static void drawPiranha(DrawSurface d, int x, int y) {
        // Stem of the Piranha
        d.setColor(Color.GREEN);
        d.fillRectangle(x + 20, y, 10, PIPE_Y - y); // Stem

        // Head of the Piranha
        d.setColor(Color.RED);
        d.fillOval(x, y - 50, 50, 50); // Head
        d.setColor(Color.WHITE);
        d.fillOval(x + 10, y - 40, 30, 30); // Mouth

        // Lips of the Piranha
        d.setColor(Color.RED);
        d.drawOval(x + 10, y - 40, 30, 30); // Mouth outline
    }

    /**
     * Draws the clouds in the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawClouds(DrawSurface d) {
        d.setColor(Color.WHITE);
        drawCloud(d, 100, 100);
        drawCloud(d, 400, 150);
        drawCloud(d, 700, 100);
    }

    /**
     * Draws a single cloud in the game.
     *
     * @param d the DrawSurface on which to draw
     * @param x the horizontal position of the cloud
     * @param y the vertical position of the cloud
     */
    public static void drawCloud(DrawSurface d, int x, int y) {
        d.fillCircle(x, y, 20);
        d.fillCircle(x + 20, y, 30);
        d.fillCircle(x + 40, y, 20);
        d.fillCircle(x + 10, y - 10, 20);
        d.fillCircle(x + 30, y - 10, 20);
    }

    /**
     * Draws the hills in the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawHills(DrawSurface d) {
        d.setColor(new Color(34, 139, 34)); // Green color for hills
        drawHill(d, 50, HEIGHT - 150, 200, 100);
        drawHill(d, 500, HEIGHT - 200, 300, 150);
    }

    /**
     * Draws a single hill in the game.
     *
     * @param d      the DrawSurface on which to draw
     * @param x      the horizontal position of the hill
     * @param y      the vertical position of the hill
     * @param width  the width of the hill
     * @param height the height of the hill
     */
    public static void drawHill(DrawSurface d, int x, int y, int width, int height) {
        d.fillOval(x, y, width, height);
    }

    /**
     * Draws the bushes in the game.
     *
     * @param d the DrawSurface on which to draw
     */
    public static void drawBushes(DrawSurface d) {
        d.setColor(new Color(0, 128, 0)); // Dark green for bushes
        drawBush(d, 150, HEIGHT - 130, 100, 50);
        drawBush(d, 400, HEIGHT - 130, 150, 50);
        drawBush(d, 650, HEIGHT - 130, 100, 50);
    }

    /**
     * Draws a single bush in the game.
     *
     * @param d      the DrawSurface on which to draw
     * @param x      the horizontal position of the bush
     * @param y      the vertical position of the bush
     * @param width  the width of the bush
     * @param height the height of the bush
     */
    public static void drawBush(DrawSurface d, int x, int y, int width, int height) {
        d.fillOval(x, y, width, height);
    }
}
