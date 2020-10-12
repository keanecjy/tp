package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.storage.company.JsonAdaptedCompanyItem;
import seedu.address.storage.person.JsonAdaptedPerson;
import seedu.address.storage.profile.JsonAdaptedProfileItem;

/**
 * Manages storage of ItemList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final ListStorage<Person, JsonAdaptedPerson> addressBookStorage;
    private final ListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage;
    private final ListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage;
    private final ListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ItemListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ListStorage<Person, JsonAdaptedPerson> addressBookStorage,
            ListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage,
            ListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage,
            ListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage,
            UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.applicationItemListStorage = applicationItemListStorage;
        this.companyItemListStorage = companyItemListStorage;
        this.profileItemListStorage = profileItemListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Get ListStorage ==============================

    @Override
    public ListStorage<Person, JsonAdaptedPerson> getAddressBookStorage() {
        return addressBookStorage;
    }

    @Override
    public ListStorage<ApplicationItem, JsonAdaptedApplicationItem> getApplicationItemListStorage() {
        return applicationItemListStorage;
    }

    @Override
    public ListStorage<CompanyItem, JsonAdaptedCompanyItem> getCompanyItemListStorage() {
        return companyItemListStorage;
    }

    @Override
    public ListStorage<ProfileItem, JsonAdaptedProfileItem> getProfileItemListStorage() {
        return profileItemListStorage;
    }

}
