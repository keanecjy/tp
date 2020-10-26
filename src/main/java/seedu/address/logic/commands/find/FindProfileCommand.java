package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.ProfileItemContainsKeywordPredicate;
import seedu.address.ui.tabs.TabName;

/**
 * Finds and lists all profileItem in Internhunter whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindProfileCommand extends FindCommand {


    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PROFILE_ALIAS + ": Finds all profile items whose titles contain any of "
            + "the specified keywords (case-insensitive).\n"
            + "Parameters: KEYWORD [ANOTHER_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " " + PROFILE_ALIAS + " competition html hackathon" + "\n";

    private final ProfileItemContainsKeywordPredicate predicate;

    public FindProfileCommand(ProfileItemContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredProfileList(predicate);
        model.setProfileViewIndex(Index.fromOneBased(1));
        String message = String.format(Messages.MESSAGE_FIND_SUCCESS,
                model.getFilteredProfileListSize(), PROFILE_NAME);
        return getCommandResult(model, message, TabName.PROFILE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProfileCommand // instanceof handles nulls
                && predicate.equals(((FindProfileCommand) other).predicate)); // state check
    }
}
