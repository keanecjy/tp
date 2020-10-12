package seedu.address.logic.commands.edit;

import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.commands.Command;

/**
 * todo javadocs
 */
public abstract class EditCommandAbstract extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of an item from InternHunter accessed "
            + "by the index number used in the displayed list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters (for ITEM_TYPE "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "', "
            + "'" + PROFILE_ALIAS + "'): "
            + "ITEM_TYPE INDEX DETAILS\n"
            + "Parameters (for ITEM_TYPE "
            + "'" + INTERNSHIP_ALIAS + "'): "
            + "ITEM_TYPE INDEX " + PREFIX_INDEX + "INDEX DETAILS\n"
            + "Note: Valid ITEM_TYPEs are "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + INTERNSHIP_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "' or "
            + "'" + PROFILE_ALIAS + "'. "
            + "INDEX must be a positive integer. "
            + "Each ITEM_TYPE requires its own set of DETAILS.\n"
            + "Example: " + COMMAND_WORD + " " + PROFILE_ALIAS + " 3 DETAILS";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
}
