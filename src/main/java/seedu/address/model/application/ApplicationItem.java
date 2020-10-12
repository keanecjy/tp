package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Objects;

import seedu.address.model.internship.InternshipItem;
import seedu.address.model.item.Item;
import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.storage.item.JsonAdaptedItem;

/**
 * Represents an ApplicationItem in the InternHunter application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ApplicationItem extends Item {

    // Identity fields
    private final InternshipItem internshipItem;

    // Data fields
    private final Status status;
    private final StatusDate statusDate;

    /**
     * Every field must be present and not null.
     */
    public ApplicationItem(InternshipItem internshipItem, Status status, StatusDate statusDate) {
        requireAllNonNull(internshipItem, status, statusDate);
        this.internshipItem = internshipItem;
        this.status = status;
        this.statusDate = statusDate;
    }

    /**
     * Creates an application item using the internship item.
     * Method is used to match internships with their application.
     *
     * @param internshipItem Internship item.
     */
    public ApplicationItem(InternshipItem internshipItem) {
        requireNonNull(internshipItem);
        this.internshipItem = internshipItem;
        this.status = Status.APPLIED;
        this.statusDate = new StatusDate(LocalDateTime.now());
    }

    public InternshipItem getInternshipItem() {
        return internshipItem;
    }

    public Status getStatus() {
        return status;
    }

    public StatusDate getStatusDate() {
        return statusDate;
    }

    /**
     * Obtains the name of the item.
     *
     * @return Item name.
     */
    @Override
    public String getItemName() {
        return APPLICATION_NAME;
    }

    /**
     * Obtains the mapping of all field names to their corresponding fields.
     *
     * @return Mapping of field names to fields for the ApplicationItem object.
     */
    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = internshipItem.getMapping();
        mapping.put(STATUS_DISPLAY_NAME, status);
        mapping.put(DATE_DISPLAY_NAME, statusDate);
        return mapping;
    }

    /**
     * Returns true if both Applications have the {@code InternshipItem}.
     * This defines a weaker notion of equality between two ApplicationItem objects.
     *
     * @param otherItem Other ApplicationItem to compare to.
     * @return True if and only if the 2 Applications have the same identity field.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        // short circuit if same object
        if (otherItem == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherItem instanceof ApplicationItem)) {
            return false;
        }

        ApplicationItem otherApplication = (ApplicationItem) otherItem;
        return otherApplication.getInternshipItem().equals(getInternshipItem());
    }

    /**
     * Returns true if both Applications have the same data fields.
     * This defines a stronger notion of equality between two ApplicationItem objects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ApplicationItem)) {
            return false;
        }

        ApplicationItem otherApplication = (ApplicationItem) other;
        return otherApplication.getInternshipItem().equals(getInternshipItem())
                && otherApplication.getStatus().equals(getStatus())
                && otherApplication.getStatusDate().equals(getStatusDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(internshipItem, status, statusDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInternshipItem())
                .append(", ")
                .append(" Status: ")
                .append(getStatus())
                .append(", ")
                .append(" Date: ")
                .append(getStatusDate());
        return builder.toString();
    }

    @Override
    public JsonAdaptedItem getJsonAdaptedItem() {
        return new JsonAdaptedApplicationItem(this);
    }

}
