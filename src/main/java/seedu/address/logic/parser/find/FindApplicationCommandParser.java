package seedu.address.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.address.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.address.logic.commands.find.FindApplicationCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.ApplicationNameContainsKeyWordsPredicate;

public class FindApplicationCommandParser implements Parser<FindApplicationCommand> {

    @Override
    public FindApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindApplicationCommand.MESSAGE_USAGE);
        String[] jobTitleKeywords = getTrimmedArgsKeywords(args);
        ApplicationNameContainsKeyWordsPredicate predicate =
            new ApplicationNameContainsKeyWordsPredicate(Arrays.asList(jobTitleKeywords));
        return new FindApplicationCommand(predicate);
    }
}
