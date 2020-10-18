package seedu.address.logic.parser.util;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Handles the general parsing of all commands.
 * TODO: Javadocs keane
 */
public class GeneralParserUtil {

    private static final int ITEM_TYPE_INDEX = 0;
    private static final int COMMAND_DETAILS_INDEX = 1;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks if the arguments provided by the user are valid. Arguments are valid if prefixes are all present and
     * there is no text before the preamble.
     *
     * @param argumentMultimap Argument multimap.
     * @param prefixes Prefixes required in the multimap.
     * @return True if and only if the prefixes are valid.
     */
    public static boolean argumentsAreValid(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        boolean prefixesArePresent = arePrefixesPresent(argumentMultimap, prefixes);
        boolean preambleIsEmpty = isPreambleEmpty(argumentMultimap);
        return prefixesArePresent && preambleIsEmpty;
    }

    /**
     * Returns true if the preamble of this argument multimap is empty.
     *
     * @param argumentMultimap Argument multimap.
     * @return True if and only if preamble of this argument multimap is empty.
     */
    private static boolean isPreambleEmpty(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getPreamble().isEmpty();
    }

    public static Index getIndexInPreamble(ArgumentMultimap argumentMultimap, String messageUsage)
            throws ParseException {

        try {
            return parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
    }

    /**
     * Checks if item type is present. If item type is not present, then throw ParseException that displays
     * the correct command format to user.
     *
     * @param args Arguments provided by the user.
     * @param messageUsage Correct message format to display.
     * @return Item type, if it exists.
     * @throws ParseException If item type is not provided.
     */
    public static String getItemType(String args, String messageUsage) throws ParseException {
        String[] argumentTypes = getArgumentsArr(args);
        String itemType = argumentTypes[ITEM_TYPE_INDEX];

        if (itemType.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
        return itemType;
    }

    /** todo javadocs */
    public static void isValidItemType(String itemType) throws ParseException {
        if (!itemType.equals(COMPANY_ALIAS)
                && !itemType.equals(INTERNSHIP_ALIAS)
                && !itemType.equals(APPLICATION_ALIAS)
                && !itemType.equals(PROFILE_ALIAS)) {
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

    /**
     * Obtains the command details.
     *
     * @param args Arguments provided by the user.
     * @return Details of the command.
     */
    public static String getCommandDetails(String args) {
        String[] argumentTypes = getArgumentsArr(args);
        String dummy = "";
        if (argumentTypes.length < NUMBER_OF_ARGUMENTS) {
            return dummy; // if the user only entered the command word and the item type (did not enter details),
            // then provide this dummy string so that the relevant parser will show its error message.
        } else {
            return " " + argumentTypes[COMMAND_DETAILS_INDEX];
        }
    }

    public static String[] getArgumentsArr(String args) {
        return args.strip().split(" ", NUMBER_OF_ARGUMENTS);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * todo javadocs
     */
    public static void checkCommandDetailsIsNotBlank(String commandDetails, String message) throws ParseException {
        if (commandDetails.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, message));
        }
    }

    /**
     * todo javadocs
     */
    public static String[] getTrimmedArgsKeywords(String args) {
        String trimmedArgs = args.trim();
        return trimmedArgs.split("\\s+");
    }
}
