package seedu.address.ui.display;

import static seedu.address.ui.display.DisplayKeyList.PROFILE_DISPLAY_KEY_LIST;
import static seedu.address.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;

import java.util.function.Function;
import java.util.function.Predicate;

import javafx.stage.Stage;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.tabs.TabName;

/**
 * A UI component that displays all the information of a {@code ProfileItem}.
 */
public class ProfileDisplay extends InformationDisplay<ProfileItem> {

    /**
     * A function that removes the bracket of the string and indent each attribute.
     */
    private final Function<String, String> editString = string
        -> string.substring(1, string.length() - 1).replaceAll(", ", "\n");

    /**
     * A predicate that checks if the current key is a descriptor.
     */
    private final Predicate<String> isDescriptors = key -> key.equals(DESCRIPTORS_DISPLAY_NAME);

    /**
     * A function that formats the information of the profile with bullet points.
     */
    private final Function<String, String> formatProfileDetail = string -> {
        string = BULLET_WITH_ONE_SPACE + string;
        return string.replaceAll("\n", "\n" + BULLET_WITH_ONE_SPACE);
    };

    /**
     * Creates a {@code ProfileDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param profileItem The profile item to be displayed.
     */
    private ProfileDisplay(Stage primaryStage, ProfileItem profileItem) {
        super(primaryStage, profileItem);

        initializeProfileDisplayGui();
    }

    /**
     * Creates a {@code ProfileDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param profileItem The profile item to be displayed.
     * @return A Profile Display with information of the {@code profileItem} in {@code primaryStage}.
     */
    public static ProfileDisplay getProfileDisplay(Stage primaryStage, ProfileItem profileItem) {
        return new ProfileDisplay(primaryStage, profileItem);
    }

    /**
     * Sets the title and information of the {@code profileItem} for display.
     */
    private void initializeProfileDisplayGui() {
        setDisplayName();
        setProfileInformation();
    }

    /**
     * Sets the title of the {@code profileItem} for display.
     */
    private void setDisplayName() {
        Object jobTitle = mapping.get(TITLE_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * Sets the information of the {@code profileItem} for display.
     */
    private void setProfileInformation() {
        setInformation(editString, formatProfileDetail, isDescriptors, TabName.PROFILE, PROFILE_DISPLAY_KEY_LIST);
    }

}
