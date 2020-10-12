package seedu.address.ui.display;

import static seedu.address.ui.textstyle.TitleDescription.createTitleDescription;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import seedu.address.ui.MainWindow;
import seedu.address.ui.tabs.TabName;
import seedu.address.ui.textstyle.TitleDescription;

/**
 * A display containing a title and a description.
 */
public class TitleDescriptionDisplay extends HBox {

    //FXML
    private static final String FXML = "/view/TitleDescriptionDisplay.fxml";

    //FXML properties
    private static final String PROFILE_INFORMATION_WIDTH = "-fx-min-width: 95";
    private static final String COMPANY_AND_APPLICATION_INFORMATION_WIDTH = "-fx-min-width: 105";

    @FXML
    private TextFlow title;
    @FXML
    private TextFlow description;
    @FXML
    private VBox titleInformation;
    @FXML
    private VBox descriptionInformation;

    /**
     * Constructs a {@code TitleDescriptionDisplay} with {@code displayTitle}, {@code displayDescription} for
     * {@code tab} display.
     */
    private TitleDescriptionDisplay(String displayTitle, String displayDescription, TabName tab) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addDisplayInformation(displayTitle, displayDescription);
        setWidth(tab);
    }

    /**
     * Accessor to add a {@code TitleDescriptionDisplay} with {@code displayTitle}, {@code displayDescription} for
     * {@code tab} display.
     */
    public static TitleDescriptionDisplay addTitleDescriptionDisplay(String displayTitle, String displayDescription,
        TabName tab) {

        return new TitleDescriptionDisplay(displayTitle, displayDescription, tab);
    }

    /**
     * Adds a title and description into the {@code TitleDescriptionDisplay}.
     */
    private void addDisplayInformation(String displayTitle, String displayDescription) {
        TitleDescription titleDescription = createTitleDescription(displayTitle, displayDescription);
        title.getChildren().addAll(titleDescription.getTitle());
        description.getChildren().addAll(titleDescription.getDescription());
    }

    /**
     * Adjust the width of the title based on which {@code tab} display.
     */
    private void setWidth(TabName tab) {
        switch (tab) {
        case COMPANY:
            //Fallthrough
        case APPLICATION:
            titleInformation.setStyle(COMPANY_AND_APPLICATION_INFORMATION_WIDTH);
            break;
        case PROFILE:
            titleInformation.setStyle(PROFILE_INFORMATION_WIDTH);
            break;
        default:
            assert false;
        }
    }
}
