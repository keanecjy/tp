package seedu.address.ui.cards;

import static seedu.address.ui.textstyle.TitleDescription.createTitleDescription;

import java.util.LinkedHashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;
import seedu.address.ui.textstyle.TitleDescription;

/**
 * A UI component that displays some information of a {@code Item}.
 */
public abstract class Card<T extends Item> extends UiPart<Region> {

    //FXML
    private static final String FXML = "Card.fxml";

    protected T item;
    protected int displayedIndex;
    protected LinkedHashMap<String, Object> mapping;

    @FXML
    protected VBox statusBox;
    @FXML
    protected Label status;
    @FXML
    protected ImageView imageView;
    @FXML
    protected Label date;
    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private TextFlow l1;
    @FXML
    private TextFlow l2;
    @FXML
    private TextFlow l3;

    /**
     * Creates a card with information regarding {@code item} with index of {@code displayedIndex}.
     *
     * @param item The item to be displayed.
     * @param displayedIndex The index of the item.
     */
    public Card(T item, int displayedIndex) {
        super(FXML);
        this.item = item;
        this.displayedIndex = displayedIndex;
        this.mapping = item.getMapping();
    }

    /**
     * @return The item in the card.
     */
    protected T getItem() {
        return item;
    }

    /**
     * Sets the name on the card.
     *
     * @param cardName The name of card.
     */
    protected void setName(String cardName) {
        name.setText(cardName);
    }

    /**
     * Sets the tags on the card.
     *
     * @param tagNames A string containing all the tag names.
     */
    protected void setTags(String tagNames) {
        String[] tagList = generateTags(tagNames);
        setAllTags(tagList);
    }

    /**
     * Formats the {@code tagNames} into a list of tag names.
     *
     * @param tagNames A string containing all the tag names.
     * @return An array that contains a list of tag names.
     */
    private String[] generateTags(String tagNames) {
        int length = tagNames.length();
        return tagNames.substring(1, length - 1).split(",");
    }

    /**
     * Sets each tag with a particular tag name.
     *
     * @param tagList A list of tag names.
     */
    private void setAllTags(String ... tagList) {
        for (String tag : tagList) {
            Label label = new Label(tag); // figure out how to text align
            tags.getChildren().add(label);
        }
    }

    /**
     * Sets the id of the card with {@code displayIndex}.
     *
     * @param displayedIndex The index of the card.
     */
    protected void setId(int displayedIndex) {
        id.setText(displayedIndex + ". ");
    }

    /**
     * Sets the information of the card with {@code title} and {@code description} in the position depending
     * on the {@code lineNumber}.
     *
     * @param title The title of the information.
     * @param description The description of the information.
     * @param lineNumber The linenumber to denote the position of the information.
     */
    protected void setTextAt(String title, String description, LineNumber lineNumber) {
        TitleDescription titleDescription = createTitleDescription(title + ": ", description);
        Text styledTitle = titleDescription.getTitle();
        Text styledDescription = titleDescription.getDescription();
        switch(lineNumber) {
        case L1:
            setLineText(l1, styledTitle, styledDescription);
            break;
        case L2:
            setLineText(l2, styledTitle, styledDescription);
            break;
        case L3:
            setLineText(l3, styledTitle, styledDescription);
            break;
        default:
            assert false;
            break;
        }
    }

    /**
     * Sets the {@code styledTitle} and {@code styledDescription} at {@code textFlow}.
     *
     * @param textFlow The text flow to be displayed
     * @param styledTitle A text containing the styled title.
     * @param styledDescription A text containing the styled description.
     * @param <K> The type of text flow.
     */
    private <K extends Pane> void setLineText(K textFlow, Text styledTitle, Text styledDescription) {
        textFlow.getChildren().addAll(styledTitle, styledDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Card)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        // state check
        Card<T> card = (Card<T>) other;
        return id.getText().equals(card.id.getText())
            && item.equals(card.item);
    }
}
