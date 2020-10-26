package seedu.address.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.address.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.address.logic.commands.find.FindProfileCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.ProfileItemContainsKeywordPredicate;

public class FindProfileCommandParser implements Parser<FindProfileCommand> {

    @Override
    public FindProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindProfileCommand.MESSAGE_USAGE);
        String[] profileItemTitleKeywords = getTrimmedArgsKeywords(args);
        ProfileItemContainsKeywordPredicate predicate =
                new ProfileItemContainsKeywordPredicate(Arrays.asList(profileItemTitleKeywords));
        return new FindProfileCommand(predicate);
    }
}
