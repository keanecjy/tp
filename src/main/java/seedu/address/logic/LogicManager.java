package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MainParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.item.ReadOnlyItemList;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.Storage;
import seedu.address.ui.tabs.TabName;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MainParser mainParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        mainParser = new MainParser();

    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = mainParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCompanyItemList(model.getCompanyList().getUnfilteredItemList());
            storage.saveApplicationItemList(model.getApplicationList().getUnfilteredItemList());
            storage.saveProfileItemList(model.getProfileList().getUnfilteredItemList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    // todo: remove when possible
    @Override
    public ReadOnlyItemList<Person> getAddressBook() {
        return model.getAddressBook().getUnfilteredItemList();
    }

    // todo: remove when possible
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getAddressBook().getFilteredItemList();
    }

    @Override
    public ObservableList<ApplicationItem> getFilteredApplicationItemList() {
        return model.getFilteredApplicationList();
    }

    @Override
    public ObservableList<CompanyItem> getFilteredCompanyItemList() {
        return model.getFilteredCompanyList();
    }

    @Override
    public ObservableList<ProfileItem> getFilteredProfileItemList() {
        return model.getFilteredProfileList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getInternHunterFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public TabName getTabName() {
        return model.getTabName();
    }

    @Override
    public void setTabName(TabName tabName) {
        model.setTabName(tabName);
    }

    @Override
    public Index getCompanyViewIndex() {
        return model.getCompanyViewIndex();
    }

    @Override
    public Index getApplicationViewIndex() {
        return model.getApplicationViewIndex();
    }

    @Override
    public Index getProfileViewIndex() {
        return model.getProfileViewIndex();
    }

    @Override
    public void setCompanyViewIndex(Index index) {
        model.setCompanyViewIndex(index);
    }

    @Override
    public void setApplicationViewIndex(Index index) {
        model.setApplicationViewIndex(index);
    }

    @Override
    public void setProfileViewIndex(Index index) {
        model.setProfileViewIndex(index);
    }
}
