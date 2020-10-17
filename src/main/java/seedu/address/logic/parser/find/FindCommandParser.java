package seedu.address.logic.parser.find;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.address.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.address.logic.parser.util.GeneralParserUtil.isValidItemType;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.commands.find.FindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * todo javadocs
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        String itemType = getItemType(args, FindCommand.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        isValidItemType(itemType);
        checkCommandDetailsIsNotBlank(commandDetails, itemType);

        switch (itemType) {
        case COMPANY_ALIAS:
            // todo shawn
            //return new FindCompanyCommand(commandDetails);
            return null;
        case APPLICATION_ALIAS:
            // todo sean
            //return new FindApplicationCommand(commandDetails);
            return null;
        case PROFILE_ALIAS:
            // todo isaac
            //return new FindProfileCommand(commandDetails);
            return null;
        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

    /**
     * todo javadocs
     */
    private void checkCommandDetailsIsNotBlank(String commandDetails, String itemType) throws ParseException {
        if (commandDetails.isBlank()) {
            switch (itemType) {
            case COMPANY_ALIAS:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                //    FindCompanyCommand.MESSAGE_USAGE));

            case APPLICATION_ALIAS:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                //   FindApplicationCommand.MESSAGE_USAGE));

            case PROFILE_ALIAS:
                //throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                //   FindProfileCommand.MESSAGE_USAGE));

            default:
                // Invalid item type
                throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
            }
        }
    }

}
