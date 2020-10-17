package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.util.GeneralParserUtil.arePrefixesPresent;
import static seedu.address.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.address.logic.parser.util.GeneralParserUtil.parseIndex;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.add.AddApplicationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ApplicationParserUtil;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;

/**
 * Parses input arguments and creates a new AddApplicationCommand object.
 */
public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddApplicationCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_STATUS, PREFIX_STATUS_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap, AddApplicationCommand.MESSAGE_USAGE);
        Index internshipIndex;
        try {
            internshipIndex = parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }
        Status status = getStatus(argMultimap);
        StatusDate statusDate = getStatusDate(argMultimap);

        return new AddApplicationCommand(companyIndex, internshipIndex, status, statusDate);
    }

    /**
     * Obtains the status from the user input. Returns default applied status if not provided by user.
     *
     * @param argMultimap ArgumentMultimap.
     * @return Status for this application.
     * @throws ParseException if the given {@code status} is invalid.
     */
    private Status getStatus(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            return ApplicationParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } else {
            return Status.APPLIED;
        }
    }

    /**
     * Obtains the status from the user input. Returns default today's date if not provided by user.
     *
     * @param argMultimap ArgumentMultimap.
     * @return StatusDate for this application.
     * @throws ParseException if the given {@code StatusDate} is invalid.
     */
    private StatusDate getStatusDate(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS_DATE).isPresent()) {
            return ApplicationParserUtil.parseStatusDate(argMultimap.getValue(PREFIX_STATUS_DATE).get());
        } else {
            return new StatusDate(LocalDateTime.now());
        }
    }

}
