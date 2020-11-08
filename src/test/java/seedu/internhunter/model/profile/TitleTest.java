package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_DOUBLE_SPACE_TITLE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_EMPTY_TITLE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_SPACE_TITLE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE;

import org.junit.jupiter.api.Test;


public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_EMPTY_TITLE));
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_SPACE_TITLE));
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_DOUBLE_SPACE_TITLE));
    }

    @Test
    public void toString_validFormats_success() {
        Title title = new Title(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE);
        assertEquals(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE, title.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        Title title = new Title(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE);
        Title titleTwo = new Title(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE);
        assertEquals(title, titleTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        Title title = new Title(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE);
        Title titleTwo = new Title(VALID_TITLE_FACEBOOK_INTERNSHIP_LOWERCASE);
        assertEquals(title.hashCode(), titleTwo.hashCode());
    }
}
