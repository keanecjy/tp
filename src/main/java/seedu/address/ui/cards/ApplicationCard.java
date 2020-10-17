package seedu.address.ui.cards;

import static seedu.address.model.util.DateUtil.extractDayAndMonth;
import static seedu.address.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.address.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.address.model.util.StatusUtil.INTERVIEW_KEYWORD;
import static seedu.address.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.address.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.address.model.util.StatusUtil.WAITING_KEYWORD;
import static seedu.address.ui.GuardClauseUi.IS_EMPTY_LIST_STRING;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

import javafx.scene.image.Image;
import seedu.address.model.application.ApplicationItem;

/**
 * todo Javadocs
 */
public class ApplicationCard extends Card<ApplicationItem> {

    //Image Link
    private static final String CALENDAR_IMAGE_LINK = "/images/calendar.png";

    //FXML properties
    private static final String MIN_WIDTH_STATUS_BOX = " -fx-min-width: 60;";
    private static final String MAX_WIDTH_STATUS_BOX = " -fx-max-width: 60;";
    private static final String MIN_HEIGHT_STATUS_BOX = " -fx-min-height: 20;";
    private static final String MAX_HEIGHT_STATUS_BOX = " -fx-max-height: 20;";
    private static final String TRANSLATE_Y_STATUS_BOX = "-fx-translate-y: 5;";
    private static final String ALIGNMENT_STATUS_BOX = "-fx-alignment: center;";
    private static final String DEFAULT_STATUS_BOX = MIN_WIDTH_STATUS_BOX + MIN_HEIGHT_STATUS_BOX
        + MAX_HEIGHT_STATUS_BOX + MAX_WIDTH_STATUS_BOX + TRANSLATE_Y_STATUS_BOX + ALIGNMENT_STATUS_BOX;
    private static final String ACCEPTED_COLOR = "-fx-background-color: #3ADB9D;";
    private static final String WAITING_COLOR = "-fx-background-color: #F4D014;";
    private static final String REJECTED_COLOR = "-fx-background-color: #F02E62;";
    private static final String APPLIED_COLOR = "-fx-background-color: #399fd2;";
    private static final String INTERVIEW_COLOR = "-fx-background-color: #f86a23;";
    private static final String OFFERED_COLOR = "-fx-background-color: #0e2578;";
    private static final int IMAGE_HEIGHT_WIDTH = 23;

    /**
     * todo Javadocs
     */
    public ApplicationCard(ApplicationItem applicationItem, int displayedIndex) {
        super(applicationItem, displayedIndex);
        initializeInternshipCardGui();
    }

    /**
     * todo Javadocs
     */
    private void initializeInternshipCardGui() {
        setId(displayedIndex);
        setName();
        setRequirements();
        setStatus();
        setPeriod();
        setWage();
        setCompanyName();
        setDate();
    }

    /**
     * todo Javadocs
     */
    private void setName() {
        Object jobTitle = mapping.get(JOB_TITLE_DISPLAY_NAME);
        setName(jobTitle.toString());
    }

    /**
     * todo Javadocs
     */
    private void setRequirements() {
        Object requirements = mapping.get(REQUIREMENTS_DISPLAY_NAME);
        if (!IS_EMPTY_LIST_STRING.test(requirements.toString())) {
            setTags(requirements.toString());
        }
    }

    /**
     * todo Javadocs
     */
    private void setStatus() {
        Object status = mapping.get(STATUS_DISPLAY_NAME);
        if (!IS_EMPTY_LIST_STRING.test(status.toString())) {
            getStatusStyle(status.toString());
        }
    }

    /**
     * todo Javadocs
     */
    private void getStatusStyle(String statusType) {
        String updatedStatusBoxStyle = DEFAULT_STATUS_BOX;
        switch (statusType) {
        case (APPLIED_KEYWORD):
            status.setText(APPLIED_KEYWORD);
            updatedStatusBoxStyle += APPLIED_COLOR;
            break;
        case (INTERVIEW_KEYWORD):
            status.setText(INTERVIEW_KEYWORD);
            updatedStatusBoxStyle += INTERVIEW_COLOR;
            break;
        case (WAITING_KEYWORD):
            status.setText(WAITING_KEYWORD);
            updatedStatusBoxStyle += WAITING_COLOR;
            break;
        case (REJECTED_KEYWORD):
            status.setText(REJECTED_KEYWORD);
            updatedStatusBoxStyle += REJECTED_COLOR;
            break;
        case (OFFERED_KEYWORD):
            status.setText(OFFERED_KEYWORD);
            updatedStatusBoxStyle += OFFERED_COLOR;
            break;
        case (ACCEPTED_KEYWORD):
            status.setText(ACCEPTED_KEYWORD);
            updatedStatusBoxStyle += ACCEPTED_COLOR;
            break;
        default:
            assert false;
            break;
        }
        statusBox.setStyle(updatedStatusBoxStyle);
    }

    /**
     * todo Javadocs
     */
    private void setCompanyName() {
        Object companyName = mapping.get(COMPANY_DISPLAY_NAME);
        setTextAt(COMPANY_DISPLAY_NAME, companyName.toString(), LineNumber.L1);
    }

    /**
     * todo Javadocs
     */
    private void setWage() {
        Object wage = mapping.get(WAGE_DISPLAY_NAME);
        setTextAt(WAGE_DISPLAY_NAME, wage.toString(), LineNumber.L2);
    }

    /**
     * todo Javadocs
     */
    private void setPeriod() {
        Object period = mapping.get(PERIOD_DISPLAY_NAME);
        setTextAt(PERIOD_DISPLAY_NAME, period.toString(), LineNumber.L3);
    }

    /**
     * todo Javadocs
     */
    private void setDate() {
        Image calendarIcon = new Image(this.getClass().getResourceAsStream(CALENDAR_IMAGE_LINK));
        imageView.setImage(calendarIcon);
        setImageStyling();

        Object dateInformation = mapping.get(DATE_DISPLAY_NAME);
        String[] dateInformationArray = dateInformation.toString().split(" ");
        date.setText(extractDayAndMonth(dateInformationArray));
    }

    /**
     * todo Javadocs
     */
    private void setImageStyling() {
        imageView.setFitHeight(IMAGE_HEIGHT_WIDTH);
        imageView.setFitWidth(IMAGE_HEIGHT_WIDTH);
    }

}
