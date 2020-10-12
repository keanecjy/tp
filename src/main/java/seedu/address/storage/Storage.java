package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.person.Person;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.storage.company.JsonAdaptedCompanyItem;
import seedu.address.storage.person.JsonAdaptedPerson;
import seedu.address.storage.profile.JsonAdaptedProfileItem;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Gets the address book storage.
     *
     * @return address book storage.
     */
    ListStorage<Person, JsonAdaptedPerson> getAddressBookStorage();

    /**
     * Gets the application item list storage.
     *
     * @return application item list storage.
     */
    ListStorage<ApplicationItem, JsonAdaptedApplicationItem> getApplicationItemListStorage();

    /**
     * Gets the company item list storage.
     *
     * @return company item list storage.
     */
    ListStorage<CompanyItem, JsonAdaptedCompanyItem> getCompanyItemListStorage();

    /**
     * Gets the profile item list storage.
     *
     * @return profile item list storage.
     */
    ListStorage<ProfileItem, JsonAdaptedProfileItem> getProfileItemListStorage();

}
