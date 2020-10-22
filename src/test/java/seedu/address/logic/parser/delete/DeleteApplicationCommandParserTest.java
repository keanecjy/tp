package seedu.address.logic.parser.delete;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.delete.DeleteApplicationCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteApplicationCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteApplicationCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the GeneralParserUtil, and
 * therefore should be covered by the GeneralParserUtilTest.
 */
public class DeleteApplicationCommandParserTest {

    private final DeleteApplicationCommandParser parser = new DeleteApplicationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteApplicationCommand() {
        assertParseSuccess(parser, "1", new DeleteApplicationCommand(INDEX_FIRST));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        final String messageExpected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteApplicationCommand.MESSAGE_USAGE);

        // empty argument
        assertParseFailure(parser, "", messageExpected);
        // blank argument
        assertParseFailure(parser, "  ", messageExpected);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
    }

}
