package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_PHONE;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.tabs.TabName;

/**
 * Adds a Company to the Model's Company list. todo javadocs (shawn)
 */
public class AddCompanyCommand extends AddCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMPANY_ALIAS
            + ": Adds a " + COMPANY_NAME + " to "
            + "InternHunter.\nParameters: "
            + PREFIX_COMPANY_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_INDUSTRY + "INDUSTRY_TYPE]...\n"
            + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_PHONE + "65218000 "
            + PREFIX_EMAIL + "GoogleHires@gmail.com "
            + PREFIX_ADDRESS + "70 Pasir Panjang Rd, #03-71 "
            + PREFIX_INDUSTRY + "Cloud Computing "
            + PREFIX_INDUSTRY + "Artificial Intelligence";

    private final CompanyItem toAdd;

    /**
     * Creates an AddCompanyCommand to add the specified {@code CompanyItem}
     */
    public AddCompanyCommand(CompanyItem company) {
        requireNonNull(company);
        toAdd = company;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCompany(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, COMPANY_NAME));
        }

        model.addCompany(toAdd);
        setCompanyViewIndex(model);
        String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, COMPANY_NAME, toAdd);
        return getCommandResult(model, addSuccessMessage, TabName.COMPANY);
    }

    /**
     * Sets the company view index to the newly added company.
     *
     * @param model {@code Model} which the command should operate on.
     */
    private void setCompanyViewIndex(Model model) {
        int size = model.getFilteredCompanyListSize();
        model.setCompanyViewIndex(Index.fromOneBased(size));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCompanyCommand // instanceof handles nulls
                && toAdd.equals(((AddCompanyCommand) other).toAdd));
    }

}
