package seedu.address.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTORS;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.address.logic.parser.util.ProfileParserUtil.parseCategory;
import static seedu.address.logic.parser.util.ProfileParserUtil.parseDescriptors;
import static seedu.address.logic.parser.util.ProfileParserUtil.parseTitle;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.Descriptor;

/**
 * Parses input arguments and creates a new EditProfileCommand object.
 */
public class EditProfileCommandParser implements Parser<EditProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditProfileCommand.
     * and returns an EditProfileCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_CATEGORY,
                PREFIX_DESCRIPTORS);

        Index index = getIndexInPreamble(argMultimap, EditProfileCommand.MESSAGE_USAGE);

        EditProfileItemDescriptor editProfileDescriptor = new EditProfileItemDescriptor();
        // Update the setters
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editProfileDescriptor.setTitle(parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editProfileDescriptor.setProfileItemCategory(parseCategory(argMultimap.getValue(PREFIX_CATEGORY)
                .get()));
        }
        parseDescriptorsForEdit(argMultimap.getAllValues(PREFIX_DESCRIPTORS))
                .ifPresent(editProfileDescriptor::setDescriptors);

        if (!editProfileDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProfileCommand.MESSAGE_USAGE);
        }

        return new EditProfileCommand(index, editProfileDescriptor);
    }
    /**
     * Parses {@code Collection<String> descriptors} into a {@code Set<Descriptor>} if {@code descriptors} is non-empty.
     * If {@code descriptors} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Descriptor>} containing zero descriptors.
     *
     * @throws ParseException if descriptors specified is not a valid descriptor.
     */
    private Optional<Set<Descriptor>> parseDescriptorsForEdit(Collection<String> descriptors) throws ParseException {
        assert descriptors != null;

        if (descriptors.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> descriptorSet = descriptors.size() == 1 && descriptors.contains("")
                ? Collections.emptySet()
                : descriptors;
        return Optional.of(parseDescriptors(descriptorSet));
    }


}
