package seedu.address.logic.commands.util;

import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.ui.tabs.TabName;

/**
 * Encapsulates common / shared execution processes between commands.
 */
public class CommandUtil {

    /**
     * Gets a specified internship from a specified company, checking the indexes' validity while doing so.
     *
     * @param model Model of application.
     * @param companyIndex Company index referenced.
     * @param internshipIndex Internship index referenced.
     * @return InternshipItem corresponding to the 2 indexes.
     * @throws CommandException If indexes are out of bounds.
     */
    public static InternshipItem getInternshipFromCompany(Model model, Index companyIndex, Index internshipIndex)
            throws CommandException {
        List<CompanyItem> lastShownCompanyList = model.getCompanyList().getFilteredItemList();

        if (companyIndex.getZeroBased() >= lastShownCompanyList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME));
        }

        CompanyItem companyItem = lastShownCompanyList.get(companyIndex.getZeroBased());
        return companyItem.getInternship(internshipIndex);
    }

    /**
     * Gets the feedback message of the operation result for display and indicates whether tabs need to be switched or
     * not.
     *
     * @param model Model of application.
     * @param messageSuccess Feedback message of the operation result for display (indicates operation success).
     * @return Feedback message of the operation result for display.
     */
    public static CommandResult getCommandResult(Model model, String messageSuccess, TabName tabName) {
        if (model.getTabName() != tabName) {
            model.setTabName(tabName);
            return new CommandResult(messageSuccess, false, false, true);
        } else {
            return new CommandResult(messageSuccess);
        }
    }
}
