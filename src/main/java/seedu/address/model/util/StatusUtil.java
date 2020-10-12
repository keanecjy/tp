package seedu.address.model.util;

import seedu.address.model.application.Status;

/**
 * StatusUtil class which provides the available statuses for an internship application.
 */
public class StatusUtil {

    public static final String APPLIED_KEYWORD = "Applied";
    public static final String INTERVIEW_KEYWORD = "Interview";
    public static final String WAITING_KEYWORD = "Waiting";
    public static final String REJECTED_KEYWORD = "Rejected";
    public static final String OFFERED_KEYWORD = "Offered";
    public static final String ACCEPTED_KEYWORD = "Accepted";

    private static final String ERROR_MESSAGE = "Checks for status validity failed";

    /**
     * Converts the given string to a Status.
     *
     * @param status Status of application.
     * @return Status object.
     */
    public static Status convertToStatus(String status) {
        switch (status) {
        case APPLIED_KEYWORD:
            return Status.APPLIED;
        case INTERVIEW_KEYWORD:
            return Status.INTERVIEW;
        case WAITING_KEYWORD:
            return Status.WAITING;
        case REJECTED_KEYWORD:
            return Status.REJECTED;
        case OFFERED_KEYWORD:
            return Status.OFFERED;
        case ACCEPTED_KEYWORD:
            return Status.ACCEPTED;
        default:
            assert false : ERROR_MESSAGE;
            return null;
        }
    }

}
