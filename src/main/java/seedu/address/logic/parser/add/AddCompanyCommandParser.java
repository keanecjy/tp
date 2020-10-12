package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.util.GeneralParserUtil.argumentsAreValid;

import java.util.Set;

import seedu.address.logic.commands.add.AddCompanyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.CompanyParserUtil;
import seedu.address.model.company.Address;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Email;
import seedu.address.model.company.Industry;
import seedu.address.model.company.Phone;

/**
 * Parses input arguments and creates a new AddCompanyCommand object. todo javadocs (shawn)
 */
public class AddCompanyCommandParser implements Parser<AddCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCompanyCommand
     * and returns an AddCompanyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCompanyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_INDUSTRY);

        if (!argumentsAreValid(argMultimap, PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = CompanyParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Phone phone = CompanyParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = CompanyParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = CompanyParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Industry> industryList = CompanyParserUtil.parseIndustries(argMultimap.getAllValues(PREFIX_INDUSTRY));

        CompanyItem company = new CompanyItem(companyName, phone, email, address, industryList);

        return new AddCompanyCommand(company);
    }
}
