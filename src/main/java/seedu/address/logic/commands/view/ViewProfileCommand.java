package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.tabs.TabName;

public class ViewProfileCommand extends ViewCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + PROFILE_ALIAS + ": Views a " + PROFILE_ITEM_NAME + " in "
            + "InternHunter.\nParameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PROFILE_ALIAS
            + " 5";

    private final Index targetIndex;

    public ViewProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the view profile command and returns the result message.
     * This command also auto-switches the user to the profile tab.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ProfileItem> lastShownList = model.getProfileList().getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        boolean shouldSwitchTab = false;
        if (model.getTabName() != TabName.PROFILE) {
            model.setTabName(TabName.PROFILE);
            shouldSwitchTab = true;
        }
        model.setViewIndex(targetIndex);
        String viewSuccessMessage = String.format(MESSAGE_VIEW_SUCCESS, PROFILE_NAME, targetIndex);
        return new CommandResult(viewSuccessMessage, false, false , shouldSwitchTab, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProfileCommand // instanceof handles nulls
                && targetIndex.equals(((ViewProfileCommand) other).targetIndex)); // state check
    }

}
