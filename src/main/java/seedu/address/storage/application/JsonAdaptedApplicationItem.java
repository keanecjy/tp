package seedu.address.storage.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.util.DateUtil;
import seedu.address.storage.internship.JsonAdaptedInternshipItem;
import seedu.address.storage.item.JsonAdaptedItem;

/**
 * Jackson-friendly version of {@link ApplicationItem}.
 */
public class JsonAdaptedApplicationItem extends JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application item's %s field is missing!";

    private final String status;
    private final String statusDate;
    private final JsonAdaptedInternshipItem internshipItem;

    /**
     * Constructs a {@code JsonAdaptedApplicationItem} with the given application item details.
     */
    @JsonCreator
    public JsonAdaptedApplicationItem(@JsonProperty("status") String status,
            @JsonProperty("statusDate") String statusDate,
            @JsonProperty("internshipItem") JsonAdaptedInternshipItem internshipItem) {
        this.status = status;
        this.statusDate = statusDate;
        this.internshipItem = internshipItem;
    }

    /**
     * Converts a given {@code ApplicationItem} into this class for Jackson use.
     */
    public JsonAdaptedApplicationItem(ApplicationItem source) {
        status = source.getStatus().toString();
        statusDate = source.getStatusDate().toString();
        internshipItem = new JsonAdaptedInternshipItem(source.getInternshipItem());
    }

    /**
     * Converts this Jackson-friendly adapted internship item object into the model's {@code ApplicationItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application item.
     */
    @Override
    public ApplicationItem toModelType() throws IllegalValueException {

        if (internshipItem == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipItem.class.getSimpleName()));
        }

        final InternshipItem itemInternship = internshipItem.toModelType();

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status itemStatus = Status.valueOf(status.toUpperCase());

        if (statusDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StatusDate.class.getSimpleName()));
        }
        if (!DateUtil.isValidOutputDate(statusDate)) {
            throw new IllegalValueException(StatusDate.MESSAGE_CONSTRAINTS);
        }
        final StatusDate itemStatusDate = new StatusDate(DateUtil.convertOutputFormat(statusDate));

        return new ApplicationItem(itemInternship, itemStatus, itemStatusDate);
    }

}
