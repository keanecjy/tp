package seedu.address.logic.parser.edit;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.address.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.commands.edit.EditCommandAbstract;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditCommandParserWrapper implements Parser<EditCommandAbstract> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns a EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommandAbstract parse(String args) throws ParseException {

        String itemType = getItemType(args, EditCommandAbstract.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new EditCompanyCommandParser().parse(commandDetails);

        case INTERNSHIP_ALIAS:
            // todo: Return own edit command
            return null;

        case APPLICATION_ALIAS:
            return new EditApplicationCommandParser().parse(commandDetails);

        case PROFILE_ALIAS:
            //todo: return own edit command
            return null;

        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
