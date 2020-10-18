package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationNameContainsKeyWordsPredicate;

/**
 * todo javadocs
 */
public class FindApplicationCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + " " + APPLICATION_ALIAS + ": Finds if an application exist in the list of application in InternHunter\n"
        + "Parameters: ITEM_TYPE DESCRIPTION\n" // THE WORD DESCRIPTION CAN BE CHANGE IN THE FUTURE.
        + "Example: " + COMMAND_WORD + " " + APPLICATION_ALIAS + " " + "software engineering" + "\n";

    private final ApplicationNameContainsKeyWordsPredicate predicate;

    public FindApplicationCommand(ApplicationNameContainsKeyWordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getApplicationList().updateFilteredItemList(predicate);
        model.setApplicationViewIndex(Index.fromOneBased(1));
        return new CommandResult(
            String.format(Messages.MESSAGE_FIND_APPLICATION_SUCCESS,
                model.getApplicationList().getFilteredItemList().size()));
    }
}
