package seedu.address.logic.parser.switchparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.tabs.TabName;

/**
 * Parses input arguments and creates a new SwitchCommand object
 */
public class SwitchCommandParser implements Parser<SwitchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SwitchCommand
     * and returns a SwitchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SwitchCommand parse(String args) throws ParseException {
        try {
            TabName tabName = ParserUtil.parseTab(args);
            return new SwitchCommand(tabName);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE), pe);
        }
    }
}
