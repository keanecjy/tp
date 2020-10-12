package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.ui.tabs.TabName;

public class ViewApplicationCommand extends ViewCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + APPLICATION_ALIAS + ": Views a profile in "
            + "InternHunter.\nParameters: "
            + "INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + APPLICATION_ALIAS
            + " 2";

    private final Index targetIndex;

    public ViewApplicationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the view application command and returns the result message.
     * This command also auto-switches the user to the application tab.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ApplicationItem> lastShownList = model.getApplicationList().getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME));
        }

        // TODO after model/ui methods are done: Check if current item is already in view
        // TODO: Set model to show view

        String viewSuccessMessage = String.format(MESSAGE_VIEW_SUCCESS, APPLICATION_NAME, targetIndex);
        return getCommandResult(model, viewSuccessMessage, TabName.APPLICATION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewApplicationCommand // instanceof handles nulls
                && targetIndex.equals(((ViewApplicationCommand) other).targetIndex)); // state check
    }

}
