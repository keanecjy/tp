package seedu.address.logic.parser.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.util.TabUtil;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.tabs.TabName;

public class TabParserUtil {

    /**
     * Parses {@code selectedTab} into a {@code TabName} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified selectedTab is invalid (not com or int or me).
     */
    public static TabName parseTab(String selectedTab) throws ParseException {
        requireNonNull(selectedTab);
        String tab = selectedTab.trim();
        if (tab.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
        }
        String[] tabArray = tab.split(" ");
        if (tabArray.length > 1) {
            throw new ParseException(SwitchCommand.EXCESS_MESSAGE);
        }
        return TabUtil.getSwitchTabName(tab);
    }
}
