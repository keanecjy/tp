package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.company.CompanyName;
import seedu.address.model.item.Item;
import seedu.address.storage.internship.JsonAdaptedInternshipItem;

/**
 * Represents an InternshipItem in the InternHunter application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipItem extends Item {

    // Identity fields
    private CompanyName companyName;
    private final JobTitle jobTitle;
    private final Period period;

    // Data fields
    private final Wage wage;
    private final Set<Requirement> requirements = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public InternshipItem(CompanyName companyName, JobTitle jobTitle, Period period, Wage wage,
            Set<Requirement> requirements) {
        requireAllNonNull(companyName, jobTitle, period, wage, requirements);
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.period = period;
        this.wage = wage;
        this.requirements.addAll(requirements);
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public Period getPeriod() {
        return period;
    }

    public Wage getWage() {
        return wage;
    }

    /**
     * Returns an immutable requirement set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Obtains the name of the item.
     *
     * @return Item name.
     */
    @Override
    public String getItemName() {
        return INTERNSHIP_NAME;
    }

    /**
     * Obtains the mapping of all field names to their corresponding fields in an InternshipItem object.
     *
     * @return Mapping of field names to fields for the InternshipItem.
     */
    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = new LinkedHashMap<>();
        mapping.put(JOB_TITLE_DISPLAY_NAME, jobTitle);
        mapping.put(COMPANY_DISPLAY_NAME, companyName);
        mapping.put(PERIOD_DISPLAY_NAME, period);
        mapping.put(WAGE_DISPLAY_NAME, wage);
        mapping.put(REQUIREMENTS_DISPLAY_NAME, requirements);
        return mapping;
    }

    /**
     * Returns true if both InternshipItems have the same jobTitle and period.
     * This defines a weaker notion of equality between two InternshipItems.
     *
     * @param otherItem Other InternShipItem to compare to.
     * @return True if and only if the 2 InternshipItems have the same identity fields.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof InternshipItem)) {
            return false;
        }

        InternshipItem otherInternshipItem = (InternshipItem) otherItem;
        return otherInternshipItem.getCompanyName().equals(getCompanyName())
                && otherInternshipItem.getJobTitle().equals(getJobTitle())
                && otherInternshipItem.getPeriod().equals(getPeriod());
    }

    /**
     * Returns true if both InternshipItems have the same identity and data fields.
     * This defines a stronger notion of equality between two InternshipItems.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipItem)) {
            return false;
        }

        InternshipItem otherInternshipItem = (InternshipItem) other;
        return otherInternshipItem.getCompanyName().equals(getCompanyName())
                && otherInternshipItem.getJobTitle().equals(getJobTitle())
                && otherInternshipItem.getPeriod().equals(getPeriod())
                && otherInternshipItem.getWage().equals(getWage())
                && otherInternshipItem.getRequirements().equals(getRequirements());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, jobTitle, period, wage, requirements);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getJobTitle())
                .append(", ")
                .append(" Company Name: ")
                .append(getCompanyName())
                .append(", ")
                .append(" Period: ")
                .append(getPeriod())
                .append(", ")
                .append(" Wage: ")
                .append(getWage())
                .append(", ")
                .append(" Requirements: ");
        getRequirements().forEach(builder::append);
        return builder.toString();
    }

    @Override
    public JsonAdaptedInternshipItem getJsonAdaptedItem() {
        return new JsonAdaptedInternshipItem(this);
    }

}
