package seedu.address.logic.parser.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteCompanyCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.GeneralParserUtil;

/**
 * Parses input arguments and creates a new DeleteCompanyCommand object.
 */
public class DeleteCompanyCommandParser implements Parser<DeleteCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCompanyCommand
     * and returns a DeleteCompanyCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return DeleteCompanyCommand object for execution
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteCompanyCommand parse(String args) throws ParseException {

        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, DeleteCompanyCommand.MESSAGE_USAGE);
        Index index = GeneralParserUtil.parseIndex(args);
        return new DeleteCompanyCommand(index);
    }
}
