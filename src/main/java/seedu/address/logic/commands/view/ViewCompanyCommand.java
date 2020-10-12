package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.tabs.TabName;

public class ViewCompanyCommand extends ViewCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMPANY_ALIAS + ": Views a " + COMPANY_NAME + " in "
            + "InternHunter.\nParameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + COMPANY_ALIAS
            + " 3";

    private final Index targetIndex;

    public ViewCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the view company command and returns the result message.
     * This command also auto-switches the user to the company tab.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CompanyItem> lastShownList = model.getCompanyList().getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME));
        }

        boolean shouldSwitchTab = false;
        if (model.getTabName() != TabName.COMPANY) {
            model.setTabName(TabName.COMPANY);
            shouldSwitchTab = true;
        }
        model.setViewIndex(targetIndex);
        String viewSuccessMessage = String.format(MESSAGE_VIEW_SUCCESS, COMPANY_NAME, targetIndex);
        return new CommandResult(viewSuccessMessage, false, false , shouldSwitchTab, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCompanyCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCompanyCommand) other).targetIndex)); // state check
    }

}
