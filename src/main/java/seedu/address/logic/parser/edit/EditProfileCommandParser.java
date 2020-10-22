package seedu.address.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.edit.EditCommandAbstract.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.util.GeneralParserUtil.argumentsAreValid;
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
                PREFIX_DESCRIPTOR);

        if (!argumentsAreValid(true, argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProfileCommand.MESSAGE_USAGE));
        }

        Index index = getIndexInPreamble(argMultimap);

        EditProfileItemDescriptor editProfileItemDescriptor = new EditProfileItemDescriptor();
        // Update the setters
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editProfileItemDescriptor.setTitle(parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editProfileItemDescriptor.setProfileItemCategory(parseCategory(argMultimap.getValue(PREFIX_CATEGORY)
                .get()));
        }
        parseDescriptorsForEdit(argMultimap.getAllValues(PREFIX_DESCRIPTOR))
                .ifPresent(editProfileItemDescriptor::setDescriptors);

        if (!editProfileItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditProfileCommand(index, editProfileItemDescriptor);
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
