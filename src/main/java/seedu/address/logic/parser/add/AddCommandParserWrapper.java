package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.address.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.commands.add.AddCommandAbstract;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for item type and returns AddCommandAbstract Object.
 */
public class AddCommandParserWrapper implements Parser<AddCommandAbstract> {

    /**
     * Parses the given {@code String} of arguments for the item type
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandAbstract parse(String args) throws ParseException {
        String itemType = getItemType(args, AddCommandAbstract.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new AddCompanyCommandParser().parse(commandDetails);
        case INTERNSHIP_ALIAS:
            return new AddInternshipCommandParser().parse(commandDetails);
        case APPLICATION_ALIAS:
            return new AddApplicationCommandParser().parse(commandDetails);
        case PROFILE_ALIAS:
            return new AddProfileCommandParser().parse(commandDetails);
        default:
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
