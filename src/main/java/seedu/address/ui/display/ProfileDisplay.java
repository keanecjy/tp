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
 * A display containing information about the chosen profile's item.
 */
public class ProfileDisplay extends InformationDisplay<ProfileItem> {

    /**
     * Constructs a {@code ProfileDisplay} in the given {@code primaryStage}.
     */
    private ProfileDisplay(Stage primaryStage, ProfileItem profileItem) {
        super(primaryStage, profileItem);

        initializeProfileDisplayGui();
    }

    /**
     * Creates the {@code ProfileDisplay} information in the {@code primaryStage}.
     */
    public static ProfileDisplay getProfileDisplay(Stage primaryStage, ProfileItem profileItem) {
        return new ProfileDisplay(primaryStage, profileItem);
    }

    /**
     * todo Javadocs
     */
    private void initializeProfileDisplayGui() {
        setDisplayName();
        setProfileInformation();
    }

    /**
     * todo Javadocs
     */
    private void setDisplayName() {
        Object jobTitle = mapping.get(TITLE_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * todo javadocs
     */
    private void setProfileInformation() {
        Predicate<String> isDescriptors = key -> key.equals(DESCRIPTORS_DISPLAY_NAME);
        Function<String, String> editString = string -> string.substring(1, string.length() - 1).replaceAll(", ", "\n");
        setInformation(editString, isDescriptors, TabName.PROFILE, PROFILE_DISPLAY_KEY_LIST);
    }
}
