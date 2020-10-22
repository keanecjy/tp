package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
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
import seedu.address.model.item.ItemList;
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
        public ObservableList<CompanyItem> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ItemList<CompanyItem> getUnfilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the company item list
         */
        @Override
        public ObservableList<CompanyItem> getCompanyItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(CompanyItem companyItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(CompanyItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(CompanyItem companyItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(CompanyItem target, CompanyItem editedCompanyItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompanyList(ItemList<CompanyItem> companyList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<ApplicationItem> getApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ApplicationItem> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ItemList<ApplicationItem> getUnfilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(ApplicationItem applicationItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(ApplicationItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSameApplication(ApplicationItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(ApplicationItem applicationItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationList(ItemList<ApplicationItem> applicationList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilterableItemList<ProfileItem> getProfileList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ProfileItem> getFilteredProfileList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ItemList<ProfileItem> getUnfilteredProfileList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the profile item list
         */
        @Override
        public ObservableList<ProfileItem> getProfileItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProfileItem(ProfileItem profileItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProfileItem(ProfileItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProfileItem(ProfileItem profileItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfileItem(ProfileItem target, ProfileItem editedProfileItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProfileList(Predicate<? super ProfileItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfileList(ItemList<ProfileItem> profileList) {
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

        /**
         * Gets ProfileItem from Filtered Profile list.
         *
         * @param index
         */
        @Override
        public ProfileItem getProfileItemFromFilteredList(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CompanyItem getCompanyItemFromFilteredList(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ApplicationItem getApplicationItemFromFilteredList(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredCompanyListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredApplicationListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredProfileListSize() {
            throw new AssertionError("This method should not be called.");
        }
    }

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
