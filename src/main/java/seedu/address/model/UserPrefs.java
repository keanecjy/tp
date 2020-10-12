package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data", "addressbook.json");
    private Path applicationItemListFilePath = Paths.get("data", "applicationitemlist.json");
    private Path companyItemListFilePath = Paths.get("data", "companyitemlist.json");
    private Path profileItemListFilePath = Paths.get("data", "profileitemlist.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getApplicationItemListFilePath() {
        return applicationItemListFilePath;
    }

    public void setApplicationItemListFilePath(Path applicationItemListFilePath) {
        requireNonNull(applicationItemListFilePath);
        this.applicationItemListFilePath = applicationItemListFilePath;
    }

    public Path getCompanyItemListFilePath() {
        return companyItemListFilePath;
    }

    public void setCompanyItemListFilePath(Path companyItemListFilePath) {
        requireNonNull(companyItemListFilePath);
        this.companyItemListFilePath = companyItemListFilePath;
    }

    public Path getProfileItemListFilePath() {
        return profileItemListFilePath;
    }

    public void setProfileItemListFilePath(Path profileItemListFilePath) {
        requireNonNull(companyItemListFilePath);
        this.profileItemListFilePath = profileItemListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal data file location : " + addressBookFilePath;
    }

}
