package seedu.internhunter.model.profile;

import seedu.internhunter.model.wrapper.NonEmptyString;

/**
 * Represents a title of a field in a {@code ProfileItem}
 * Guarantees: immutable; is valid as declared in {@link #isValidNonEmptyString(String)}
 */
public class Title extends NonEmptyString {

    public static final String MESSAGE_CONSTRAINTS = "Profile Item Titles should not be blank";

    /**
     * Constructs a {@code Title} for the {@code ProfileItem}
     *
     * @param title A valid title for the profile item.
     */
    public Title(String title) {
        super(title, MESSAGE_CONSTRAINTS);
    }

}
