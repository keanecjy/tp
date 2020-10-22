package seedu.address.logic.parser.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.util.GeneralParserUtil.argumentsAreValid;

import java.util.Set;

import seedu.address.logic.commands.add.AddProfileCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ProfileParserUtil;
import seedu.address.model.profile.Descriptor;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemCategory;
import seedu.address.model.profile.Title;


/**
 * Parses input arguments and creates a new AddProfileCommand object
 */
public class AddProfileCommandParser implements Parser<AddProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProfileCommand
     * and returns an AddProfileCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_CATEGORY, PREFIX_DESCRIPTOR);

        if (!argumentsAreValid(false, argMultimap, PREFIX_TITLE, PREFIX_CATEGORY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProfileCommand.MESSAGE_USAGE));
        }

        Title title = ProfileParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        ProfileItemCategory category = ProfileParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Set<Descriptor> descriptorSet = ProfileParserUtil
                .parseDescriptors(argMultimap.getAllValues(PREFIX_DESCRIPTOR));

        ProfileItem profileItem = new ProfileItem(title, category, descriptorSet);

        return new AddProfileCommand(profileItem);
    }

}

