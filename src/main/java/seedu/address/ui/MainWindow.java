package seedu.address.ui;

import static seedu.address.ui.tabs.TabName.APPLICATION;
import static seedu.address.ui.tabs.TabName.COMPANY;
import static seedu.address.ui.tabs.TabName.PROFILE;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
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

    // Independent Ui parts residing in this Ui container
    private ListPanel listPanel;
    private InformationDisplay informationDisplay;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private Tabs tabs;

    private ObservableList<CompanyItem> companyItems;
    private ObservableList<ApplicationItem> applicationItems;
    private ObservableList<ProfileItem> profileItems;

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
        listPanel = new CompanyListPanel(companyItems);
        listPanelPlaceholder.getChildren().add((Node) listPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.setContent(resultDisplay.getRoot());

        if (companyItems.size() > 0) {
            informationDisplay = CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(0));
            display.getChildren().add((Node) informationDisplay.getRoot());
        }

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        tabs = Tabs.getTabs(this, primaryStage, logic);
        tabsContainer.getChildren().add(tabs);
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
    public void changeTabView(TabName tabName, Stage primaryStage) {
        assert (tabName.equals(APPLICATION) || tabName.equals(COMPANY) || tabName.equals(PROFILE));
        display.getChildren().clear();
        listPanelPlaceholder.getChildren().clear();
        switch (tabName) {
        case COMPANY:
            if (companyItems.size() > 0) {
                informationDisplay = CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(0));
                display.getChildren().add((Node) informationDisplay.getRoot());
            }
            listPanel = new CompanyListPanel(companyItems);
            break;
        case APPLICATION:
            if (applicationItems.size() > 0) {
                informationDisplay = ApplicationDisplay.getApplicationDisplay(primaryStage, applicationItems.get(0));
                display.getChildren().add((Node) informationDisplay.getRoot());
            }
            listPanel = new ApplicationListPanel(applicationItems);
            break;
        case PROFILE:
            if (profileItems.size() > 0) {
                informationDisplay = ProfileDisplay.getProfileDisplay(primaryStage, profileItems.get(0));
                display.getChildren().add((Node) informationDisplay.getRoot());
            }
            listPanel = new ProfileListPanel(profileItems);
            break;
        default:
            assert false;
            break;
        }
        logic.setViewIndex(Index.fromZeroBased(0));
        listPanelPlaceholder.getChildren().add((Node) listPanel.getRoot());
    }

    /**
     * todo javadocs
     */
    public void changeDisplay() {
        TabName tabName = logic.getTabName();
        int index = logic.getViewIndex().getZeroBased();
        switch (tabName) {
        case COMPANY:
            if (companyItems.size() > 0) {
                informationDisplay = CompanyDisplay.getCompanyDisplay(primaryStage, companyItems.get(index));
            }
            break;
        case APPLICATION:
            if (applicationItems.size() > 0) {
                informationDisplay = ApplicationDisplay.getApplicationDisplay(primaryStage,
                    applicationItems.get(index));
            }
            break;
        case PROFILE:
            if (profileItems.size() > 0) {
                informationDisplay = ProfileDisplay.getProfileDisplay(primaryStage, profileItems.get(index));
            }
            break;
        default:
            assert false;
            break;
        }
        display.getChildren().clear();
        display.getChildren().add((Node) informationDisplay.getRoot());
    }
}
