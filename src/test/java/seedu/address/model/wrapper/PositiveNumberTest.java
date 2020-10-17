package seedu.address.model.wrapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositiveNumberTest {

    @Test
    public void isValidPositiveNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PositiveNumber.isValidPositiveNumber(null));
    }

    @Test
    public void isValidPositiveNumber_validInput_success() {
        assertTrue(PositiveNumber.isValidPositiveNumber("2"));
        assertTrue(PositiveNumber.isValidPositiveNumber("12345"));
        assertTrue(PositiveNumber.isValidPositiveNumber("243"));
    }

    @Test
    public void isValidPositiveNumber_invalidDigits_success() {
        assertFalse(PositiveNumber.isValidPositiveNumber("0")); // Zero
        assertFalse(PositiveNumber.isValidPositiveNumber("-4")); // Negative number
        assertFalse(PositiveNumber.isValidPositiveNumber("400.00")); // Decimals
        assertFalse(PositiveNumber.isValidPositiveNumber("0.400")); // Decimals
    }

    @Test
    public void isValidPositiveNumber_invalidInput_success() {
        assertFalse(PositiveNumber.isValidPositiveNumber("")); // empty string
        assertFalse(PositiveNumber.isValidPositiveNumber("    ")); // spaces only
        assertFalse(PositiveNumber.isValidPositiveNumber("   213")); // space then number
        assertFalse(PositiveNumber.isValidPositiveNumber("1221abc2131")); // alphabets within digits
        assertFalse(PositiveNumber.isValidPositiveNumber("90 41")); // spaces within digits
    }

}
