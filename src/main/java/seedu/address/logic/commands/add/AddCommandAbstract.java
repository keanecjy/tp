package seedu.address.logic.commands.add;

import seedu.address.logic.commands.Command;

/**
 * Represents an Add Command for Items.
 */
public abstract class AddCommandAbstract extends Command {

    public static final String COMMAND_WORD = "add";
    //todo Update Message Usage to standardised format
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a company, internship, application or profile item to InternHunter.\n"
            + "Parameters: ITEM_TYPE DETAILS\n"
            + "Note: Valid ITEM_TYPEs are 'com', 'int', 'app' or 'me'. "
            + "Each ITEM_TYPE requires its own set of details.\n"
            + "Example: " + COMMAND_WORD + " "
            + "int "
            + "DETAILS";

    /**
     * Obtains the name of item associated with Add Command.
     *
     * @return Item name.
     */
    public abstract String getItemType();
}
