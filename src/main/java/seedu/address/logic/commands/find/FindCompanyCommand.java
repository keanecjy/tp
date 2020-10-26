package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyNameContainsKeyWordsPredicate;
import seedu.address.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class FindCompanyCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + COMPANY_ALIAS + ": Finds all companies in the list of companies whose names contain any of the given "
            + "keywords.\n"
            + "Parameters: KEYWORD [ANOTHER_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + " Google Facebook";

    private final CompanyNameContainsKeyWordsPredicate predicate;

    public FindCompanyCommand(CompanyNameContainsKeyWordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredCompanyList(predicate);
        model.setCompanyViewIndex(Index.fromOneBased(1));
        String message = String.format(Messages.MESSAGE_FIND_SUCCESS,
                model.getFilteredCompanyListSize(), COMPANY_NAME);
        return getCommandResult(model, message, TabName.COMPANY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCompanyCommand // instanceof handles nulls
                && predicate.equals(((FindCompanyCommand) other).predicate)); // state check
    }
}
