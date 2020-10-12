package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.address.logic.commands.util.CommandUtil.getApplication;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.ui.tabs.TabName;

/**
 * Deletes the application from the Application list.
 */
public class DeleteApplicationCommand extends DeleteCommandAbstract {

    private final Index targetIndex;

    public DeleteApplicationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ApplicationItem applicationToDelete = getApplication(model, targetIndex);
        model.getApplicationList().deleteItem(applicationToDelete);
        String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, APPLICATION_NAME, applicationToDelete);
        return getCommandResult(model, deleteSuccessMessage, TabName.APPLICATION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicationCommand) other).targetIndex)); // state check
    }

}
