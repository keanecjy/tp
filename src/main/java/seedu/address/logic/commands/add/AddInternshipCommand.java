package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.address.logic.commands.util.CommandUtil.getCompany;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_NAME;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.internship.JobTitle;
import seedu.address.model.internship.Period;
import seedu.address.model.internship.Requirement;
import seedu.address.model.internship.Wage;
import seedu.address.ui.tabs.TabName;

public class AddInternshipCommand extends AddCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + INTERNSHIP_ALIAS
            + ": Adds an " + INTERNSHIP_NAME + " to InternHunter.\n"
            + "Parameters: "
            + "INDEX "
            + PREFIX_JOB_TITLE + "JOB_TITLE "
            // Todo: Update Wage when its status is resolved.
            + PREFIX_WAGE + "WAGE "
            + "[" + PREFIX_PERIOD + "PERIOD] "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Note: Select a company to add an internship to using INDEX.\n"
            + "Example: " + COMMAND_WORD + " " + INTERNSHIP_ALIAS
            + " 1 "
            + PREFIX_JOB_TITLE + "Software Engineer "
            + PREFIX_WAGE + "3000 "
            + PREFIX_PERIOD + "3 months "
            + PREFIX_REQUIREMENT + "React "
            + PREFIX_REQUIREMENT + "Vue ";

    public static final String MESSAGE_DUPLICATE_ITEM = "This %1$s already exists in %2$s";

    private final Index companyIndex;
    private final JobTitle jobTitle;
    private final Wage wage;
    private final Period period;
    private final Set<Requirement> requirements;

    /**
     * Creates an AddCommand to add the specified {@code Internship}.
     */
    public AddInternshipCommand(Index companyIndex, JobTitle jobTitle,
            Wage wage, Period period, Set<Requirement> requirements) {
        requireAllNonNull(companyIndex, jobTitle, wage, period, requirements);
        this.companyIndex = companyIndex;
        this.jobTitle = jobTitle;
        this.wage = wage;
        this.period = period;
        this.requirements = requirements;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CompanyItem companyItem = getCompany(model, companyIndex);
        InternshipItem internshipItem = new InternshipItem(companyItem.getCompanyName(), jobTitle, period, wage,
                requirements);

        if (companyItem.containsInternship(internshipItem)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM,
                    INTERNSHIP_NAME, companyItem.getCompanyName()));
        }

        companyItem.addInternship(internshipItem);
        model.setCompanyViewIndex(companyIndex);
        String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, INTERNSHIP_NAME, internshipItem);
        return getCommandResult(model, addSuccessMessage, TabName.COMPANY);
    }

}
