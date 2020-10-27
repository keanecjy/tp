package seedu.internhunter.ui;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_DATA_LIST;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_DISPLAY;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_LIST_PANEL;
import static seedu.internhunter.ui.tabs.TabName.APPLICATION;
import static seedu.internhunter.ui.tabs.TabName.COMPANY;
import static seedu.internhunter.ui.tabs.TabName.PROFILE;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.logic.Logic;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.display.ApplicationDisplay;
import seedu.internhunter.ui.display.CompanyDisplay;
import seedu.internhunter.ui.display.InformationDisplay;
import seedu.internhunter.ui.display.ProfileDisplay;
import seedu.internhunter.ui.panel.ApplicationListPanel;
import seedu.internhunter.ui.panel.CompanyListPanel;
import seedu.internhunter.ui.panel.ListPanel;
import seedu.internhunter.ui.panel.ProfileListPanel;
import seedu.internhunter.ui.popupwindow.HelpWindow;
import seedu.internhunter.ui.popupwindow.InternshipsWindow;
import seedu.internhunter.ui.popupwindow.PopupWindow;
import seedu.internhunter.ui.tabs.TabName;
import seedu.internhunter.ui.tabs.Tabs;

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
    private InternshipsWindow internshipsWindow;
    private Tabs tabs;
    private ListPanel<? extends Item> listPanel;
    private InformationDisplay<? extends Item> informationDisplay;
    private CommandBox commandBox;

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
     * @param primaryStage The main stage of the app.
     * @param logic The logic unit of the app.
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
     * Retrieves the primary stage.
     *
     * @return the {@code primaryStage} of the main window.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets up the GUI properties in the {@code primaryStage} using the stored user settings in {@code logic}.
     *
     * @param primaryStage The main stage of the app.
     * @param logic The logic unit of the app.
     */
    private void initializeUi(Stage primaryStage, Logic logic) {
        setWindowDefaultSize(logic.getGuiSettings());
        bindHeights(primaryStage);
        helpWindow = new HelpWindow();
        internshipsWindow = new InternshipsWindow();
        primaryStage.setOnCloseRequest(event -> {
            GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                    (int) primaryStage.getX(), (int) primaryStage.getY());
            logic.setGuiSettings(guiSettings);

            ExitDialog exitDialog = new ExitDialog(event, helpWindow, internshipsWindow);
            exitDialog.show();
        });
    }

    /**
     * Binds the height of {@code personList} and {@code resultDisplayPlaceHolder} in the {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     */
    private void bindHeights(Stage primaryStage) {
        cardList.prefWidthProperty().bind(primaryStage.widthProperty().subtract(PERSON_LIST_HEIGHT_SHRINK));
        resultDisplayPlaceholder.prefWidthProperty().bind(primaryStage.widthProperty().subtract(RESULT_HEIGHT_SHRINK));
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     *
     * @param guiSettings The stored GUI settings of the app.
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
     * Adds the tab display to the {@code MainWindow}.
     */
    void addTabs() {
        tabs = Tabs.getTabs(this);
        tabsContainer.getChildren().add(tabs.getRoot());
    }

    /**
     * Adds the result display to the {@code MainWindow}.
     */
    void addResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.setContent(resultDisplay.getRoot());
    }

    /**
     * Adds the information display to the {@code MainWindow}.
     */
    void addInformationDisplay() {
        display.getChildren().clear();
        if (!IS_EMPTY_DATA_LIST.test(companyItems)) {
            informationDisplay = CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(0));
            display.getChildren().add(informationDisplay.getRoot());
        }
    }

    /**
     * Adds the command box display to the {@code MainWindow}.
     */
    void addCommandBox() {
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Adds the list panel display to the {@code MainWindow}.
     */
    void addListPanel() {
        listPanelPlaceholder.getChildren().clear();
        listPanel = new CompanyListPanel(companyItems);
        listPanelPlaceholder.getChildren().add(listPanel.getRoot());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        handlePopupWindow(helpWindow);
    }

    /**
     * Opens the internships window or focuses on it if it's already opened.
     */
    @FXML
    private void handleMatchingInternships(ObservableList<InternshipItem> internshipList) {
        internshipsWindow.setInternshipList(internshipList);
        handlePopupWindow(internshipsWindow);
    }

    /**
     * Opens the popup window or focuses on it if it's already opened.
     *
     * @param popupWindow Popup window.
     */
    private void handlePopupWindow(PopupWindow popupWindow) {
        if (!popupWindow.isShowing()) {
            popupWindow.show();
        } else {
            popupWindow.focus();
        }
    }

    /**
     * Displays the GUI.
     */
    public void show() {
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
     * Switch the tabs of the application to {@code tabName}.
     *
     * @param tabName The tab to be switched to.
     */
    private void switchTab(TabName tabName) {
        requireNonNull(tabName);
        tabs.switchTab(tabName);
    }

    /**
     * Executes the command and returns the result.
     *
     * @param commandText The text that the user input.
     * @return A command result which contains the result of executing the text input.
     * @throws CommandException thrown when there is an invalid command inputted.
     * @throws ParseException thrown when there is an invalid text to be parsed.
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowMatchingInternships()) {
                handleMatchingInternships(commandResult.getMatchingInternships());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isSwitchTab()) {
                switchTab(logic.getTabName());
                changeListPanelView(logic.getTabName());
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
     * Changes the display of screen, depending on {@code tabName}, in the {@code primaryStage}.
     *
     * @param tabName The tab to be switched to.
     */
    public void changeListPanelView(TabName tabName) {
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
        if (!IS_EMPTY_LIST_PANEL.test(newListPanel)) {
            listPanelPlaceholder.getChildren().add(newListPanel.get().getRoot());
        }
    }

    /**
     * Retrieves the list of cards that displays a list of company information.
     *
     * @return An Optional value containing the company list panel.
     */
    private Optional<ListPanel<? extends Item>> getCompanyTabView() {
        if (IS_EMPTY_DATA_LIST.test(companyItems)) {
            return Optional.empty();
        }
        return Optional.of(new CompanyListPanel(companyItems));
    }

    /**
     * Retrieves the list of cards that displays a list of application information.
     *
     * @return An Optional value containing the application list panel.
     */
    private Optional<ListPanel<? extends Item>> setApplicationTabView() {
        if (IS_EMPTY_DATA_LIST.test(applicationItems)) {
            return Optional.empty();
        }
        return Optional.of(new ApplicationListPanel(applicationItems));
    }

    /**
     * Retrieves the list of cards that displays a list of profile information.
     *
     * @return An Optional value containing the profile list panel.
     */
    private Optional<ListPanel<? extends Item>> setProfileTabView() {
        if (IS_EMPTY_DATA_LIST.test(profileItems)) {
            return Optional.empty();
        }
        return Optional.of(new ProfileListPanel(profileItems));
    }

    /**
     * Switches the display based on the {@code index} and {@code tabName}.
     */
    public void changeDisplay() {
        TabName tabName = logic.getTabName();
        int index;
        Optional<InformationDisplay<? extends Item>> newInformationDisplay = Optional.empty();
        switch (tabName) {
        case COMPANY:
            index = logic.getCompanyViewIndex().getZeroBased();
            newInformationDisplay = getCompanyDisplay(index);
            break;
        case APPLICATION:
            index = logic.getApplicationViewIndex().getZeroBased();
            newInformationDisplay = getApplicationDisplay(index);
            break;
        case PROFILE:
            index = logic.getProfileViewIndex().getZeroBased();
            newInformationDisplay = getProfileDisplay(index);
            break;
        default:
            assert false;
            break;
        }
        display.getChildren().clear();
        if (!IS_EMPTY_DISPLAY.test(newInformationDisplay)) {
            display.getChildren().add(newInformationDisplay.get().getRoot());
        }
    }

    /**
     * Retrieves the particular company item's information at that index.
     *
     * @param index The Index of the display to be displayed.
     * @return An Optional containing the display information of the company at that particular Index.
     */
    private Optional<InformationDisplay<? extends Item>> getCompanyDisplay(int index) {
        if (IS_EMPTY_DATA_LIST.test(companyItems)) {
            return Optional.empty();
        }
        return Optional.of(CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(index)));
    }

    /**
     * Retrieves the particular application item's information at that index.
     *
     * @param index The Index of the display to be displayed.
     * @return An Optional containing the display information of the Application at that particular Index.
     */
    private Optional<InformationDisplay<? extends Item>> getApplicationDisplay(int index) {
        if (IS_EMPTY_DATA_LIST.test(applicationItems)) {
            return Optional.empty();
        }
        return Optional.of(ApplicationDisplay.getApplicationDisplay(primaryStage, applicationItems.get(index)));
    }

    /**
     * Retrieves the particular profile item's information at that index.
     *
     * @param index The Index of the display to be displayed.
     * @return An Optional containing the display information of the profile at that particular Index.
     */
    private Optional<InformationDisplay<? extends Item>> getProfileDisplay(int index) {
        if (IS_EMPTY_DATA_LIST.test(profileItems)) {
            return Optional.empty();
        }
        return Optional.of(ProfileDisplay.getProfileDisplay(primaryStage, profileItems.get(index)));
    }
}