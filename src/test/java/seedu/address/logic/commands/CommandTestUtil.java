package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.address.model.item.ItemList;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemContainsKeywordPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;

    // Preamble
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY_RANDOM = "NonEmptyPreamble";
    public static final String PREAMBLE_EMPTY = "";

    // Valid indexes
    public static final String VALID_INDEX_ONE = " " + PREFIX_INDEX + INDEX_FIRST;
    public static final String VALID_INDEX_TWO = " " + PREFIX_INDEX + INDEX_SECOND;

    // Invalid indexes
    public static final String INVALID_INDEX_RANDOM_STRING = " " + PREFIX_INDEX + "random";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ItemList<Person> expectedAddressBook = new ItemList<>(actualModel.getAddressBook().getUnfilteredItemList());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getAddressBook().getFilteredItemList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook().getUnfilteredItemList());
        assertEquals(expectedFilteredList, actualModel.getAddressBook().getFilteredItemList());
    }

    /**
     * Todo: remove
     * @param model
     * @param targetIndex
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getAddressBook().getFilteredItemList().size());
        Person person = model.getAddressBook().getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.getAddressBook().updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getAddressBook().getFilteredItemList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the profileItem at the given {@code targetIndex} in the
     * {@code model}'s profile list.
     */
    public static void showProfileItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProfileList().size());

        ProfileItem profileItem = model.getProfileItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitTitle = profileItem.getTitle().toString().split("\\s+");
        model.updateFilteredProfileList(new ProfileItemContainsKeywordPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredProfileListSize());
    }

    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s application list.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        ApplicationItem applicationItem = model.getApplicationItemFromFilteredList(
                targetIndex.getZeroBased());
        final String[] splitJobTitle = applicationItem.getJobTitleOfInternshipItem().toString().split("\\s+");
        model.updateFilteredApplicationList(
                new ApplicationNameContainsKeyWordsPredicate(Arrays.asList(splitJobTitle[0])));

        assertEquals(1, model.getFilteredApplicationListSize());
    }

}
