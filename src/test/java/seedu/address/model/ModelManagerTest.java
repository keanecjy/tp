package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.item.ItemList;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ItemListManager<Person>(), modelManager.getAddressBook());
        assertEquals(new ItemListManager<CompanyItem>(), modelManager.getCompanyList());
        assertEquals(new ItemListManager<ApplicationItem>(), modelManager.getApplicationList());
        assertEquals(new ItemListManager<ProfileItem>(), modelManager.getProfileList());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setInternHunterFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternHunterFilePath(null));
    }

    @Test
    public void setInternHunterFilePath_validPath_setsInternHunterFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInternHunterFilePath(path);
        assertEquals(path, modelManager.getInternHunterFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.getAddressBook().hasItem(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.getAddressBook().hasItem(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.getAddressBook().addItem(ALICE);
        assertTrue(modelManager.getAddressBook().hasItem(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getAddressBook()
                .getFilteredItemList().remove(0));
    }

    @Test
    public void equals() {
        ItemList<Person> addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ItemList<Person> differentAddressBook = new ItemList<>();
        ItemList<CompanyItem> companyList = new ItemList<>();
        ItemList<InternshipItem> internshipList = new ItemList<>();
        ItemList<ApplicationItem> applicationList = new ItemList<>();
        ItemList<ProfileItem> profileList = new ItemList<>();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, companyList, applicationList,
                profileList,
                userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, companyList,
                applicationList, profileList, userPrefs);
        assertEquals(modelManagerCopy, modelManager);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(modelManager, null);

        // different types -> returns false
        assertNotEquals(modelManager, 5);

        // different addressBook -> returns false
        assertNotEquals(new ModelManager(differentAddressBook, companyList,
                applicationList, profileList, userPrefs), modelManager);

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.getAddressBook()
                .updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(new ModelManager(addressBook, companyList,
                applicationList, profileList, userPrefs), modelManager);

        // resets modelManager to initial state for upcoming tests
        modelManager.getAddressBook().updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertNotEquals(new ModelManager(addressBook, companyList,
                applicationList, profileList, differentUserPrefs), modelManager);
    }
}
