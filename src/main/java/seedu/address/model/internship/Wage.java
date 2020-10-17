package seedu.address.model.internship;

import seedu.address.model.wrapper.PositiveNumber;

/**
 * Represents an InternshipItem's wage.
 * Guarantees: immutable; is valid as declared in {@link #isValidWage(String)}
 */
public class Wage extends PositiveNumber {

    public static final String WAGE_SYMBOL = "$";
    public static final String MESSAGE_CONSTRAINTS = "Wage should only contain a positive number";

    /**
     * Constructs a {@code Wage}.
     *
     * @param wage A valid wage.
     */
    public Wage(String wage) {
        super(wage, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid wage.
     *
     * @param wage String to test.
     * @return True if the given string is a valid wage, false otherwise.
     */
    public static boolean isValidWage(String wage) {
        return isValidPositiveNumber(wage);
    }

    /**
     * Returns the string representation of this wage object.
     *
     * @return String representation of this wage object.
     */
    @Override
    public String toString() {
        return WAGE_SYMBOL + getValue();
    }

}
