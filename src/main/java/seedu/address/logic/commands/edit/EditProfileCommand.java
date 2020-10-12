package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getProfileItem;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTORS;
import static seedu.address.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.address.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.util.ItemUtil.PROFILE_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.Descriptor;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemCategory;
import seedu.address.model.profile.Title;

public class EditProfileCommand extends EditCommandAbstract {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the profile item identified "
            + "by the index number used in the displayed profile list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_DESCRIPTORS + "DESCRIPTOR]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "achievement "
            + PREFIX_DESCRIPTORS + "Devised a mobile transaction solution. ";


    private final Index targetIndex;
    private final EditProfileItemDescriptor editProfileItemDescriptor;

    /**
     * @param index of the profileItem in the filtered profile item list to edit
     * @param editProfileItemDescriptor details to edit the profile item with
     */
    public EditProfileCommand(Index index, EditProfileItemDescriptor editProfileItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editProfileItemDescriptor);

        this.targetIndex = index;
        this.editProfileItemDescriptor = new EditProfileItemDescriptor(editProfileItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ProfileItem profileItemToEdit = getProfileItem(model, targetIndex);
        ProfileItem editedProfile = createEditedProfileItem(profileItemToEdit, editProfileItemDescriptor);

        if (!profileItemToEdit.isSameItem(editedProfile) && model.getProfileList().hasItem(editedProfile)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_ITEM, PROFILE_NAME));
        }

        model.getProfileList().setItem(profileItemToEdit, editedProfile);
        model.getProfileList().updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(Messages.MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfile));
    }

    /**
     * Creates and returns a {@code ProfileItem} with the details of {@code profileItemToEdit}
     * edited with {@code editProfileItemDescriptor}.
     */
    private static ProfileItem createEditedProfileItem(ProfileItem profileItemToEdit,
        EditProfileItemDescriptor editProfileItemDescriptor) {
        assert profileItemToEdit != null;

        Title updatedTitle = editProfileItemDescriptor.getTitle()
            .orElse(profileItemToEdit.getTitle());
        ProfileItemCategory updatedCategory = editProfileItemDescriptor
            .getProfileItemCategory().orElse(profileItemToEdit.getCategory());
        Set<Descriptor> updatedDescriptor = editProfileItemDescriptor
            .getDescriptors().orElse(profileItemToEdit.getDescriptors());

        return new ProfileItem(updatedTitle, updatedCategory, updatedDescriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditProfileCommand)) {
            return false;
        }

        // state check
        EditProfileCommand e = (EditProfileCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editProfileItemDescriptor.equals(e.editProfileItemDescriptor);
    }

    /**
     * Stores the details to edit the profile item with. Each non-empty field value will replace the
     * corresponding field value of the profile item.
     */
    public static class EditProfileItemDescriptor {
        private Title title;
        private ProfileItemCategory profileItemCategory;
        private Set<Descriptor> descriptors;

        public EditProfileItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code descriptors} is used internally.
         */
        public EditProfileItemDescriptor(EditProfileItemDescriptor toCopy) {
            setTitle(toCopy.title);
            setProfileItemCategory(toCopy.profileItemCategory);
            setDescriptors(toCopy.descriptors);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return isAnyNonNull(title, profileItemCategory, descriptors);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }


        public void setProfileItemCategory(ProfileItemCategory cat) {
            this.profileItemCategory = cat;
        }

        public Optional<ProfileItemCategory> getProfileItemCategory() {
            return Optional.ofNullable(profileItemCategory);
        }

        /**
         * Sets {@code descriptors} to this object's {@code descriptors}.
         * A defensive copy of {@code descriptors} is used internally.
         */
        public void setDescriptors(Set<Descriptor> descriptors) {
            this.descriptors = (descriptors != null) ? new HashSet<>(descriptors) : null;
        }

        /**
         * Returns an unmodifiable descriptor set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code descriptors} is null.
         */
        public Optional<Set<Descriptor>> getDescriptors() {
            return (descriptors != null) ? Optional.of(Collections.unmodifiableSet(descriptors)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProfileItemDescriptor)) {
                return false;
            }

            // state check
            EditProfileItemDescriptor e = (EditProfileItemDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getProfileItemCategory().equals(e.getProfileItemCategory())
                    && getDescriptors().equals(e.getDescriptors());
        }
    }
}
