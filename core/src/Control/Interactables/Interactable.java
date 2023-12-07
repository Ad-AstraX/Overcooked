package Control.Interactables;

import Control.Holdables.Holdable;

public interface Interactable {
    /**
     * A method that is implemented in every interactable realization.
     * This method is called whenever the player wishes to interact with a certain object.
     * The object then fulfills its task upon interaction
     * <p>
     * @param holdable The ingredient or food to be "processed" by the kitchen counter
     * @return Whether or not the Interaction was successful
     */
    public boolean interact(Holdable holdable);
}
