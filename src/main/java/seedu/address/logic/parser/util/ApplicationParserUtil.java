package seedu.address.logic.parser.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;
import seedu.address.model.util.DateUtil;

/**
 * ApplicationParserUtil class which parses all the fields in an ApplicationItem.
 */
public class ApplicationParserUtil {

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = StringUtil.toTitleCase(status.trim());

        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return Status.valueOf(trimmedStatus.toUpperCase());
    }

    /**
     * Parses a {@code String statusDate} into a {@code StatusDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code StatusDate} is invalid.
     */
    public static StatusDate parseStatusDate(String statusDate) throws ParseException {
        requireNonNull(statusDate);
        String trimmedStatusDate = statusDate.trim();

        if (!StatusDate.isValidDate(statusDate)) {
            throw new ParseException(StatusDate.MESSAGE_CONSTRAINTS);
        }

        LocalDateTime localDateTime = DateUtil.convertToDateTime(trimmedStatusDate);
        return new StatusDate(localDateTime);
    }

}
