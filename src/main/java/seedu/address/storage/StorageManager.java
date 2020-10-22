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
import seedu.address.model.item.ReadOnlyItemList;
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
    private final ItemListStorage<Person, JsonAdaptedPerson> addressBookStorage;
    private final ItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage;
    private final ItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage;
    private final ItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ItemListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ItemListStorage<Person, JsonAdaptedPerson> addressBookStorage,
            ItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage,
            ItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage,
            ItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage,
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

    // ================ Item List Storage ==============================

    @Override
    public Path getApplicationItemListFilePath() {
        logger.fine("Accessing application item list storage");
        return applicationItemListStorage.getItemListFilePath();
    }

    @Override
    public Optional<ReadOnlyItemList<ApplicationItem>> readApplicationItemList()
            throws DataConversionException, IOException {
        logger.fine("Accessing application item list storage");
        return applicationItemListStorage.readItemList();
    }

    @Override
    public Optional<ReadOnlyItemList<ApplicationItem>> readApplicationItemList(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Accessing application item list storage");
        return applicationItemListStorage.readItemList(filePath);
    }

    @Override
    public void saveApplicationItemList(ReadOnlyItemList<ApplicationItem> itemList) throws IOException {
        logger.fine("Accessing application item list storage");
        applicationItemListStorage.saveItemList(itemList);
    }

    @Override
    public void saveApplicationItemList(ReadOnlyItemList<ApplicationItem> itemList, Path filePath) throws IOException {
        logger.fine("Accessing application item list storage");
        applicationItemListStorage.saveItemList(itemList, filePath);
    }

    @Override
    public Path getCompanyItemListFilePath() {
        logger.fine("Accessing company item list storage");
        return companyItemListStorage.getItemListFilePath();
    }

    @Override
    public Optional<ReadOnlyItemList<CompanyItem>> readCompanyItemList() throws DataConversionException, IOException {
        logger.fine("Accessing company item list storage");
        return companyItemListStorage.readItemList();
    }

    @Override
    public Optional<ReadOnlyItemList<CompanyItem>> readCompanyItemList(
            Path filePath) throws DataConversionException, IOException {
        return companyItemListStorage.readItemList(filePath);
    }

    @Override
    public void saveCompanyItemList(ReadOnlyItemList<CompanyItem> itemList) throws IOException {
        logger.fine("Accessing company item list storage");
        companyItemListStorage.saveItemList(itemList);
    }

    @Override
    public void saveCompanyItemList(ReadOnlyItemList<CompanyItem> itemList, Path filePath) throws IOException {
        logger.fine("Accessing company item list storage");
        companyItemListStorage.saveItemList(itemList, filePath);
    }

    @Override
    public Path getProfileItemListFilePath() {
        logger.fine("Accessing profile item list storage");
        return profileItemListStorage.getItemListFilePath();
    }

    @Override
    public Optional<ReadOnlyItemList<ProfileItem>> readProfileItemList() throws DataConversionException, IOException {
        logger.fine("Accessing profile item list storage");
        return profileItemListStorage.readItemList();
    }

    @Override
    public Optional<ReadOnlyItemList<ProfileItem>> readProfileItemList(
            Path filePath) throws DataConversionException, IOException {
        logger.fine("Accessing profile item list storage");
        return profileItemListStorage.readItemList(filePath);
    }

    @Override
    public void saveProfileItemList(ReadOnlyItemList<ProfileItem> itemList) throws IOException {
        logger.fine("Accessing profile item list storage");
        profileItemListStorage.saveItemList(itemList);
    }

    @Override
    public void saveProfileItemList(ReadOnlyItemList<ProfileItem> itemList, Path filePath) throws IOException {
        logger.fine("Accessing profile item list storage");
        profileItemListStorage.saveItemList(itemList, filePath);
    }

}
