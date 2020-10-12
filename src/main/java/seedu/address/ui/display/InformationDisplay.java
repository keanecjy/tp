package seedu.address.ui.display;

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;
import seedu.address.ui.tabs.TabName;

/**
 * A display that contains information.
 */
public abstract class InformationDisplay<T extends Item> extends UiPart<Region> {

    //FXML
    private static final String FXML = "InformationDisplay.fxml";

    //FXML properties
    private static final int INFORMATION_HEIGHT_SHRINK = 175;
    private static final int INFORMATION_WIDTH_SHRINK = 100;
    protected T item;
    protected LinkedHashMap<String, Object> mapping;

    @FXML
    private ScrollPane informationDisplay;
    @FXML
    private HBox nameBar;
    @FXML
    private Label informationTitle;
    @FXML
    private VBox information;

    /**
     * Constructs a {@code InformationDisplay} in the given {@code primaryStage}.
     */
    public InformationDisplay(Stage primaryStage, T item) {
        super(FXML);
        initializeInformationDisplay(primaryStage);
        this.item = item;
        this.mapping = item.getMapping();
    }

    /**
     * Sets the size of {@code InformationDisplay} in the given {@code primaryStage}.
     */
    private void initializeInformationDisplay(Stage primaryStage) {
        informationDisplay.prefHeightProperty().bind(primaryStage.heightProperty().subtract(INFORMATION_HEIGHT_SHRINK));
        nameBar.maxWidthProperty().bind(informationDisplay.widthProperty().subtract(INFORMATION_WIDTH_SHRINK));
    }

    /**
     * Adds {@code titleDescriptionDisplay} into {@code InformationDisplay}.
     */
    void addInformation(TitleDescriptionDisplay titleDescriptionDisplay) {
        information.getChildren().addAll(titleDescriptionDisplay);
    }

    /**
     * Adds {@code title} into {@code InformationDisplay}.
     */
    protected void setInformationTitle(String title) {
        informationTitle.setText(title);
    }

    /**
     * todo javadocs
     */
    protected void setInformation(Function<String, String> editString, Predicate<String> haveBrackets,
        TabName tabName, String ... displayKeyList) {

        for (String key : displayKeyList) {
            Object object = mapping.get(key);
            String detail = object.toString();
            if (haveBrackets.test(key)) {
                detail = editString.apply(detail);
            }
            addInformation(TitleDescriptionDisplay.addTitleDescriptionDisplay(key, detail, tabName));
        }
    }
}
