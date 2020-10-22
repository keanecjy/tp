package seedu.address.logic.parser.find;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * todo javadocs
 */
public class FindCommandParserTest {

    private FindCommandParser findCommandParser;

    @BeforeEach
    public void setUp() {
        findCommandParser = new FindCommandParser();
    }

    // todo shawn

    // todo isaac
    // Note can just write inside these 4 methods, if i do forget any test case do inform me.
    @Test
    public void parse_invalidTypes_throwsParseException() {
        // invalid item type
        assertThrows(ParseException.class, () -> findCommandParser.parse("hello"));
        assertThrows(ParseException.class, () -> findCommandParser.parse("1"));
        assertThrows(ParseException.class, () -> findCommandParser.parse(""));
    }

    @Test
    public void parse_missingInput_throwsParseException() {
        // missing description as input
        assertThrows(ParseException.class, () -> findCommandParser.parse(APPLICATION_ALIAS));
        assertThrows(ParseException.class, () -> findCommandParser.parse(APPLICATION_ALIAS + " "));
    }

    @Test
    public void parse_listAppTrue_success() throws ParseException {
        // item type app, description present -> FindApplicationCommand
        assertTrue(findCommandParser.parse(APPLICATION_ALIAS + " 3") instanceof FindApplicationCommand);
        assertTrue(findCommandParser.parse(APPLICATION_ALIAS + " developers") instanceof FindApplicationCommand);
    }

    @Test
    public void parse_listAppFalse_success() throws ParseException {
        // item type different
        assertFalse(findCommandParser.parse(COMPANY_ALIAS) instanceof FindApplicationCommand);
        assertFalse(findCommandParser.parse(PROFILE_ALIAS) instanceof FindApplicationCommand);
    }
}
