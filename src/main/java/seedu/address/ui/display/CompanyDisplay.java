package seedu.address.ui.display;

import static seedu.address.ui.display.DisplayKeyList.COMPANY_DISPLAY_KEY_LIST;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;

import java.util.function.Function;
import java.util.function.Predicate;

import javafx.stage.Stage;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.tabs.TabName;

/**
 * A display containing information about the chosen company.
 */
public class CompanyDisplay extends InformationDisplay<CompanyItem> {

    /**
     * Constructs a {@code CompanyDisplay} in the given {@code primaryStage}.
     */
    private CompanyDisplay(Stage primaryStage, CompanyItem companyItem) {
        super(primaryStage, companyItem);

        initializeCompanyDisplayGui();
    }

    /**
     * Creates the {@code CompanyDisplay} information in the {@code primaryStage}.
     */
    public static CompanyDisplay getCompanyDisplay(Stage primaryStage, CompanyItem companyItem) {
        return new CompanyDisplay(primaryStage, companyItem);
    }

    /**
     * todo Javadocs
     */
    private void initializeCompanyDisplayGui() {
        setInformationTitle();
        setInformation();
    }

    /**
     * todo Javadocs
     */
    private void setInformationTitle() {
        Object jobTitle = mapping.get(COMPANY_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * todo javadocs
     */
    private void setInformation() {
        Predicate<String> isIndustriesOrInternship = key -> key.equals(INDUSTRIES_DISPLAY_NAME)
            || key.equals(INTERNSHIPS_DISPLAY_NAME);
        Function<String, String> editString = string -> string.substring(1, string.length() - 1);
        setInformation(editString, isIndustriesOrInternship, TabName.COMPANY, COMPANY_DISPLAY_KEY_LIST);
    }
}
