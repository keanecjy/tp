package seedu.address.logic.parser.find;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindProfileCommand;
import seedu.address.model.profile.ProfileItemContainsKeywordPredicate;

class FindProfileCommandParserTest {
    private static final String MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindProfileCommand.MESSAGE_USAGE);

    private FindProfileCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new FindProfileCommandParser();
    }

    @Test
    public void parse_missingDescription_throwsParseException() {
        assertParseFailure(parser, "    ", MESSAGE);
        assertParseFailure(parser, " ", MESSAGE);
    }

    @Test
    public void parse_validArguments_returnsFindApplicationCommand() {
        FindProfileCommand expectedCommand = new FindProfileCommand(
                new ProfileItemContainsKeywordPredicate(Arrays.asList("Software", "Hardware", "Developers")));

        assertParseSuccess(parser, "Software Hardware Developers", expectedCommand);

        assertParseSuccess(parser, "\n Software \n \t Hardware Developers\t",
                expectedCommand);
    }
}
