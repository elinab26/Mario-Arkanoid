package Screens;

import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Collidables.Collidable;
import Geometry.Point;
import Level.*;
import ListenersAndNotifier.BallRemover;
import ListenersAndNotifier.BlockRemover;
import Sprites.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Animations.Animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The GameLevel class represents a game of Arkanoid, managing the game loop,
 * creating and adding game elements such as balls, blocks, and paddle,
 * and handling the drawing and updating of these elements.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private Counter removedBlocks;
    private Counter remainingBalls;
    private BallRemover ballRemover;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;
    private ScoreTrackingListener scoreTrackingListener;
    private static int counterLevels;

    /**
     * Constructs a new GameLevel object, initializing the game environment and sprite collection.
     *
     * @param levelInformation      the information about the current level
     * @param scoreTrackingListener the listener for tracking the game score
     */
    public GameLevel(LevelInformation levelInformation, ScoreTrackingListener scoreTrackingListener) {
        environment = new GameEnvironment(new ArrayList<>());
        sprites = new SpriteCollection(new ArrayList<>());
        removedBlocks = new Counter(0);
        remainingBalls = new Counter(0);
        this.scoreTrackingListener = scoreTrackingListener;
        ballRemover = new BallRemover(this, this.remainingBalls);
        this.gui = new GUI("Arkanoid", 800, 600);
        this.runner = new AnimationRunner(gui);
        this.running = true;
        keyboardSensor = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
    }

    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    public BallRemover getBallRemover() {
        return ballRemover;
    }

    public void setRemainingBalls(int number) {
        remainingBalls.increase(number);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating the GUI, balls, paddle, blocks, and bounds,
     * and adding them to the game.
     */
    public void initialize() {
        Sprite background = levelInformation.getBackground();
        background.addToGame(this);
        Random random = new Random();
        this.paddle = new Paddle(keyboardSensor, levelInformation);
        this.paddle.addToGame(this);

        BlockRemover remover = new BlockRemover(this, this.removedBlocks);
        ScoreIndicator score = new ScoreIndicator(scoreTrackingListener.getCurrentScore(), this.levelInformation);
        score.addToGame(this);

        Color[] colors = new Color[]{Color.red, Color.green, Color.blue, Color.orange, Color.gray, Color.pink};
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball1 = new Ball(new Point(random.nextInt(740) + 30, 500), 4, colors[i], environment);
            ball1.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball1.addToGame(this);
            ball1.addHitListener(ballRemover);
            remainingBalls.increase(1);
        }
        createBounds(ballRemover);
        createBlocks(remover, scoreTrackingListener);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboardSensor));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.environment.getBlocks().size() == 5) {
            counterLevels++;
            scoreTrackingListener.setCurrentScore(100);
            this.running = false;
            System.out.println(counterLevels);
            if (counterLevels == GameFlow.getCounter()) {
                this.runner.run(new Win(this.keyboardSensor, scoreTrackingListener));
                gui.close();
            }
            this.sprites.drawAllOn(d);
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.runner.run(new GameOver(this.keyboardSensor, this.scoreTrackingListener));
            gui.close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(1, 3, sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Creates the bounds of the game area using blocks and adds them to the game.
     *
     * @param ballRemover the BallRemover that handles ball removal
     */
    public void createBounds(BallRemover ballRemover) {
        Color color = Color.DARK_GRAY;
        Block blockUp = new Block(new Point(0, 20), 800, 30, color);
        blockUp.addToGame(this);
        Block blockLeft = new Block(new Point(0, 50), 30, 800, color);
        blockLeft.addToGame(this);
        Block blockDown = new Block(new Point(30, 600), 740, 30, color);
        blockDown.addToGame(this);
        blockDown.addHitListener(ballRemover);
        Block blockRight = new Block(new Point(770, 50), 30, 800, color);
        blockRight.addToGame(this);
    }

    /**
     * Creates the blocks in the game and adds them to the game.
     *
     * @param remover       the BlockRemover that handles block removal
     * @param scoreListener the ScoreTrackingListener that handles score updates
     */
    public void createBlocks(BlockRemover remover, ScoreTrackingListener scoreListener) {
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(scoreListener);
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidableFromCollection(c);
    }

    /**
     * Removes a sprite object from the sprite collection.
     *
     * @param s the sprite object to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSpriteFromCollection(s);
    }
}
