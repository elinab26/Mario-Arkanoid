package Level;
/**
 * The Level.Counter class is used for counting things. It includes methods to increase,
 * decrease, and get the current count.
 */
public class Counter {
    private int count;

    /**
     * Constructs a Level.Counter with the specified initial count.
     *
     * @param count the initial count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Adds a number to the current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Subtracts a number from the current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}

