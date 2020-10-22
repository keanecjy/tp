package seedu.address.storage.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.item.ItemList;
import seedu.address.model.profile.ProfileItem;
import seedu.address.storage.JsonSerializableItemList;
import seedu.address.testutil.profile.SampleProfileItems;

public class JsonSerializableProfileItemListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableProfileItemListTest");
    private static final Path SAMPLE_PROFILE_ITEMS_FILE = TEST_DATA_FOLDER.resolve("sampleProfileItemList.json");
    private static final Path INVALID_PROFILE_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidProfileItemList.json");
    private static final Path DUPLICATE_PROFILE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateProfileItemList.json");

    @Test
    public void toModelType_sampleProfileItemsFile_success() throws Exception {
        JsonSerializableItemList<ProfileItem, JsonAdaptedProfileItem> dataFromFile = JsonUtil.readJsonFile(
                SAMPLE_PROFILE_ITEMS_FILE, ProfileItem.class, JsonAdaptedProfileItem.class).get();
        ItemList<ProfileItem> addressBookFromFile = dataFromFile.toModelType();
        ItemList<ProfileItem> sampleProfileItemListStorage = SampleProfileItems.getSampleProfileItemList();
        assertEquals(addressBookFromFile, sampleProfileItemListStorage);
    }

    @Test
    public void toModelType_invalidProfileItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<ProfileItem, JsonAdaptedProfileItem> dataFromFile = JsonUtil.readJsonFile(
                INVALID_PROFILE_ITEM_FILE, ProfileItem.class, JsonAdaptedProfileItem.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProfileItems_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<ProfileItem, JsonAdaptedProfileItem> dataFromFile = JsonUtil.readJsonFile(
                DUPLICATE_PROFILE_ITEM_FILE, ProfileItem.class, JsonAdaptedProfileItem.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableItemList.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }

}
