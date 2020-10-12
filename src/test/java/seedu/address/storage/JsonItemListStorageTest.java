package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.item.ItemList;
import seedu.address.model.item.ReadOnlyItemList;
import seedu.address.model.person.Person;
import seedu.address.storage.person.JsonAdaptedPerson;

public class JsonItemListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyItemList<Person>> readAddressBook(String filePath) throws Exception {
        return new JsonItemListStorage<>(Paths.get(filePath),
                Person.class, JsonAdaptedPerson.class).readItemList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ItemList<Person> original = getTypicalAddressBook();
        JsonItemListStorage<Person, JsonAdaptedPerson> jsonItemListStorage =
                new JsonItemListStorage(filePath, Person.class, JsonAdaptedPerson.class);

        // Save in new file and read back
        jsonItemListStorage.saveItemList(original, filePath);
        ReadOnlyItemList<Person> readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(HOON);
        original.removeItem(ALICE);
        jsonItemListStorage.saveItemList(original, filePath);
        readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Save and read without specifying file path
        original.addItem(IDA);
        jsonItemListStorage.saveItemList(original); // file path not specified
        readBack = jsonItemListStorage.readItemList().get(); // file path not specified
        assertEquals(original, new ItemList<>(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ItemList<Person> addressBook, String filePath) {
        try {
            new JsonItemListStorage<>(Paths.get(filePath), Person.class,
                    JsonAdaptedPerson.class).saveItemList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new ItemList<>(), null));
    }
}
