package seedu.address.logic.commands.find;

import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import seedu.address.logic.commands.Command;

/**
 * todo javadocs
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds if an item is contained in "
        + COMPANY_NAME + " or "
        + APPLICATION_NAME + " or "
        + PROFILE_NAME + ".\n"
        + "Parameters: find ITEM_TYPE description\n" // NOTE THAT THIS NAMING IS JUST TEMPORARY WILL CHANGE IF CAN
        + "Example: "
        + COMMAND_WORD + " " + COMPANY_ALIAS + " " + "Google" + "\n";
}
