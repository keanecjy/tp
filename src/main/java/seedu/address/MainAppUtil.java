package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.internship.exceptions.InconsistentInternshipException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemList;
import seedu.address.model.item.ReadOnlyItemList;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.util.ItemUtil;
import seedu.address.storage.ListStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.item.JsonAdaptedItem;

/**
 * A collection of utility functions for the MainApp.
 */
public class MainAppUtil {

    private static final Logger logger = LogsCenter.getLogger(MainAppUtil.class);

    /**
     * Gets the initial item list.
     *
     * @param itemListStorage storage of the item list.
     * @param <T>             type of the item.
     * @param <U>             type of the json adapted item.
     * @return initial item list.
     */
    public static <T extends Item, U extends JsonAdaptedItem> ReadOnlyItemList<T> initItemList(
            ListStorage<T, U> itemListStorage, String itemType) {
        Optional<ReadOnlyItemList<T>> itemListOptional;
        ReadOnlyItemList<T> initialItemListData;
        try {
            itemListOptional = itemListStorage.readItemList();
            if (itemListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample " + itemType + " item list");
            }
            //to do sample data
            initialItemListData = itemListOptional.orElse(new ItemList<>());
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty "
                    + itemType + "item list");
            initialItemListData = new ItemList<>();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty "
                    + itemType + "item list");
            initialItemListData = new ItemList<>();
        }

        return initialItemListData;
    }

    /**
     * Matches internships in the application list with the ones in the company list.
     *
     * @param applicationItemList list of application items.
     * @param companyItemList     list of company items.
     * @throws InconsistentInternshipException an application has an internship
     *                                         that does not exist in any company's list of internships.
     */
    private static void matchInternships(ItemList<ApplicationItem> applicationItemList,
            ItemList<CompanyItem> companyItemList) throws InconsistentInternshipException {
        for (ApplicationItem applicationItem : applicationItemList.getItemList()) {
            final InternshipItem applicationInternshipItem = applicationItem.getInternshipItem();

            final Optional<InternshipItem> correctInternshipItem = companyItemList.getItemList().stream()
                    .map(CompanyItem::getInternships).reduce(new ArrayList<>(), (x, y) -> {
                        x.addAll(y);
                        return x;
                    }).stream().filter(companyInternshipItem -> companyInternshipItem.equals(applicationInternshipItem))
                    .findAny();

            if (correctInternshipItem.isEmpty()) {
                applicationItemList.resetData(new ItemList<>());
                companyItemList.resetData(new ItemList<>());
                throw new InconsistentInternshipException();
            }

            //Changes the current application item with the one with the correct internship object.
            applicationItemList.setItem(applicationItem, new ApplicationItem(correctInternshipItem.get(),
                    applicationItem.getStatus(), applicationItem.getStatusDate()));
        }
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    public static Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ItemList<Person> addressBook = new ItemList<>(initItemList(storage.getAddressBookStorage(), "AB3"));
        ItemList<CompanyItem> companyItemList = new ItemList<>(initItemList(storage.getCompanyItemListStorage(),
                ItemUtil.COMPANY_NAME));
        ItemList<ApplicationItem> applicationItemList =
                new ItemList<>(initItemList(storage.getApplicationItemListStorage(), ItemUtil.APPLICATION_NAME));
        ItemList<ProfileItem> profileItemList = new ItemList<>(initItemList(storage.getProfileItemListStorage(),
                ItemUtil.PROFILE_ITEM_NAME));

        try {
            matchInternships(applicationItemList, companyItemList);
        } catch (InconsistentInternshipException e) {
            logger.warning(e.getMessage());
        }

        return new ModelManager(addressBook, companyItemList, applicationItemList, profileItemList, userPrefs);
    }

    public static void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    public static Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    public static UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }
}
