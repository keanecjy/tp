package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.address.logic.parser.util.GeneralParserUtil.arePrefixesPresent;
import static seedu.address.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.address.logic.parser.util.InternshipParserUtil.parseJobTitle;
import static seedu.address.logic.parser.util.InternshipParserUtil.parseRequirements;
import static seedu.address.logic.parser.util.InternshipParserUtil.parseWage;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.InternshipParserUtil;
import seedu.address.model.internship.JobTitle;
import seedu.address.model.internship.Period;
import seedu.address.model.internship.Requirement;
import seedu.address.model.internship.Wage;

/**
 * Parses input arguments and creates a new AddApplicationCommand object.
 */
public class AddInternshipCommandParser implements Parser<AddInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddInternshipCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_TITLE,
                PREFIX_PERIOD, PREFIX_WAGE, PREFIX_REQUIREMENT);

        // Todo: Wage will be compulsory until its status can be resolved
        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_TITLE, PREFIX_WAGE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap, AddInternshipCommand.MESSAGE_USAGE);
        JobTitle jobTitle = parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get());
        Wage wage = parseWage(argMultimap.getValue(PREFIX_WAGE).get());
        Period period = getPeriod(argMultimap);
        Set<Requirement> requirements = parseRequirements(argMultimap.getAllValues(PREFIX_REQUIREMENT));

        return new AddInternshipCommand(companyIndex, jobTitle, wage, period, requirements);
    }

    /**
     * Obtains the period from the user input. Returns default "-" if unspecified.
     *
     * @param argMultimap ArgumentMultimap.
     * @return Period for this application.
     * @throws ParseException if the given {@code Period} is invalid.
     */
    private Period getPeriod(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_PERIOD).isPresent()) {
            return InternshipParserUtil.parsePeriod(argMultimap.getValue(PREFIX_PERIOD).get());
        } else {
            return new Period("-");
        }
    }

}
