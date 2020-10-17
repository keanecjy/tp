package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.util.DateUtil.DATE_TIME_LONG_FORMAT;
import static seedu.address.model.util.DateUtil.formatterDateTime;
import static seedu.address.model.util.DateUtil.isDateFormat;
import static seedu.address.model.util.DateUtil.isDateTimeFormat;

import java.time.LocalDateTime;

/**
 * Represents the date of the Application status update.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class StatusDate {

    public static final String MESSAGE_CONSTRAINTS = "Status date should be in the format of d-M-yy or d-M-yy HHmm";

    private final LocalDateTime statusDate;

    /**
     * Constructs an {@code StatusDate}.
     *
     * @param statusDate A valid statusDate.
     */
    public StatusDate(LocalDateTime statusDate) {
        requireNonNull(statusDate);
        this.statusDate = statusDate;
    }

    /**
     * Returns true if the given statusDate is valid.
     *
     * @param statusDate Input statusDate.
     * @return True if statusDate has a valid input format, false otherwise.
     */
    public static boolean isValidDate(String statusDate) {
        return isDateFormat(statusDate) || isDateTimeFormat(statusDate);
    }

    /**
     * Checks if the otherDate has the same statusDate as this Date object.
     * Two LocalDateTime objects have ths same statusDate if they have the same year and statusDate.
     *
     * @param otherDate Date of the other task.
     * @return True if the other task has the same statusDate.
     */
    private boolean sameDate(LocalDateTime otherDate) {
        boolean sameYear = statusDate.getYear() == otherDate.getYear();
        boolean sameDay = statusDate.getDayOfYear() == otherDate.getDayOfYear();
        return sameYear && sameDay;
    }

    /**
     * Returns true if both StatusDates have the same year and day.
     *
     * @param other Object object to compare to.
     * @return True if the other StatusDate object has the same year and day as this one.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusDate // instanceof handles nulls
                && sameDate(((StatusDate) other).statusDate)); // state check
    }

    /**
     * Returns the hashcode of this StatusDate object, which is the hashcode of its statusDate field.
     *
     * @return Hashcode of this StatusDate object.
     */
    @Override
    public int hashCode() {
        return statusDate.hashCode();
    }

    /**
     * Returns the string representation of this StatusDate object.
     *
     * @return String representation of this StatusDate object.
     */
    @Override
    public String toString() {
        return statusDate.format(formatterDateTime(DATE_TIME_LONG_FORMAT));
    }

}
