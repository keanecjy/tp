package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.model.internship.Wage.WAGE_SYMBOL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.address.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;

import org.junit.jupiter.api.Test;

public class WageTest {

    private static final String INVALID_WAGE_ZERO = "0";
    private static final String INVALID_WAGE_NEGATIVE = "-5";
    private static final String INVALID_WAGE_DECIMAL = "3555.4";

    private static final Wage VALID_WAGE_ONE = new Wage(VALID_WAGE_2000);
    private static final Wage VALID_WAGE_TWO = new Wage(VALID_WAGE_3000);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Wage(null));
    }

    @Test
    public void constructor_invalidWage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Wage(INVALID_WAGE_ZERO));
        assertThrows(IllegalArgumentException.class, () -> new Wage(INVALID_WAGE_NEGATIVE));
        assertThrows(IllegalArgumentException.class, () -> new Wage(INVALID_WAGE_DECIMAL));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_WAGE_2000, VALID_WAGE_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(WAGE_SYMBOL + VALID_WAGE_2000, VALID_WAGE_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_WAGE_ONE, VALID_WAGE_ONE);
        Wage wageCopy = new Wage(VALID_WAGE_2000);
        assertEquals(VALID_WAGE_ONE, wageCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_WAGE_ONE, VALID_WAGE_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_WAGE_ONE.hashCode(), VALID_WAGE_ONE.hashCode());
    }

}
