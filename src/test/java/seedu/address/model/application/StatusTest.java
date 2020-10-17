package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.address.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.address.model.util.StatusUtil.INTERVIEW_KEYWORD;
import static seedu.address.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.address.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.address.model.util.StatusUtil.WAITING_KEYWORD;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    private static final String BLANK_STATUS = "";
    private static final String VALID_STATUS = "APPLIED";
    private static final String INVALID_STATUS = "DEAD";

    @Test
    public void valueOf_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.valueOf(null));
    }

    @Test
    public void valueOf_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf(BLANK_STATUS));
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf(INVALID_STATUS));
    }

    @Test
    public void isValidBoolean_validFormats_success() {
        assertTrue(Status.isValidStatus(APPLIED_KEYWORD));
        assertTrue(Status.isValidStatus(INTERVIEW_KEYWORD));
        assertTrue(Status.isValidStatus(WAITING_KEYWORD));
        assertTrue(Status.isValidStatus(OFFERED_KEYWORD));
        assertTrue(Status.isValidStatus(ACCEPTED_KEYWORD));
        assertTrue(Status.isValidStatus(REJECTED_KEYWORD));
    }

    @Test
    public void toString_validFormat_success() {
        Status status1 = Status.valueOf(VALID_STATUS);
        assertEquals(APPLIED_KEYWORD, status1.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        Status status1 = Status.valueOf(VALID_STATUS);
        Status status2 = Status.valueOf(VALID_STATUS);
        assertEquals(status1, status2);
    }

}
