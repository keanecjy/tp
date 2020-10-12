package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.logic.commands.util.CommandUtil.getCompany;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.tabs.TabName;

/**
 * Deletes a Company from the Model's Company list. todo javadocs (shawn)
 */
public class DeleteCompanyCommand extends DeleteCommandAbstract {

    private final Index targetIndex;

    public DeleteCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CompanyItem companyToDelete = getCompany(model, targetIndex);

        // Delete all internships in the company
        deleteAllInternshipsInCompany(model, companyToDelete);

        // Delete the company
        model.getCompanyList().deleteItem(companyToDelete);

        String message = String.format(MESSAGE_DELETED_ITEM, COMPANY_NAME, companyToDelete);
        return getCommandResult(model, message, TabName.COMPANY);
    }

    private void deleteAllInternshipsInCompany(Model model, CompanyItem company) throws CommandException {
        int numberOfInternships = company.getNumberOfInternships();
        for (int i = 0; i < numberOfInternships; i++) {
            new DeleteInternshipCommand(targetIndex, Index.fromZeroBased(0)).execute(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCompanyCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCompanyCommand) other).targetIndex)); // state check
    }

}
