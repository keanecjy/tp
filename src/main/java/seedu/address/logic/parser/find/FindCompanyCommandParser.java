package seedu.address.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.address.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.address.logic.commands.find.FindCompanyCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.CompanyNameContainsKeyWordsPredicate;

public class FindCompanyCommandParser implements Parser<FindCompanyCommand> {

    @Override
    public FindCompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindCompanyCommand.MESSAGE_USAGE);
        String[] companyNameKeywords = getTrimmedArgsKeywords(args);
        CompanyNameContainsKeyWordsPredicate predicate =
                new CompanyNameContainsKeyWordsPredicate(Arrays.asList(companyNameKeywords));
        return new FindCompanyCommand(predicate);
    }

}
