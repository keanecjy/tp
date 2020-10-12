package seedu.address.ui.tabs;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.ui.MainWindow;

/**
 * Tabs which allow switching of screens for different information.
 */
public class Tabs extends VBox {

    //FXML
    private static final String FXML = "/view/Tabs.fxml";

    //FXML properties
    private static final String TRANSPARENT = "-fx-background-color: transparent;";
    private static final String TAB_COLOR = "-fx-background-color: #BED0F7;";
    private static final String APPLICATION_Y_TRANSLATE = "-fx-translate-y: -13.5";
    private static final String COMPANY_Y_TRANSLATE = "-fx-translate-y: 0";
    private static final String PROFILE_Y_TRANSLATE = "-fx-translate-y: -27";

    // Image links
    private static final String APPLICATION_IMAGE_LINK = "/images/application.png";
    private static final String COMPANY_IMAGE_LINK = "/images/company.png";
    private static final String PROFILE_IMAGE_LINK = "/images/profile.png";

    @FXML
    private Button applicationButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button profileButton;
    @FXML
    private VBox application;
    @FXML
    private VBox company;
    @FXML
    private VBox profile;
    @FXML
    private ImageView applicationIcon;
    @FXML
    private ImageView companyIcon;
    @FXML
    private ImageView profileIcon;

    private Stage stage;
    private MainWindow mainWindow;
    private Logic logic;

    /**
     * Constructs the {@code Tabs} in the given {@code primaryStage} of the {@code mainWindow}.
     */
    private Tabs(MainWindow mainWindow, Stage primaryStage, Logic logic) {
        try {
            stage = primaryStage;
            this.mainWindow = mainWindow;
            this.logic = logic;

            // Loading the information of the GUI
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            // initialize the GUI of the tabs.
            company.setStyle(TAB_COLOR);
            setTabIcons();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the {@code Tabs} information in the {@code primaryStage} of the {@code mainWindow}.
     */
    public static Tabs getTabs(MainWindow mainWindow, Stage primaryStage, Logic logic) {
        return new Tabs(mainWindow, primaryStage, logic);
    }

    /**
     * Handles the click {@code event} on the tabs.
     */
    @FXML
    public void handleClick(ActionEvent event) {
        if (event.getSource() == applicationButton) {
            selectApplication(stage);
            logic.setTabName(TabName.APPLICATION);
        } else if (event.getSource() == companyButton) {
            selectCompany(stage);
            logic.setTabName(TabName.COMPANY);
        } else if (event.getSource() == profileButton) {
            selectProfile(stage);
            logic.setTabName(TabName.PROFILE);
        } else {
            assert false : "Invalid button";
        }
    }

    /**
     * Switch tabs.
     */
    public void switchTab() {
        TabName tabName = logic.getTabName();
        switch (tabName) {
        case COMPANY:
            selectCompany(stage);
            break;
        case APPLICATION:
            selectApplication(stage);
            break;
        case PROFILE:
            selectProfile(stage);
            break;
        default:
            assert false;
        }
    }

    /**
     * Sets up the tab icons in GUI of {@code MainWindow}.
     */
    private void setTabIcons() {
        Image applicationIconImage = new Image(getClass().getResourceAsStream(APPLICATION_IMAGE_LINK));
        applicationIcon.setImage(applicationIconImage);

        Image companyIconPicture = new Image(getClass().getResourceAsStream(COMPANY_IMAGE_LINK));
        companyIcon.setImage(companyIconPicture);

        Image profileIconPicture = new Image(getClass().getResourceAsStream(PROFILE_IMAGE_LINK));
        profileIcon.setImage(profileIconPicture);
    }

    /**
     * Sets the display and tab to be of {@code application} in the {@code stage}
     * Currently it only switches the information display.
     */
    public void selectApplication(Stage stage) {
        // adjust tab bar position
        setColor(application, APPLICATION_Y_TRANSLATE);
        setTransparent(company, COMPANY_Y_TRANSLATE);
        setTransparent(profile, PROFILE_Y_TRANSLATE);

        // adjust the display of the gui
        mainWindow.changeTabView(TabName.APPLICATION, stage);
    }

    /**
     * Sets the display and tab to be of {@code company} in the {@code stage}
     * Currently it only switches the information display.
     */
    public void selectCompany(Stage stage) {
        // adjust tab bar position
        setTransparent(application, APPLICATION_Y_TRANSLATE);
        setColor(company, COMPANY_Y_TRANSLATE);
        setTransparent(profile, PROFILE_Y_TRANSLATE);

        // adjust the display of the gui
        mainWindow.changeTabView(TabName.COMPANY, stage);
    }

    /**
     * Sets the display and tab to be of {@code profile} in the {@code stage}
     * Currently it only switches the information display.
     */
    public void selectProfile(Stage stage) {
        // adjust tab bar position
        setTransparent(application, APPLICATION_Y_TRANSLATE);
        setTransparent(company, COMPANY_Y_TRANSLATE);
        setColor(profile, PROFILE_Y_TRANSLATE);

        // adjust the display of the gui
        mainWindow.changeTabView(TabName.PROFILE, stage);
    }

    /**
     * Sets the color of the {@code scene} to be transparent.
     */
    private <T extends Pane> void setTransparent(T scene, String distance) {
        scene.setStyle(TRANSPARENT + distance);
    }

    /**
     * Sets the color of the {@code scene} to be {@code TAB_COLOR}.
     */
    private <T extends Pane> void setColor(T scene, String distance) {
        scene.setStyle(TAB_COLOR + distance);
    }
}
