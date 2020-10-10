package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    /** AB3 messages */
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    // To remove
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    /** Shared messages */
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The %s index provided is invalid!";
    public static final String MESSAGE_WRONG_TAB = "You must be on the %s tab in order to execute this command!";
    public static final String MESSAGE_DUPLICATE_ITEM = "This %s already exists in InternHunter";
    public static final String MESSAGE_DELETED_ITEM = "Deleted %1$s: %2$s";
    public static final String MESSAGE_INVALID_ITEM_TYPE = "Item type has to be either 'com', 'int', 'app' or 'me'";

}
