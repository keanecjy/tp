package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.item.ItemList;
import seedu.address.model.item.ReadOnlyItemList;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.storage.company.JsonAdaptedCompanyItem;
import seedu.address.storage.person.JsonAdaptedPerson;
import seedu.address.storage.profile.JsonAdaptedProfileItem;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonItemListStorage<Person, JsonAdaptedPerson> addressBookStorage = new JsonItemListStorage<>(
                getTempFilePath("ab"), Person.class, JsonAdaptedPerson.class);
        JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
                new JsonItemListStorage<>(getTempFilePath("app"), ApplicationItem.class,
                        JsonAdaptedApplicationItem.class);
        JsonItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage =
                new JsonItemListStorage<>(getTempFilePath("app"), CompanyItem.class,
                        JsonAdaptedCompanyItem.class);
        JsonItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage =
                new JsonItemListStorage<>(getTempFilePath("app"), ProfileItem.class,
                        JsonAdaptedProfileItem.class);

        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, applicationItemListStorage, companyItemListStorage,
                profileItemListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonItemListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBoolStorageTest} class.
         */
        ItemList<Person> original = getTypicalAddressBook();
        storageManager.getAddressBookStorage().saveItemList(original);
        ReadOnlyItemList<Person> retrieved = storageManager.getAddressBookStorage().readItemList().get();
        assertEquals(original, new ItemList<>(retrieved));
    }

    @Test
    public void getItemListFilePath() {
        assertNotNull(storageManager.getAddressBookStorage().getItemListFilePath());
    }

}
