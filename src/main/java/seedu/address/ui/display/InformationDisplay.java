package seedu.address.ui.display;

import static seedu.address.ui.GuardClauseUi.IS_EMPTY_STRING;
import static seedu.address.ui.GuardClauseUi.IS_SAME_STRING;
import static seedu.address.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;

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

    private static final String NEW_LINE = "\n";
    private static final String COMMA_ONE_SPACE = ", ";
    private static final String COMMA_TWO_SPACE = ", {2}";
    private static final String BULLET_WITH_TWO_SPACE_FRONT_ONE_BACK = "  \u2022 ";
    private static final String BULLET_WITH_ONE_SPACE = "\u2022 ";
    private static final String DOT_SPACE = ". ";
    private static final String DASH = "-";

    //FXML
    private static final String FXML = "InformationDisplay.fxml";

    //FXML properties
    private static final int INFORMATION_HEIGHT_SHRINK = 175;
    private static final int INFORMATION_WIDTH_SHRINK = 100;
    protected T item;
    protected LinkedHashMap<String, Object> mapping;

    @FXML
    private ScrollPane scrollableInformationDisplay;
    @FXML
    private HBox nameBar;
    @FXML
    private Label informationTitle;
    @FXML
    private VBox information;
    @FXML
    private VBox informationDisplay;
    @FXML
    private VBox informationDisplayContainer;

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
        scrollableInformationDisplay.prefHeightProperty()
            .bind(primaryStage.heightProperty().subtract(INFORMATION_HEIGHT_SHRINK));
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
            if (IS_SAME_STRING.test(key, INTERNSHIPS_DISPLAY_NAME) && !IS_EMPTY_STRING.test(detail)) {
                detail = formatInternshipDetail(detail);
            }
            if (IS_SAME_STRING.test(key, DESCRIPTORS_DISPLAY_NAME) && !IS_EMPTY_STRING.test(detail)) {
                detail = formatProfileDetail(detail);
            }
            if (IS_EMPTY_STRING.test(detail)) {
                detail = DASH;
            }
            addInformation(TitleDescriptionDisplay.addTitleDescriptionDisplay(key, detail, tabName));
        }
    }

    /**
     * todo javadocs
     */
    public String formatProfileDetail(String string) {
        string = BULLET_WITH_ONE_SPACE + string;
        return string.replaceAll(NEW_LINE, NEW_LINE + BULLET_WITH_ONE_SPACE);
    }

    /**
     * todo javadocs
     */
    public String formatInternshipDetail(String string) {
        String s = string.substring(0, string.length() - 1);
        s = s.replaceAll(NEW_LINE + COMMA_ONE_SPACE, NEW_LINE);
        StringBuffer buffer = new StringBuffer(s);
        buffer.insert(0, 1 + DOT_SPACE);
        formatNumbering(buffer, NEW_LINE);
        return formatBulletPoints(buffer);
    }

    /**
     * todo javadocs
     */
    public void formatNumbering(StringBuffer buffer, String target) {
        int index = buffer.indexOf(target);
        int counter = 2;
        while (index != -1) {
            String replacement = target + counter + DOT_SPACE;
            buffer.replace(index, index + 1, replacement);
            index += replacement.length();
            index = buffer.indexOf(target, index);
            counter++;
        }
    }

    /**
     * todo javadocs
     */
    public String formatBulletPoints(StringBuffer buffer) {
        String string = buffer.toString();
        string = string.replaceAll(COMMA_TWO_SPACE, NEW_LINE + BULLET_WITH_TWO_SPACE_FRONT_ONE_BACK);
        return string;
    }
}
