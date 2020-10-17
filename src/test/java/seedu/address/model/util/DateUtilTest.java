package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.DateUtil.DATE_INPUT_FORMAT;
import static seedu.address.model.util.DateUtil.DATE_TIME_INPUT_FORMAT;
import static seedu.address.model.util.DateUtil.DEFAULT_TIME;
import static seedu.address.model.util.DateUtil.ERROR_MESSAGE;
import static seedu.address.model.util.DateUtil.convertOutputFormat;
import static seedu.address.model.util.DateUtil.convertToDateTime;
import static seedu.address.model.util.DateUtil.extractDayAndMonth;
import static seedu.address.model.util.DateUtil.formatterDateTime;
import static seedu.address.model.util.DateUtil.isDateFormat;
import static seedu.address.model.util.DateUtil.isDateTimeFormat;
import static seedu.address.model.util.DateUtil.isValidOutputDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    /**
     * DATE_TIME FORMATS
     */
    private static final String VALID_DATE_TIME_ONE = "18-9-20 2240";
    private static final String VALID_DATE_TIME_TWO = "9-09-20 0000";
    private static final String INVALID_DATE_TIME_ONE = "18-9-20   2240";
    private static final String INVALID_DATE_TIME_TWO = "9-09-20 00 00";

    /**
     * DATE FORMATS
     */
    private static final String VALID_DATE_ONE = "18-9-20";
    private static final String VALID_DATE_TWO = "9-09-20";
    private static final String INVALID_DATE_ONE = "20-09-20 ";
    private static final String INVALID_DATE_TWO = " 9-09-20";

    /**
     * OUTPUT FORMATS
     */
    private static final String VALID_OUTPUT_FORMAT = "18 Sep 2020 @ 10.40 PM";
    private static final String INVALID_OUTPUT_FORMAT = "18 Sep 2020 @ 10.40 pm";
    private static final String VALID_SHORT_FORMAT = "18 Sep";

    @Test
    public void isDateTimeFormat_validFormats_success() {
        assertTrue(isDateTimeFormat(VALID_DATE_TIME_ONE));
        assertTrue(isDateTimeFormat(VALID_DATE_TIME_TWO));
    }

    @Test
    public void isDateTimeFormat_invalidFormats_success() {
        assertFalse(isDateTimeFormat(INVALID_DATE_TIME_ONE));
        assertFalse(isDateTimeFormat(INVALID_DATE_TIME_TWO));
        assertFalse(isDateTimeFormat(VALID_DATE_ONE));
        assertFalse(isDateTimeFormat(VALID_DATE_TWO));
    }

    @Test
    public void isDateFormat_validFormats_success() {
        assertTrue(isDateFormat(VALID_DATE_ONE));
        assertTrue(isDateFormat(VALID_DATE_TWO));
    }

    @Test
    public void isDateFormat_invalidFormats_success() {
        assertFalse(isDateFormat(INVALID_DATE_ONE));
        assertFalse(isDateFormat(INVALID_DATE_TWO));
        assertFalse(isDateFormat(VALID_DATE_TIME_ONE));
        assertFalse(isDateFormat(VALID_DATE_TIME_TWO));
    }


    @Test
    public void convertToDateTime_validFormats_success() {
        // Converting LocalDateTime
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse(VALID_DATE_TIME_ONE,
                DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT));
        assertEquals(expectedLocalDateTime, convertToDateTime(VALID_DATE_TIME_ONE));

        // Converting LocalDate
        LocalDate localDate = LocalDate.parse(VALID_DATE_ONE, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
        LocalDateTime expectedLocalDateTimeDefaultTime = LocalDateTime.of(localDate, LocalTime.parse(DEFAULT_TIME));
        assertEquals(expectedLocalDateTimeDefaultTime, convertToDateTime(VALID_DATE_ONE));
    }

    @Test
    public void convertToDateTime_invalidFormats_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> convertToDateTime(INVALID_DATE_TIME_ONE));
        assertThrows(AssertionError.class, () -> convertToDateTime(INVALID_DATE_TIME_TWO));
        assertThrows(AssertionError.class, () -> convertToDateTime(INVALID_DATE_ONE));
        assertThrows(AssertionError.class, () -> convertToDateTime(INVALID_DATE_TWO));
    }

    @Test
    public void convertToDateTime_invalidFormatsErrorMessage_success() {
        AssertionError ae = assertThrows(AssertionError.class, () -> convertToDateTime(INVALID_DATE_TIME_ONE));
        assertEquals(ERROR_MESSAGE, ae.getMessage());
    }

    @Test
    public void convertOutputFormat_validFormats_success() {
        assertEquals(LocalDateTime.parse(VALID_DATE_TIME_ONE, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT)),
                convertOutputFormat(VALID_OUTPUT_FORMAT));
    }

    @Test
    public void isValidOutputDate_validFormat_success() {
        assertTrue(isValidOutputDate(VALID_OUTPUT_FORMAT));
    }

    @Test
    public void isValidOutputDate_invalidFormat_success() {
        assertFalse(isValidOutputDate(INVALID_OUTPUT_FORMAT));
    }

    @Test
    public void formatterDateTime_validInput_success() {
        DateTimeFormatter expected = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT);
        // Comparison using toString since different DateTimeFormatter objects are not equal
        assertEquals(expected.toString(), formatterDateTime(DATE_TIME_INPUT_FORMAT).toString());
    }

    @Test
    public void extractDayAndMonth_validInput_success() {
        String[] dateInfoArray = VALID_OUTPUT_FORMAT.split(" ");
        assertEquals(VALID_SHORT_FORMAT, extractDayAndMonth(dateInfoArray));
    }

}
