package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FilterableItemList;
import seedu.address.model.ItemListManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.testutil.PersonBuilder;
import seedu.address.ui.tabs.TabName;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommandCopy, addAliceCommand);

        // different types -> returns false
        assertNotEquals(addAliceCommand, 1);

        // null -> returns false
        assertNotEquals(addAliceCommand, null);

        // different person -> returns false
        assertNotEquals(addBobCommand, addAliceCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternHunterFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternHunterFilePath(Path internHunterFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<Person> getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<CompanyItem> getCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<ApplicationItem> getApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<ProfileItem> getProfileList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTabName(TabName tabName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TabName getTabName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompanyViewIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfileViewIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationViewIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getCompanyViewIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getApplicationViewIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getProfileViewIndex() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public FilterableItemList<Person> getAddressBook() {
            FilterableItemList<Person> itemList = new ItemListManager<>();
            itemList.addItem(person);
            return itemList;
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {

        @Override
        public FilterableItemList<Person> getAddressBook() {
            return new ItemListManager<>();
        }
    }

}
