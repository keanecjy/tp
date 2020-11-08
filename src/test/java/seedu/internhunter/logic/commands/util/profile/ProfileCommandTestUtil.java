package seedu.internhunter.logic.commands.util.profile;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_SPACE_TITLE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT_INPUT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_EXPERIENCE_INPUT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_SKILL_INPUT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_IMPLEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_COMPETITION;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_HTML;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_INTERNSHIP;

import seedu.internhunter.commons.core.Messages;

public class ProfileCommandTestUtil {

    // Invalid company index
    public static final String INVALID_PROFILE_ITEM_INDEX_MESSAGE =
            String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME);

    // Valid parameters
    public static final String TITLE_DESC_HTML = " " + PREFIX_TITLE + VALID_TITLE_HTML;
    public static final String TITLE_DESC_INTERNSHIP = " " + PREFIX_TITLE + VALID_TITLE_INTERNSHIP;
    public static final String TITLE_DESC_COMPETITION = " " + PREFIX_TITLE + VALID_TITLE_COMPETITION;

    public static final String CATEGORY_DESC_SKILL = " " + PREFIX_CATEGORY + VALID_CATEGORY_SKILL_INPUT;
    public static final String CATEGORY_DESC_ACHIEVEMENT = " " + PREFIX_CATEGORY + VALID_CATEGORY_ACHIEVEMENT_INPUT;
    public static final String CATEGORY_DESC_EXPERIENCE = " " + PREFIX_CATEGORY + VALID_CATEGORY_EXPERIENCE_INPUT;

    public static final String DESCRIPTOR_DESC_IMPLEMENT = " " + PREFIX_DESCRIPTOR + VALID_DESCRIPTOR_IMPLEMENT;
    public static final String DESCRIPTOR_DESC_LEARN = " " + PREFIX_DESCRIPTOR + VALID_DESCRIPTOR_LEARN;

    // Invalid parameters
    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + INVALID_SPACE_TITLE;
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "awards";
    public static final String INVALID_DESCRIPTOR_DESC = " " + PREFIX_DESCRIPTOR + "hubby*"; //

    // Invalid profile item index
    public static final String INVALID_PROFILE_INDEX_MESSAGE =
            String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME);

}
