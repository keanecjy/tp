package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.tabs.TabName;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getInternHunterFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setInternHunterFilePath(Path internHunterFilePath);

    /**
     * Gets the person list manager.
     */
    FilterableItemList<Person> getAddressBook();

    /**
     * Gets the company list manager.
     */
    FilterableItemList<CompanyItem> getCompanyList();

    /**
     * Gets the application list manager.
     */
    FilterableItemList<ApplicationItem> getApplicationList();

    /**
     * Get profile list manager.
     */
    FilterableItemList<ProfileItem> getProfileList();

    /**
     * Replaces the current tab name with {@code tabName}.
     */
    void setTabName(TabName tabName);

    /**
     * Retrieves the current tab name
     */
    TabName getTabName();

    /**
     * Replaces the current index with {@code index}.
     */
    void setViewIndex(Index index);

    /**
     * Retrieves the current view Index.
     */
    Index getViewIndex();
}
