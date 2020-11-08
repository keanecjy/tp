package seedu.internhunter.testutil.profile;

/**
 * Contains fields of ProfileItem used for SampleProfileItems and ProfileItem tests.
 */
public class ProfileItemFieldsUtil {

    // Valid titles
    public static final String VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE = "internship at facebook";
    public static final String VALID_TITLE_GOVTECH_INTERNSHIP = "Internship with Govtech";
    public static final String VALID_TITLE_HTML = "HTML";
    public static final String VALID_TITLE_R = "R";
    public static final String VALID_TITLE_GRAPHQL = "GraphQL";
    public static final String VALID_TITLE_INTERNSHIP = "Data Analyst Internship at ByteDance";
    public static final String VALID_TITLE_COMPETITION = "Won second place in Code Jam";
    public static final String VALID_TITLE_HACKATHON = "Winner of Special Recognition in Orbital";
    public static final String VALID_TITLE_PARTICIPATE = "Participation in MS OpenHack";
    public static final String VALID_TITLE_NUS_MODS = "Contribute to NUSMODS";

    // Invalid titles
    public static final String INVALID_EMPTY_TITLE = "";
    public static final String INVALID_SPACE_TITLE = " ";
    public static final String INVALID_DOUBLE_SPACE_TITLE = "  ";

    // Valid categories for ProfileItemCategory, only upper case
    public static final String VALID_CATEGORY_SKILL = "SKILL";
    public static final String VALID_CATEGORY_ACHIEVEMENT = "ACHIEVEMENT";
    public static final String VALID_CATEGORY_EXPERIENCE = "EXPERIENCE";

    // Invalid Categories for ProfileItemCategory
    public static final String INVALID_CATEGORY_CASING = "sKiLl";

    // Valid Categories user inputs, case insensitive
    public static final String VALID_CATEGORY_SKILL_INPUT = "skill";
    public static final String VALID_CATEGORY_EXPERIENCE_INPUT = "experience";
    public static final String VALID_CATEGORY_ACHIEVEMENT_INPUT = "achievement";

    // Invalid Categories for user input and ProfileItemCategory
    public static final String BLANK_CATEGORY = "";
    public static final String INVALID_CATEGORY_TYPE = "AWARDS";

    // Valid Descriptors
    public static final String VALID_DESCRIPTOR_IMPLEMENT = "Implement automated tool to boost productivity by 15%";
    public static final String VALID_DESCRIPTOR_LEARN = "Learn how to run a cron job";
    public static final String VALID_DESCRIPTOR_GOVTECH = "DevOps solution to speed up automated testing by 50%";
    public static final String VALID_DESCRIPTOR_DEVELOP = "Developed dashboard to keep track of performance metrics";
    public static final String VALID_DESCRIPTOR_COMPETITION = "Solved the Board Meeting Question.";
    public static final String VALID_DESCRIPTOR_HTML = "learn to create tables.";
    public static final String VALID_DESCRIPTOR_FIX = "Fix UI bugs in Modules View";
    public static final String VALID_DESCRIPTOR = "Implement a automated tool to speed up testing by 20%";
    public static final String VALID_DESCRIPTOR_PUNCTUATION = ".,!?:;+#%";
    public static final String VALID_DESCRIPTOR_SOLVE = "Solve the Agriculture challenge with satellite imagery";

    // Invalid Descriptors
    public static final String INVALID_BLANK_DESCRIPTOR = "";
    public static final String INVALID_DESCRIPTOR_PARENTHESIS = "Wrote documentation (with User Guide included)";
    public static final String INVALID_DESCRIPTOR_AMPERSAND = "Deploy a bot @ telegram";

}
