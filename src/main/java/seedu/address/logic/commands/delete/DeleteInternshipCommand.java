package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.logic.commands.util.CommandUtil.getInternshipFromCompany;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.ui.tabs.TabName;

/**
 * Deletes an internship from the internship list.
 */
public class DeleteInternshipCommand extends DeleteCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the internship identified by the"
            + "index number used in the displayed internship list in a company."
            + "The application (if any) made with this internship will also be deleted. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "2";

    private final Index companyIndex;
    private final Index internshipIndex;

    /**
     * Creates an DeleteInternshipCommand to delete the specified {@code InternshipItem}.
     */
    public DeleteInternshipCommand(Index companyIndex, Index internshipIndex) {
        requireAllNonNull(companyIndex, internshipIndex);
        this.companyIndex = companyIndex;
        this.internshipIndex = internshipIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        InternshipItem internshipItem = getInternshipFromCompany(model, companyIndex, internshipIndex);

        // Delete applications for this deleted internship
        ApplicationItem applicationItemToDelete = new ApplicationItem(internshipItem);
        model.getApplicationList().deleteSameItem(applicationItemToDelete);

        return getCommandResult(model, String.format(Messages.MESSAGE_DELETED_ITEM, INTERNSHIP_NAME, internshipItem),
                TabName.COMPANY);
    }

    @Override
    public String getItemType() {
        return INTERNSHIP_NAME;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInternshipCommand)) {
            return false;
        }

        DeleteInternshipCommand otherDeleteInternshipCommand = (DeleteInternshipCommand) other;
        return companyIndex.equals(otherDeleteInternshipCommand.companyIndex)
                && internshipIndex.equals(otherDeleteInternshipCommand.internshipIndex);
    }

}
