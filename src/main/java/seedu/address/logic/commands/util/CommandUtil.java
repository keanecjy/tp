package seedu.address.logic.commands.util;

import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.tabs.TabName;

/**
 * Encapsulates common / shared execution processes between commands.
 */
public class CommandUtil {

    public static CompanyItem getCompany(Model model, Index companyIndex) throws CommandException {
        List<CompanyItem> lastShownList = model.getCompanyList().getFilteredItemList();

        if (companyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME));
        }

        return lastShownList.get(companyIndex.getZeroBased());
    }

    public static ApplicationItem getApplication(Model model, Index applicationIndex) throws CommandException {
        List<ApplicationItem> lastShownList = model.getApplicationList().getFilteredItemList();

        if (applicationIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME));
        }

        return lastShownList.get(applicationIndex.getZeroBased());
    }

    public static ProfileItem getProfileItem(Model model, Index profileItemIndex) throws CommandException {
        List<ProfileItem> lastShownList = model.getProfileList().getFilteredItemList();

        if (profileItemIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        return lastShownList.get(profileItemIndex.getZeroBased());
    }

    /**
     * Gets the feedback message of the operation result for display and indicates whether tabs need to be switched or
     * not.
     *
     * @param model Model of application.
     * @param message Feedback message of the operation result for display.
     * @return Feedback message of the operation result for display.
     */
    public static CommandResult getCommandResult(Model model, String message, TabName tabName) {
        if (model.getTabName() != tabName) {
            model.setTabName(tabName);
            return new CommandResult(message, false, false, true, true);
        } else {
            return new CommandResult(message);
        }
    }

}
