package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.address.logic.commands.util.application.ApplicationCommandTestUtil.APPLICATION_ALIAS_DESC;
import static seedu.address.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.address.logic.commands.util.internship.InternshipCommandTestUtil.INTERNSHIP_ALIAS_DESC;
import static seedu.address.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_SWE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.edit.EditCommandAbstract;
import seedu.address.logic.commands.find.FindApplicationCommand;
import seedu.address.logic.commands.find.FindCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * todo javadocs
 * todo UPDATE ALL THE STRINGS(@shawn, @isaac). Abstract it like @ApplicationCommandTestUtil
 * like the " com" and " me"
 */
public class MainParserTest {

    private final MainParser parser = new MainParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        // Todo: Update test cases after add commands are added.
        //        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        //        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand()
        //        assertEquals(new AddCommand(person), command);
    }

    // todo: add delete test (Issac)
    // @Test
    // public void parseCommand_delete() throws Exception {
    //     DeleteCommand command = (DeleteCommand) parser.parseCommand(
    //             DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
    //     assertEquals(new DeleteCommand(INDEX_FIRST), command);
    // }

    // todo: When com, profile has their prefixes and syntax ready
    @Test
    public void parseCommand_edit() throws Exception {
        // assertTrue(parser.parseCommand(EditCommandAbstract.COMMAND_WORD + INTERNSHIP_ALIAS_DESC + SPACE
        //         + INDEX_FIRST.getOneBased() + JOB_TITLE_DESC_SWE) instanceof EditCommandAbstract);
        assertTrue(parser.parseCommand(EditCommandAbstract.COMMAND_WORD + INTERNSHIP_ALIAS_DESC + " "
                + INDEX_FIRST.getOneBased() + VALID_INDEX_ONE + JOB_TITLE_DESC_SWE) instanceof EditCommandAbstract);
        assertTrue(parser.parseCommand(EditCommandAbstract.COMMAND_WORD + APPLICATION_ALIAS_DESC + " "
                + INDEX_FIRST.getOneBased() + STATUS_DESC_ACCEPTED) instanceof EditCommandAbstract);
        // assertTrue(parser.parseCommand(EditCommandAbstract.COMMAND_WORD + INTERNSHIP_ALIAS_DESC + SPACE
        //         + INDEX_FIRST.getOneBased() + JOB_TITLE_DESC_SWE) instanceof EditCommandAbstract);
    }

    // todo: When com, profile has their prefixes and syntax ready
    @Test
    public void parseCommand_view_success() throws Exception {
        // assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD +  + SPACE
        //         + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " "
                + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
        // assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + SPACE + PROFILE_ALIAS + SPACE
        //         + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_match_success() throws Exception {
        assertTrue(parser.parseCommand(MatchCommand.COMMAND_WORD) instanceof MatchCommand);
        assertTrue(parser.parseCommand(MatchCommand.COMMAND_WORD + " 3") instanceof MatchCommand);
    }

    @Nested
    class MainParserToFindParserTest {

        @Test
        public void parseCommand_findValidTypes_returnsTrue() throws ParseException {
            // TODO include in when the rest are done
            // assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " me software") instanceof FindCommand);
            // assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " me 3") instanceof FindCommand);
            // assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " com hardware") instanceof FindCommand);
            // assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + " com 4") instanceof FindCommand);
            assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " developers")
                instanceof FindCommand);

            assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " 2")
                instanceof FindCommand);
        }

        @Test
        public void parseCommand_findMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand(FindCommand.COMMAND_WORD));
            assertThrows(ParseException.class, invalidMessage, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " "));
        }

        @Test
        public void parseCommand_findInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " Com"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " App"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " Me"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " Hello"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + " 1"));
        }

        @Test
        public void parseCommand_missingDescription_throwsParseException() {
            // missing description for app
            String appMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApplicationCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, appMessage, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " "));

            assertThrows(ParseException.class, appMessage, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC));

            // missing description for com
            // String comMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCompanyCommand.MESSAGE_USAGE);
            // assertThrows(ParseException.class, comMessage, () -> parser.parseCommand("find com "));
            // assertThrows(ParseException.class, comMessage, () -> parser.parseCommand("find com"));

            // missing description for me
            // String meMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProfileCommand.MESSAGE_USAGE);
            // assertThrows(ParseException.class, meMessage, () -> parser.parseCommand("find me "));
            // assertThrows(ParseException.class, meMessage, () -> parser.parseCommand("find me"));
        }
    }

    @Nested
    class MainParserToListParserTest {

        @Test
        public void parseCommand_listValidTypes_returnsTrue() throws ParseException {
            // TODO include in when the rest are done
            //assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " me") instanceof ListCommand);
            //assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " com") instanceof ListCommand);

            assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC) instanceof ListCommand);
        }

        @Test
        public void parseCommand_listMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand(ListCommand.COMMAND_WORD));
            assertThrows(ParseException.class, invalidMessage, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " "));
        }

        @Test
        public void parseCommand_listInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " Com"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " App"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " Me"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " hello"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " 1"));
        }

        @Test
        public void parseCommand_listExcessInput_throwsParseException() {
            String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.EXCESS_MESSAGE);
            assertThrows(ParseException.class, message, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " com hello"));

            assertThrows(ParseException.class, message, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " 1"));

            assertThrows(ParseException.class, message, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " me great"));
        }

    }

    @Nested
    class MainParserToSwitchParserTest {

        @Test
        public void parseCommand_switchValidTypes_returnsTrue() throws ParseException {
            assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD + " me") instanceof SwitchCommand);
            assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD + " com") instanceof SwitchCommand);
            assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC)
                instanceof SwitchCommand);
        }

        @Test
        public void parseCommand_switchMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand(SwitchCommand.COMMAND_WORD));
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand(SwitchCommand.COMMAND_WORD));
        }

        @Test
        public void parseCommand_switchInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " hello"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " 1"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " Com"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " App"));

            assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " Me"));
        }

        @Test
        public void parseCommand_switchExcessInput_throwsParseException() {
            assertThrows(ParseException.class, SwitchCommand.EXCESS_MESSAGE, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " com hello"));

            assertThrows(ParseException.class, SwitchCommand.EXCESS_MESSAGE, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + APPLICATION_ALIAS_DESC + " great"));

            assertThrows(ParseException.class, SwitchCommand.EXCESS_MESSAGE, ()
                -> parser.parseCommand(SwitchCommand.COMMAND_WORD + " me ok"));
        }
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, errorMessage, () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

}
