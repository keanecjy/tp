package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.ITEM_PREFIX_APPLICATION;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.ITEM_PREFIX_COMPANY;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.ITEM_PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.ITEM_PREFIX_USER_PROFILE;

import seedu.address.logic.commands.add.AddCommandAbstract;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for item type and returns AddCommandAbstract Object.
 */
public class AddCommandParserWrapper implements Parser<AddCommandAbstract> {

    private static final int ITEM_TYPE_INDEX = 0;
    private static final int COMMAND_DETAILS_INDEX = 1;
    private static final int NUMBER_OF_ARGUMENTS_TYPES = 2;

    /**
     * Parses the given {@code String} of arguments for the item type
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandAbstract parse(String args) throws ParseException {

        String[] argumentTypes = args.strip().split(" ", NUMBER_OF_ARGUMENTS_TYPES);
        String itemType = argumentTypes[ITEM_TYPE_INDEX];

        checkItemTypePresent(itemType);
        String commandDetails = getCommandDetails(argumentTypes);
        switch (itemType) {
        case ITEM_PREFIX_COMPANY:
            return new AddCompanyCommandParser().parse(commandDetails);
        case ITEM_PREFIX_INTERNSHIP:
            // todo: create parser object and return command from within
            return new AddInternshipCommand("Not an internship added");
        case ITEM_PREFIX_APPLICATION:
            return new AddApplicationCommandParser().parse(commandDetails);
        case ITEM_PREFIX_USER_PROFILE:
            return null;
        // Throw exception as item type is invalid
        default:
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

    private void checkItemTypePresent(String itemType) throws ParseException {
        if (itemType.trim().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddCommandAbstract.MESSAGE_USAGE));
        }
    }

    private String getCommandDetails(String[] argumentTypes) {
        String dummy = "";
        if (argumentTypes.length < NUMBER_OF_ARGUMENTS_TYPES) {
            return dummy; // if the user only entered the command word and the item type (did not enter details),
            // then provide this dummy string so that the relevant parser will show its error message.
        } else {
            return " " + argumentTypes[COMMAND_DETAILS_INDEX];
        }
    }
}
