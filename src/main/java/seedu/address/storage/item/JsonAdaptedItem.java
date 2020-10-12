package seedu.address.storage.item;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;

/*
    Represents a Jackson-friendly version  of an item.
 */
public abstract class JsonAdaptedItem {

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public abstract Item toModelType() throws IllegalValueException;
}
