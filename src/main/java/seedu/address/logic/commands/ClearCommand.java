package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.item.ItemList;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "InternHunter has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setApplicationList(new ItemList<>());
        model.setCompanyList(new ItemList<>());
        model.setProfileList(new ItemList<>());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
