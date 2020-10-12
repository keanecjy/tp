package seedu.address.logic.parser.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.util.ProfileItemCategoryUtil.ACHIEVEMENT_KEYWORD;
import static seedu.address.model.util.ProfileItemCategoryUtil.EXPERIENCE_KEYWORD;
import static seedu.address.model.util.ProfileItemCategoryUtil.SKILL_KEYWORD;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.Descriptor;
import seedu.address.model.profile.ProfileItemCategory;
import seedu.address.model.profile.Title;

/**
 * Contains utility methods used for parsing Strings to fields in a ProfileItem.
 */
public class ProfileParserUtil {

    private static final String INVALID_PROFILE_CATEGORY_MESSAGE = "Invalid profile item category";

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Title parseTitle(String name) throws ParseException {
        requireNonNull(name);
        String trimmedTitle = name.trim();
        if (!Title.isValidAlphaNumericWord(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String category} into a {@code ProfileItemCategory}.
     *
     * @param category category in String form.
     * @return ProfileItemCategory Object parsed from String.
     * @throws ParseException if not a valid profileItemCategory.
     */
    public static ProfileItemCategory parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        String trimmedLowerCaseCategory = trimmedCategory.toLowerCase();

        if (!ProfileItemCategory.isValidProfileItemCategory(trimmedLowerCaseCategory)) {
            throw new ParseException(ProfileItemCategory.MESSAGE_CONSTRAINTS);
        }

        switch (trimmedLowerCaseCategory) {
        case ACHIEVEMENT_KEYWORD:
            return ProfileItemCategory.ACHIEVEMENT;
        case EXPERIENCE_KEYWORD:
            return ProfileItemCategory.EXPERIENCE;
        case SKILL_KEYWORD:
            return ProfileItemCategory.SKILL;
        default:
            assert false : INVALID_PROFILE_CATEGORY_MESSAGE;
            return null;
        }
    }

    /**
     * Parses a {@code String descriptor} into a {@code Descriptor}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code descriptor} is invalid.
     */
    public static Descriptor parseDescriptor(String descriptor) throws ParseException {
        requireNonNull(descriptor);
        String trimmedDescriptor = descriptor.trim();
        if (!Descriptor.isValidDescriptor(trimmedDescriptor)) {
            throw new ParseException(Descriptor.MESSAGE_CONSTRAINTS);
        }
        return new Descriptor(descriptor);
    }

    /**
     * Parses {@code Collection<String> descriptor} into a {@code Set<Descriptor>}.
     * @throws ParseException for invalid descriptors in descriptorSet.
     */
    public static Set<Descriptor> parseDescriptors(Collection<String> descriptors) throws ParseException {
        requireNonNull(descriptors);
        final Set<Descriptor> descriptorSet = new HashSet<>();
        for (String descriptor : descriptors) {
            descriptorSet.add(parseDescriptor(descriptor));
        }
        return descriptorSet;
    }

}
