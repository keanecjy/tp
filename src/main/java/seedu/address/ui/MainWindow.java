package seedu.address.ui;

import static seedu.address.ui.GuardClauseUi.isEmptyDataList;
import static seedu.address.ui.GuardClauseUi.isEmptyDisplay;
import static seedu.address.ui.GuardClauseUi.isEmptyListPanel;
import static seedu.address.ui.tabs.TabName.APPLICATION;
import static seedu.address.ui.tabs.TabName.COMPANY;
import static seedu.address.ui.tabs.TabName.PROFILE;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.item.Item;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.display.ApplicationDisplay;
import seedu.address.ui.display.CompanyDisplay;
import seedu.address.ui.display.InformationDisplay;
import seedu.address.ui.display.ProfileDisplay;
import seedu.address.ui.panel.ApplicationListPanel;
import seedu.address.ui.panel.CompanyListPanel;
import seedu.address.ui.panel.ListPanel;
import seedu.address.ui.panel.ProfileListPanel;
import seedu.address.ui.tabs.TabName;
import seedu.address.ui.tabs.Tabs;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    //FXML
    private static final String FXML = "MainWindow.fxml";

    //FXML properties
    private static final int PERSON_LIST_HEIGHT_SHRINK = 255;
    private static final int RESULT_HEIGHT_SHRINK = 350;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // data portion
    private ObservableList<CompanyItem> companyItems;
    private ObservableList<ApplicationItem> applicationItems;
    private ObservableList<ProfileItem> profileItems;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private Tabs tabs;
    @FXML
    private VBox cardList;
    @FXML
    private StackPane listPanelPlaceholder;
    @FXML
    private VBox display;
    @FXML
    private ScrollPane resultDisplayPlaceholder;
    @FXML
    private VBox commandBoxPlaceholder;
    @FXML
    private VBox tabsContainer;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        // Configure the UI
        initializeUi(primaryStage, logic);

        // linking to logic
        companyItems = logic.getFilteredCompanyItemList();
        applicationItems = logic.getFilteredApplicationItemList();
        profileItems = logic.getFilteredProfileItemList();
    }

    /**
     * todo Javadocs
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets up the GUI properties in the {@code primaryStage} using the stored user settings in {@code logic}.
     */
    private void initializeUi(Stage primaryStage, Logic logic) {
        setWindowDefaultSize(logic.getGuiSettings());
        bindHeights(primaryStage);
        helpWindow = new HelpWindow();

        primaryStage.setOnCloseRequest(event -> {
            GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                    (int) primaryStage.getX(), (int) primaryStage.getY());
            logic.setGuiSettings(guiSettings);

            ExitDialog exitDialog = new ExitDialog(event, helpWindow);
            exitDialog.show();
        });
    }

    /**
     * Binds the height of {@code personList} and {@code resultDisplayPlaceHolder} in the {@code primaryStage}
     */
    private void bindHeights(Stage primaryStage) {
        cardList.prefWidthProperty().bind(primaryStage.widthProperty().subtract(PERSON_LIST_HEIGHT_SHRINK));
        resultDisplayPlaceholder.prefWidthProperty().bind(primaryStage.widthProperty().subtract(RESULT_HEIGHT_SHRINK));
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        addResultDisplay();
        addListPanel();
        addCommandBox();
        addInformationDisplay();
        addTabs();
    }

    /**
     * todo javadocs
     */
    void addTabs() {
        tabs = Tabs.getTabs(this, logic);
        tabsContainer.getChildren().add(tabs);
    }

    /**
     * todo javadocs
     */
    void addResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.setContent(resultDisplay.getRoot());
    }

    /**
     * todo javadocs
     */
    void addInformationDisplay() {
        changeDisplay();
    }

    /**
     * todo javadocs
     */
    void addCommandBox() {
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * todo javadocs
     */
    void addListPanel() {
        changeTabView(COMPANY);
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Displays the GUI.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Switch the tabs of the application.
     */
    private void switchTab() {
        tabs.switchTab();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }


            if (commandResult.isSwitchTab()) {
                switchTab();
            }

            if (commandResult.isSwitchDisplay()) {
                changeDisplay();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Changes the display of screen, depending on {@code input}, in the {@code primaryStage}.
     */
    public void changeTabView(TabName tabName) {
        assert (tabName.equals(APPLICATION) || tabName.equals(COMPANY) || tabName.equals(PROFILE));
        listPanelPlaceholder.getChildren().clear();
        Optional<ListPanel<? extends Item>> newListPanel = Optional.empty();
        switch (tabName) {
        case COMPANY:
            newListPanel = getCompanyTabView();
            break;
        case APPLICATION:
            newListPanel = setApplicationTabView();
            break;
        case PROFILE:
            newListPanel = setProfileTabView();
            break;
        default:
            assert false;
            break;
        }
        changeDisplay();
        if (!isEmptyListPanel.test(newListPanel)) {
            listPanelPlaceholder.getChildren().add(newListPanel.get().getRoot());
        }
    }

    /**
     * todo javadocs
     */
    private Optional<ListPanel<? extends Item>> getCompanyTabView() {
        return Optional.of(new CompanyListPanel(companyItems));
    }

    /**
     * todo javadocs
     */
    private Optional<ListPanel<? extends Item>> setApplicationTabView() {
        return Optional.of(new ApplicationListPanel(applicationItems));
    }

    /**
     * todo javadocs
     */
    private Optional<ListPanel<? extends Item>> setProfileTabView() {
        return Optional.of(new ProfileListPanel(profileItems));
    }

    /**
     * todo javadocs
     */
    public void changeDisplay() {
        TabName tabName = logic.getTabName();
        int index = logic.getViewIndex().getZeroBased();
        Optional<InformationDisplay<? extends Item>> newInformationDisplay = Optional.empty();
        switch (tabName) {
        case COMPANY:
            newInformationDisplay = getCompanyDisplay(index);
            break;
        case APPLICATION:
            newInformationDisplay = getApplicationDisplay(index);
            break;
        case PROFILE:
            newInformationDisplay = getProfileDisplay(index);
            break;
        default:
            assert false;
            break;
        }
        display.getChildren().clear();
        if (!isEmptyDisplay.test(newInformationDisplay)) {
            display.getChildren().add(newInformationDisplay.get().getRoot());
        }
    }

    /**
     * todo javadocs
     */
    private Optional<InformationDisplay<? extends Item>> getCompanyDisplay(int index) {
        if (!isEmptyDataList.test(companyItems)) {
            return Optional.of(CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(index)));
        }
        return Optional.empty();
    }

    /**
     * todo javadocs
     */
    private Optional<InformationDisplay<? extends Item>> getApplicationDisplay(int index) {
        if (!isEmptyDataList.test(applicationItems)) {
            return Optional.of(ApplicationDisplay.getApplicationDisplay(primaryStage, applicationItems.get(index)));
        }
        return Optional.empty();
    }

    /**
     * todo javadocs
     */
    private Optional<InformationDisplay<? extends Item>> getProfileDisplay(int index) {
        if (!isEmptyDataList.test(profileItems)) {
            return Optional.of(ProfileDisplay.getProfileDisplay(primaryStage, profileItems.get(index)));
        }
        return Optional.empty();
    }
}
