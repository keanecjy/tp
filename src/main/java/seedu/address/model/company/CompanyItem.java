package seedu.address.model.company;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.util.ItemUtil.COMPANY_NAME;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.item.Item;

/**
 * Represents a Person in the address book. todo javadocs (shawn)
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CompanyItem extends Item {

    // Identity fields
    private final CompanyName companyName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Industry> industries = new HashSet<>();
    private final List<InternshipItem> internships = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public CompanyItem(CompanyName companyName, Phone phone, Email email, Address address, Set<Industry> industries) {
        requireAllNonNull(companyName, phone, email, address, industries);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.industries.addAll(industries);
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Industry> getIndustries() {
        return Collections.unmodifiableSet(industries);
    }

    /**
     * NOTE: May end up deleting this to prevent direct access to internship list. todo javadocs (shawn)
     */
    public List<InternshipItem> getInternships() {
        return Collections.unmodifiableList(internships);
    }

    /** todo javadocs (shawn) */
    public InternshipItem getInternship(Index internshipIndex) throws CommandException {
        if (internshipIndex.getZeroBased() >= internships.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, INTERNSHIP_NAME));
        }
        return internships.get(internshipIndex.getZeroBased());
    }

    /** todo javadocs (shawn) */
    public void addInternship(InternshipItem internship) {
        internships.add(internship);
    }

    /**
     * Returns true if both persons of the same companyName have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameItem(Item otherItem) {
        if (this == otherItem) {
            return true;
        }

        if (!(otherItem instanceof CompanyItem)) {
            return false;
        }

        CompanyItem otherCompanyItem = (CompanyItem) otherItem;

        return otherCompanyItem != null
                && otherCompanyItem.getCompanyName().equals(getCompanyName())
                && (otherCompanyItem.getPhone().equals(getPhone()) || otherCompanyItem.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CompanyItem)) {
            return false;
        }

        CompanyItem otherCompanyItem = (CompanyItem) other;
        return otherCompanyItem.getCompanyName().equals(getCompanyName())
                && otherCompanyItem.getPhone().equals(getPhone())
                && otherCompanyItem.getEmail().equals(getEmail())
                && otherCompanyItem.getAddress().equals(getAddress())
                && otherCompanyItem.getIndustries().equals(getIndustries())
                && otherCompanyItem.getInternships().equals(getInternships());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, phone, email, address, industries, internships);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        if (!industries.isEmpty()) {
            builder.append(" Industries: ");
            getIndustries().forEach(industry -> builder.append(industry + " "));
        }
        if (!internships.isEmpty()) {
            builder.append("Internships: ");
            getInternships().forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public String getItemName() {
        return COMPANY_NAME;
    }

    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("Company name", companyName);
        mapping.put("Phone", phone);
        mapping.put("Email", email);
        mapping.put("Address", address);
        mapping.put("Industries", industries);
        mapping.put("Internships", internships);
        return mapping;
    }

}
