package seedu.address.model.profile;

/**
 * UserProfileCategory class contains the valid category for user profile.
 */
public enum ProfileItemCategory {
    EXPERIENCE,
    ACHIEVEMENT,
    SKILL;


    public static final String MESSAGE_CONSTRAINTS =
            "ProfileItem category c/ can only be experience, achievement or skill";

    /**
     * Returns true if the profile item category is valid.
     *
     * @param profileItemCategory Input category as lower case.
     * @return True if status is valid, false otherwise.
     */
    public static boolean isValidProfileItemCategory(String profileItemCategory) {
        return profileItemCategory.equals(EXPERIENCE.toString())
                || profileItemCategory.equals(ACHIEVEMENT.toString())
                || profileItemCategory.equals(SKILL.toString());
    }
}
