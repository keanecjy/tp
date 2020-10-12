package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.tabs.TabName;

public class DeleteProfileCommand extends DeleteCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PROFILE_ALIAS
            + ": Deletes a "
            + PROFILE_ITEM_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters: INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + PROFILE_ALIAS + " 1\n";

    private final Index targetIndex;

    public DeleteProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ProfileItem> lastShownList = model.getProfileList().getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        ProfileItem profileToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.getProfileList().deleteItem(profileToDelete);

        return getCommandResult(model, String.format(Messages.MESSAGE_DELETED_ITEM,
            PROFILE_NAME, profileToDelete), TabName.PROFILE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProfileCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProfileCommand) other).targetIndex)); // state check
    }
}
