package seedu.address.testutil.application;

import seedu.address.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;
import seedu.address.model.util.DateUtil;

/**
 * A utility class to help with building EditApplicationDescriptor objects.
 */
public class EditApplicationDescriptorBuilder {

    private EditApplicationDescriptor descriptor;

    public EditApplicationDescriptorBuilder() {
        descriptor = new EditApplicationDescriptor();
    }

    public EditApplicationDescriptorBuilder(EditApplicationDescriptor descriptor) {
        this.descriptor = new EditApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicationDescriptor} with fields containing {@code applicationItem}'s details
     */
    public EditApplicationDescriptorBuilder(ApplicationItem applicationItem) {
        descriptor = new EditApplicationDescriptor();
        descriptor.setStatus(applicationItem.getStatus());
        descriptor.setStatusDate(applicationItem.getStatusDate());
    }

    /**
     * Sets the {@code Status} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.valueOf(status.toUpperCase()));
        return this;
    }

    /**
     * Sets the {@code StatusDate} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatusDate(String statusDate) {
        descriptor.setStatusDate(new StatusDate(DateUtil.convertToDateTime(statusDate)));
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
