package seedu.address.logic.parser.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteProfileCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.GeneralParserUtil;


/**
 * Parses input arguments and creates a new DeleteProfileCommand object.
 */
public class DeleteProfileCommandParser implements Parser<DeleteProfileCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProfileItemCommand
     * and returns a DeleteProfileItemCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return DeleteProfileCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, DeleteProfileCommand.MESSAGE_USAGE);
        Index index = GeneralParserUtil.parseIndex(args);
        return new DeleteProfileCommand(index);
    }
}
