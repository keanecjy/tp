package seedu.address.logic.commands.view;

import seedu.address.logic.commands.Command;

public abstract class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    //todo Update Message Usage to standardised format
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views a company, internship, application or profile item in InternHunter.\n"
            + "Parameters: ITEM_TYPE DETAILS\n"
            + "Note: Valid ITEM_TYPEs are 'com', 'int', 'app' or 'me'. "
            + "Each ITEM_TYPE requires its own set of details.\n"
            + "Example: " + COMMAND_WORD + " "
            + "int "
            + "DETAILS";

}
