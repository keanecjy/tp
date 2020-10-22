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
 * A UI component that displays all the information of a {@code ApplicationItem}.
 */
public class ApplicationDisplay extends InformationDisplay<ApplicationItem> {

    /**
     * A predicate that checks whether the current key is requirements.
     */
    private final Predicate<String> isRequirements = key -> key.equals(REQUIREMENTS_DISPLAY_NAME);

    /**
     * A function that returns back the same string. Mainly for polymorphism usage.
     */
    private final Function<String, String> returnSameString = string -> string;

    /**
     * Creates a {@code ApplicationDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param applicationItem The application item to be displayed.
     */
    private ApplicationDisplay(Stage primaryStage, ApplicationItem applicationItem) {
        super(primaryStage, applicationItem);

        initializeApplicationDisplayGui();
    }

    /**
     * Creates a {@code ApplicationDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param applicationItem The application item to be displayed.
     * @return An Application Display with information of the {@code applicationItem} in {@code primaryStage}.
     */
    public static ApplicationDisplay getApplicationDisplay(Stage primaryStage, ApplicationItem applicationItem) {
        return new ApplicationDisplay(primaryStage, applicationItem);
    }

    /**
     * Sets the title and information of the {@code applicationItem} for display.
     */
    private void initializeApplicationDisplayGui() {
        setJobTitle();
        setApplicationInformation();
    }

    /**
     * Sets the title of the {@code applicationItem} for display.
     */
    private void setJobTitle() {
        Object jobTitle = mapping.get(JOB_TITLE_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * Sets the information of the {@code applicationItem} for display.
     */
    private void setApplicationInformation() {
        setInformation(editString, returnSameString, isRequirements, TabName.APPLICATION, APPLICATION_DISPLAY_KEY_LIST);
    }

}
