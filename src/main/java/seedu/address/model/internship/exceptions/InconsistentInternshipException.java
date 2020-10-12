package seedu.address.model.internship.exceptions;

/**
 * Signals that an application has an internship that does not exist in any company's list of internships.
 */
public class InconsistentInternshipException extends Exception {

    public InconsistentInternshipException() {
        super("An application has an internship that does not exist in any company's list of internships");
    }
}
