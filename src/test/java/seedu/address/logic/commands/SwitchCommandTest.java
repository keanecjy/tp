package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.tabs.TabName;

public class SwitchCommandTest {

    // Default screen is Internship.
    public static final String SAME_SCREEN_TEXT = "Already in Company tab";
    public static final String SWITCHED_SCREEN_TEXT_PROFILE = "Switching to Profile tab";
    public static final String SWITCHED_SCREEN_TEXT_APPLICATION = "Switching to Application tab";

    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_switchToProfileTab_success() {
        CommandResult expectedCommandResult = new CommandResult(SWITCHED_SCREEN_TEXT_PROFILE, false, false,
            true, false);
        assertCommandSuccess(new SwitchCommand(TabName.PROFILE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchToCompanyTab_success() {
        CommandResult expectedCommandResult = new CommandResult(SAME_SCREEN_TEXT, false,
            false, true, false);
        assertCommandSuccess(new SwitchCommand(TabName.COMPANY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchToApplicationTab_success() {
        CommandResult expectedCommandResult = new CommandResult(SWITCHED_SCREEN_TEXT_APPLICATION, false, false,
            true, false);
        assertCommandSuccess(new SwitchCommand(TabName.APPLICATION), model, expectedCommandResult, expectedModel);
    }
}
