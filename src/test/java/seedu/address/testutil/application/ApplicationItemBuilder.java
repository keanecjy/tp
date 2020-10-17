package seedu.address.testutil.application;

import static java.util.Objects.requireNonNull;

import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.util.DateUtil;
import seedu.address.testutil.internship.SampleInternshipItems;

/**
 * TODO: Javadocs (Keane)
 * A utility class to help with building ApplicationItem objects.
 */
public class ApplicationItemBuilder {
    public static final String DEFAULT_STATUS = "APPLIED";
    public static final String DEFAULT_STATUS_DATE = "5-12-19";

    private Status status;
    private StatusDate statusDate;
    private InternshipItem internshipItem;

    /**
     * Creates a {@code ApplicationItemBuilder} with the default details.
     */
    public ApplicationItemBuilder() {
        status = Status.valueOf(DEFAULT_STATUS);
        statusDate = new StatusDate(DateUtil.convertToDateTime(DEFAULT_STATUS_DATE));
        internshipItem = SampleInternshipItems.GOLDMAN_DA;
    }

    /**
     * Initializes the ApplicationItemBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationItemBuilder(ApplicationItem applicationToCopy) {
        requireNonNull(applicationToCopy);
        status = applicationToCopy.getStatus();
        statusDate = applicationToCopy.getStatusDate();
        internshipItem = applicationToCopy.getInternshipItem();
    }

    /**
     * Sets the {@code Status} of the {@code ApplicationItem} that we are building.
     */
    public ApplicationItemBuilder withStatus(String status) {
        requireNonNull(status);
        this.status = Status.valueOf(status);
        return this;
    }

    /**
     * Sets the {@code StatusDate} of the {@code ApplicationItem} that we are building.
     */
    public ApplicationItemBuilder withStatusDate(String statusDate) {
        requireNonNull(statusDate);
        this.statusDate = new StatusDate(DateUtil.convertToDateTime(statusDate));
        return this;
    }

    /**
     * Sets the {@code InternshipItem} of the {@code ApplicationItem} that we are building.
     */
    public ApplicationItemBuilder withInternshipItem(InternshipItem internshipItem) {
        requireNonNull(internshipItem);
        this.internshipItem = internshipItem;
        return this;
    }

    public ApplicationItem build() {
        return new ApplicationItem(internshipItem, status, statusDate);
    }

}
