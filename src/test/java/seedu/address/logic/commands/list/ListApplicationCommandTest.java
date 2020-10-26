package seedu.address.logic.commands.list;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemList;
import seedu.address.ui.tabs.TabName;

public class ListApplicationCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult commandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
            new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(),
            model.getUnfilteredApplicationList(), new ItemList<>(), new UserPrefs());
        commandResult = new CommandResult(ListApplicationCommand.MESSAGE_SUCCESS, false, false, true, true);
    }

    @Test
    public void execute_listApplicationIsNotFiltered_showsSameList() {
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company
        assertCommandSuccess(new ListApplicationCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_listApplicationIsFiltered_showsEverything() {
        showApplicationAtIndex(model, INDEX_FIRST);
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company
        assertCommandSuccess(new ListApplicationCommand(), model, commandResult, expectedModel);
    }
}
