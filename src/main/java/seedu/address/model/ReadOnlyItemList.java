package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a list of items.
 */
public interface ReadOnlyItemList {

    /**
     * Returns an unmodifiable view of the Item list.
     * This list will not contain any duplicate Items.
     */
    ObservableList<Item> getItemList();

}