package seedu.address.testutil.profile;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import seedu.address.model.profile.Descriptor;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemCategory;
import seedu.address.model.profile.Title;

/**
 * A utility class to help with building EditProfileItemDescriptor objects.
 */
public class EditProfileItemDescriptorBuilder {

    private EditProfileItemDescriptor descriptor;

    public EditProfileItemDescriptorBuilder() {
        descriptor = new EditProfileItemDescriptor();
    }

    public EditProfileItemDescriptorBuilder(EditProfileItemDescriptor descriptor) {
        this.descriptor = new EditProfileItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProfileItemDescriptor} with fields containing {@code profileItem}'s details
     */
    public EditProfileItemDescriptorBuilder(ProfileItem profileItem) {
        descriptor = new EditProfileItemDescriptor();
        descriptor.setTitle(profileItem.getTitle());
        descriptor.setProfileItemCategory(profileItem.getCategory());
        descriptor.setDescriptors(profileItem.getDescriptors());
    }

    /**
     * Sets the {@code ProfileItemCategory} of the {@code EditProfileItemDescriptor} that we are building.
     */
    public EditProfileItemDescriptorBuilder withProfileItemCategory(String category) {
        descriptor.setProfileItemCategory(ProfileItemCategory.valueOf(category.toUpperCase()));
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code EditProfileItemDescriptor} that we are building.
     */
    public EditProfileItemDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Parses the {@code descriptors} into a {@code Set<Descriptor>} and set it to the {@code EditProfileItemDescriptor}
     * that we are building.
     */
    public EditProfileItemDescriptorBuilder withDescriptors(String... descriptors) {
        Set<Descriptor> descriptorSet = Stream.of(descriptors).map(Descriptor::new).collect(Collectors.toSet());
        descriptor.setDescriptors(descriptorSet);
        return this;
    }

    public EditProfileItemDescriptor build() {
        return descriptor;
    }
}
