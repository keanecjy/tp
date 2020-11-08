package seedu.internhunter.logic.parser.util;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;

/**
 * Contains utility methods used for parsing Strings to fields in a ProfileItem.
 */
public class ProfileParserUtil {

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name in String form.
     * @return Title Object parsed from String.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Title parseTitle(String name) throws ParseException {
        requireNonNull(name);
        String trimmedTitle = name.trim();
        if (!Title.isValidNonEmptyString(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String category} into a {@code ProfileItemCategory}.
     *
     * @param category in String form.
     * @return ProfileItemCategory Object parsed from String.
     * @throws ParseException if not a valid profileItemCategory.
     */
    public static ProfileItemCategory parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        String trimmedUpperCaseCategory = trimmedCategory.toUpperCase();
        if (!ProfileItemCategory.isValidProfileItemCategory(trimmedUpperCaseCategory)) {
            throw new ParseException(ProfileItemCategory.MESSAGE_CONSTRAINTS);
        }
        return ProfileItemCategory.valueOf(trimmedUpperCaseCategory);
    }

    /**
     * Parses a {@code String descriptor} into a {@code Descriptor}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param descriptor in String form.
     * @return Descriptor Object parsed from String.
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
     *
     * @param descriptors Collection of String of descriptors.
     * @return Set containing descriptor objects.
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
