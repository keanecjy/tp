package seedu.address.storage.internship;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.CompanyName;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.internship.JobTitle;
import seedu.address.model.internship.Period;
import seedu.address.model.internship.Requirement;
import seedu.address.model.internship.Wage;
import seedu.address.storage.item.JsonAdaptedItem;

/**
 * Jackson-friendly version of {@link InternshipItem}.
 */
public class JsonAdaptedInternshipItem extends JsonAdaptedItem {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship item's %s field is missing!";

    private final String companyName;
    private final String jobTitle;
    private final String period;
    private final String wage;
    private final Set<JsonAdaptedRequirement> requirements = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedInternshipItem} with the given internship item details.
     */
    @JsonCreator
    public JsonAdaptedInternshipItem(@JsonProperty("companyName") String companyName,
            @JsonProperty("jobTitle") String jobTitle, @JsonProperty("period") String period,
            @JsonProperty("wage") String wage, @JsonProperty("requirements") Set<JsonAdaptedRequirement> requirements) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.period = period;
        this.wage = wage;
        if (requirements != null) {
            this.requirements.addAll(requirements);
        }
    }

    /**
     * Converts a given {@code InternshipItem} into this class for Jackson use.
     */
    public JsonAdaptedInternshipItem(InternshipItem source) {
        companyName = source.getCompanyName().getValue();
        jobTitle = source.getJobTitle().getValue();
        period = source.getPeriod().getValue();
        wage = source.getWage().getValue();
        requirements.addAll(source.getRequirements().stream()
                .map(JsonAdaptedRequirement::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship item object into the model's {@code InternshipItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship item.
     */
    @Override
    public InternshipItem toModelType() throws IllegalValueException {

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidAlphaNumericWord(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName itemCompanyName = new CompanyName(companyName);

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle itemJobTitle = new JobTitle(jobTitle);

        if (period == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Period.class.getSimpleName()));
        }
        if (!Period.isValidPeriod(period)) {
            throw new IllegalValueException(Period.MESSAGE_CONSTRAINTS);
        }
        final Period itemPeriod = new Period(period);

        if (wage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Wage.class.getSimpleName()));
        }
        if (!Wage.isValidWage(wage)) {
            throw new IllegalValueException(Wage.MESSAGE_CONSTRAINTS);
        }
        final Wage itemWage = new Wage(wage);

        final Set<Requirement> itemRequirements = new HashSet<>();
        for (JsonAdaptedRequirement requirement : requirements) {
            itemRequirements.add(requirement.toModelType());
        }

        return new InternshipItem(itemCompanyName, itemJobTitle, itemPeriod, itemWage, itemRequirements);
    }

}
