package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.logic.commands.util.CommandUtil.getInternshipFromCompany;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FilterableItemList;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;
import seedu.address.model.internship.InternshipItem;
import seedu.address.ui.tabs.TabName;

/**
 * Adds an application to the Application list.
 */
public class AddApplicationCommand extends AddCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + APPLICATION_ALIAS + ": Adds an application to "
            + "InternHunter.\nParameters: "
            + "INDEX "
            + PREFIX_INDEX + "INDEX "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_STATUS_DATE + "STATUS_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "2 "
            + PREFIX_STATUS + "waiting "
            + PREFIX_STATUS_DATE + "23-12-20";

    public static final String MESSAGE_SUCCESS = "New application added: %s\n"
            + "Type in 'switch app' to see the newly added application!";

    private final Index companyIndex;
    private final Index internshipIndex;
    private final Status status;
    private final StatusDate statusDate;

    /**
     * Creates an AddApplicationCommand to add the specified {@code ApplicationItem}.
     */
    public AddApplicationCommand(Index companyIndex, Index internshipIndex, Status status, StatusDate statusDate) {
        requireAllNonNull(companyIndex, internshipIndex, status, statusDate);
        this.companyIndex = companyIndex;
        this.internshipIndex = internshipIndex;
        this.status = status;
        this.statusDate = statusDate;
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
        ApplicationItem applicationItem = new ApplicationItem(internshipItem, status, statusDate);

        FilterableItemList<ApplicationItem> applicationList = model.getApplicationList();

        if (applicationList.hasItem(applicationItem)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, APPLICATION_NAME));
        }

        applicationList.addItem(applicationItem);
        return getCommandResult(model, String.format(MESSAGE_SUCCESS, applicationItem),
                TabName.APPLICATION);
    }

    @Override
    public String getItemType() {
        return APPLICATION_NAME;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApplicationCommand)) {
            return false;
        }

        AddApplicationCommand otherAddApplicationCommand = (AddApplicationCommand) other;
        return companyIndex.equals(otherAddApplicationCommand.companyIndex)
                && internshipIndex.equals(otherAddApplicationCommand.internshipIndex)
                && status.equals(otherAddApplicationCommand.status)
                && statusDate.equals(otherAddApplicationCommand.statusDate);
    }

}
