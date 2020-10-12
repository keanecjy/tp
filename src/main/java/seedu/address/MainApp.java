package seedu.address;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.JsonItemListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.ListStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.storage.company.JsonAdaptedCompanyItem;
import seedu.address.storage.person.JsonAdaptedPerson;
import seedu.address.storage.profile.JsonAdaptedProfileItem;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternHunter ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = MainAppUtil.initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = MainAppUtil.initPrefs(userPrefsStorage);
        ListStorage<Person, JsonAdaptedPerson> addressBookStorage = new JsonItemListStorage<>(
                userPrefs.getAddressBookFilePath(), Person.class, JsonAdaptedPerson.class);
        ListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage = new JsonItemListStorage<>(
                userPrefs.getApplicationItemListFilePath(), ApplicationItem.class, JsonAdaptedApplicationItem.class);
        ListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage = new JsonItemListStorage<>(
                userPrefs.getCompanyItemListFilePath(), CompanyItem.class, JsonAdaptedCompanyItem.class);
        ListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage = new JsonItemListStorage<>(
                userPrefs.getProfileItemListFilePath(), ProfileItem.class, JsonAdaptedProfileItem.class);

        storage = new StorageManager(addressBookStorage, applicationItemListStorage,
                companyItemListStorage, profileItemListStorage, userPrefsStorage);

        MainAppUtil.initLogging(config);

        model = MainAppUtil.initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting InternHunter " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping InternHunter ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
