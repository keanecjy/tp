package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class TabUtil {

    /**
     * Takes in a {@code tab} and converts the {@code tab} into it's {@code TabName}.
     */
    public static TabName getSwitchTabName(String tab) throws ParseException {
        TabName tabName;
        switch (tab) {
        case COMPANY_ALIAS:
            tabName = TabName.COMPANY;
            break;
        case APPLICATION_ALIAS:
            tabName = TabName.APPLICATION;
            break;
        case PROFILE_ALIAS:
            tabName = TabName.PROFILE;
            break;
        default:
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);
        }
        return tabName;
    }

}
