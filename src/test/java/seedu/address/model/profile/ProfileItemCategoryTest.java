package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProfileItemCategoryTest {
    static final String BLANK_CATEGORY = "";
    static final String VALID_CATEGORY_SKILL = "skill".toUpperCase();
    static final String VALID_CATEGORY_ACHIEVEMENT = "achievement".toUpperCase();
    static final String VALID_CATEGORY_EXPERIENCE = "experience".toUpperCase();
    static final String INVALID_CATEGORY = "awards".toUpperCase();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ProfileItemCategory.valueOf(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(BLANK_CATEGORY));
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(INVALID_CATEGORY));
    }

    @Test
    public void toString_validFormats_success() {
        ProfileItemCategory achievement = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(VALID_CATEGORY_ACHIEVEMENT, achievement.toString());
        ProfileItemCategory skill = ProfileItemCategory.valueOf(VALID_CATEGORY_SKILL);
        assertEquals(VALID_CATEGORY_SKILL, skill.toString());
        ProfileItemCategory experience = ProfileItemCategory.valueOf(VALID_CATEGORY_EXPERIENCE);
        assertEquals(VALID_CATEGORY_EXPERIENCE, experience.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory, profileItemCategoryTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory.hashCode(), profileItemCategoryTwo.hashCode());
    }
}
