package seedu.address.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.address.logic.parser.util.ApplicationParserUtil.parseStatus;
import static seedu.address.logic.parser.util.ApplicationParserUtil.parseStatusDate;
import static seedu.address.logic.parser.util.GeneralParserUtil.getIndexInPreamble;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditApplicationCommand;
import seedu.address.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditApplicationCommand object.
 */
public class EditApplicationCommandParser implements Parser<EditApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditApplicationCommand
     * and returns an EditApplicationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_STATUS_DATE);

        Index index = getIndexInPreamble(argMultimap, EditApplicationCommand.MESSAGE_USAGE);

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editApplicationDescriptor.setStatus(parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS_DATE).isPresent()) {
            editApplicationDescriptor.setStatusDate(parseStatusDate(argMultimap.getValue(PREFIX_STATUS_DATE).get()));
        }

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicationCommand.MESSAGE_USAGE));
        }

        return new EditApplicationCommand(index, editApplicationDescriptor);
    }

}
