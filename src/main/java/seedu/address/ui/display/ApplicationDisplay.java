package seedu.address.ui.display;

import static seedu.address.ui.display.DisplayKeyList.APPLICATION_DISPLAY_KEY_LIST;
import static seedu.address.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;

import java.util.function.Function;
import java.util.function.Predicate;

import javafx.stage.Stage;
import seedu.address.model.application.ApplicationItem;
import seedu.address.ui.tabs.TabName;

/**
 * A display containing information about the chosen application.
 */
public class ApplicationDisplay extends InformationDisplay<ApplicationItem> {

    /**
     * Constructs a {@code ApplicationDisplay} in the given {@code primaryStage}.
     */
    private ApplicationDisplay(Stage primaryStage, ApplicationItem applicationItem) {
        super(primaryStage, applicationItem);

        initializeApplicationDisplayGui();
    }

    /**
     * Creates the {@code ApplicationDisplay} information in the {@code primaryStage}.
     */
    public static ApplicationDisplay getApplicationDisplay(Stage primaryStage, ApplicationItem applicationItem) {
        return new ApplicationDisplay(primaryStage, applicationItem);
    }

    /**
     * todo Javadocs
     */
    private void initializeApplicationDisplayGui() {
        setInformationTitle();
        setInformation();
    }

    /**
     * todo Javadocs
     */
    private void setInformationTitle() {
        Object jobTitle = mapping.get(JOB_TITLE_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * todo Javadocs
     */
    private void setInformation() {
        Predicate<String> isRequirements = key -> key.equals(REQUIREMENTS_DISPLAY_NAME);
        Function<String, String> editString = string -> string.substring(1, string.length() - 1);
        setInformation(editString, isRequirements, TabName.APPLICATION, APPLICATION_DISPLAY_KEY_LIST);
    }

}
