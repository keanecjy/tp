package seedu.address.ui.textstyle;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitleDescription {

    private static final String DISPLAY_FONT = "Nunito";
    private static final int DISPLAY_SIZE = 12;
    private static final String TITLE_COLOR = "#363F80";
    private static final String DESCRIPTION_COLOR = "#8E8FB5";

    private Text title;
    private Text description;

    /**
     * Constructs a {@code TitleDescription} containing a title and a description.
     */
    private TitleDescription(Text title, Text description) {
        this.title = title;
        this.description = description;
    }

    public Text getTitle() {
        return title;
    }

    public Text getDescription() {
        return description;
    }

    /**
     * Constructs a {@code TitleDescription} that contains both the styled title and the styled description.
     */
    public static TitleDescription createTitleDescription(String displayTitle, String displayDescription) {
        Text styledTitle = new Text(displayTitle);
        Text styledDescription = new Text(displayDescription);
        setTextStyle(styledTitle, Color.web(TITLE_COLOR));
        setTextStyle(styledDescription, Color.web(DESCRIPTION_COLOR));
        return new TitleDescription(styledTitle, styledDescription);
    }

    /**
     * Sets a text with a pre-defined font and its {@code color}.
     */
    private static void setTextStyle(Text text, Color color) {
        text.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, DISPLAY_SIZE));
        text.setFill(color);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TitleDescription)) {
            return false;
        }

        TitleDescription other = (TitleDescription) obj;
        return title.toString().equals(other.title.toString())
                && description.toString().equals(other.description.toString());
    }
}
