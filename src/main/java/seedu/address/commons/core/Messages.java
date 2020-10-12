package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    /** Invalid commands */
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    // To remove
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    /** Error messages, string refers to item type */
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The %s item index provided is invalid!";
    public static final String MESSAGE_DUPLICATE_ITEM = "This %s item already exists in InternHunter";

    /** Index error */
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /** Delete message: First string refers to item type, second string refers to item deleted. */
    public static final String MESSAGE_DELETED_ITEM = "Deleted %1$s item: %2$s";

    public static final String MESSAGE_INVALID_ITEM_TYPE = "Item type has to be either 'com', 'int', 'app' or 'me'";

    /** Add message: First string refers to item type, second string refers to item added. */
    public static final String MESSAGE_ADD_SUCCESS = "New %1$s item added: %2$s\n";

    /** Edit message: First string refers to item type, second string refers to item. */
    public static final String MESSAGE_EDIT_SUCCESS = "Edited %1$s item: %2$s\n";

    /** View message: First string refers to item type, second string refers to the index. */
    public static final String MESSAGE_VIEW_SUCCESS = "Currently viewing %1$s item %2$s\n";

}
