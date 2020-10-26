package seedu.address.logic.commands.list;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProfileItemAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemList;
import seedu.address.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListProfileCommand.
 */
class ListProfileCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult commandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(), getSampleProfileItemList(),
                new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(),
                model.getUnfilteredProfileList(), new UserPrefs());
        commandResult = new CommandResult(ListProfileCommand.MESSAGE_SUCCESS, false, false, true, true);
    }

    @Test
    public void execute_listProfileIsNotFiltered_showsSameList() {
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company
        assertCommandSuccess(new ListProfileCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_listProfileIsFiltered_showsEverything() {
        showProfileItemAtIndex(model, INDEX_FIRST);
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company
        assertCommandSuccess(new ListProfileCommand(), model, commandResult, expectedModel);
    }
}
