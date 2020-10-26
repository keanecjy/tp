package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class ListCompanyCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_LIST_SUCCESS, COMPANY_NAME);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_ITEMS);
        model.setCompanyViewIndex(Index.fromOneBased(1)); // reset to zero for consistency
        return getCommandResult(model, MESSAGE_SUCCESS, TabName.COMPANY);
    }
}
