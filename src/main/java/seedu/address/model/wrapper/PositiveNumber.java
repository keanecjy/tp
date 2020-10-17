package seedu.address.model.wrapper;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a positive number object. TODO: Possibly remove in the future
 * Guarantees: immutable; is valid as declared in {@link #isValidPositiveNumber(String)}
 */
public abstract class PositiveNumber {

    public static final String VALIDATION_REGEX = "^[1-9]\\d*";

    private final String value;

    /**
     * Constructs a {@code PositiveNumber}.
     *
     * @param number Input number.
     */
    public PositiveNumber(String number, String messageConstraints) {
        requireNonNull(number);
        checkArgument(isValidPositiveNumber(number), messageConstraints);
        value = number;
    }

    /**
     * Returns true if a given string is a valid number.
     * Valid PositiveNumber is defined as a positive number.
     */
    public static boolean isValidPositiveNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositiveNumber // instanceof handles nulls
                && value.equals(((PositiveNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
