package ListenersAndNotifier;

/**
 * The ListenersAndNotifier.HitNotifier interface should be implemented by any class that wants to manage
 * a list of ListenersAndNotifier.HitListener objects. This interface provides methods to add and remove
 * ListenersAndNotifier.HitListener objects from the list.
 */
public interface HitNotifier {
    /**
     * Adds a ListenersAndNotifier.HitListener to the list of listeners to hit events.
     *
     * @param hl the ListenersAndNotifier.HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a ListenersAndNotifier.HitListener from the list of listeners to hit events.
     *
     * @param hl the ListenersAndNotifier.HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
