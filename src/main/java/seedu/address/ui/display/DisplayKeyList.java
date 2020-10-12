package seedu.address.ui.display;

import static seedu.address.ui.panel.PanelDisplayKeyword.ADDRESS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.EMAIL_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.PHONE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.TYPE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

public class DisplayKeyList {

    public static final String[] APPLICATION_DISPLAY_KEY_LIST = {COMPANY_DISPLAY_NAME, PERIOD_DISPLAY_NAME,
        WAGE_DISPLAY_NAME, REQUIREMENTS_DISPLAY_NAME, STATUS_DISPLAY_NAME, DATE_DISPLAY_NAME
    };
    public static final String[] COMPANY_DISPLAY_KEY_LIST = {COMPANY_DISPLAY_NAME, PHONE_DISPLAY_NAME,
        EMAIL_DISPLAY_NAME, ADDRESS_DISPLAY_NAME, INDUSTRIES_DISPLAY_NAME, INTERNSHIPS_DISPLAY_NAME
    };
    public static final String[] PROFILE_DISPLAY_KEY_LIST = {TITLE_DISPLAY_NAME, TYPE_DISPLAY_NAME,
        DESCRIPTORS_DISPLAY_NAME
    };
}
