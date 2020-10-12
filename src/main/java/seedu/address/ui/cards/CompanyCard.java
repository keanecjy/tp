package seedu.address.ui.cards;

import static seedu.address.ui.panel.PanelDisplayKeyword.ADDRESS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.EMAIL_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.PHONE_DISPLAY_NAME;

import seedu.address.model.company.CompanyItem;

/**
 * todo Javadocs
 */
public class CompanyCard extends Card<CompanyItem> {

    /**
     * todo Javadocs
     */
    public CompanyCard(CompanyItem companyItem, int displayedIndex) {
        super(companyItem, displayedIndex);
        initializeCompanyCardGui();
    }

    /**
     * todo Javadocs
     */
    private void initializeCompanyCardGui() {
        setId(displayedIndex);
        setName();
        setIndustries();
        setPhone();
        setEmail();
        setAddress();
    }

    /**
     * todo Javadocs
     */
    private void setName() {
        Object companyName = mapping.get(COMPANY_DISPLAY_NAME);
        setName(companyName.toString());
    }

    /**
     * todo Javadocs
     */
    private void setIndustries() {
        Object industries = mapping.get(INDUSTRIES_DISPLAY_NAME);
        if (!industries.toString().equals("[]")) {
            setTags(industries.toString());
        }
    }

    /**
     * todo Javadocs
     */
    private void setPhone() {
        Object phone = mapping.get(PHONE_DISPLAY_NAME);
        setTextAt(PHONE_DISPLAY_NAME, phone.toString(), LineNumber.L1);
    }

    /**
     * todo Javadocs
     */
    private void setEmail() {
        Object email = mapping.get(EMAIL_DISPLAY_NAME);
        setTextAt(EMAIL_DISPLAY_NAME, email.toString(), LineNumber.L2);
    }

    /**
     * todo Javadocs
     */
    private void setAddress() {
        Object address = mapping.get(ADDRESS_DISPLAY_NAME);
        setTextAt(ADDRESS_DISPLAY_NAME, address.toString(), LineNumber.L3);
    }
}
