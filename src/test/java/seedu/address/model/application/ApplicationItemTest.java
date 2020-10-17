package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.GeneralStringUtil.COMMA_WITH_SPACE;
import static seedu.address.commons.util.NumberUtil.NUMBER_FIVE;
import static seedu.address.commons.util.NumberUtil.NUMBER_SEVEN;
import static seedu.address.model.util.ApplicationItemUtil.DATE_OUTPUT_NAME;
import static seedu.address.model.util.ApplicationItemUtil.STATUS_OUTPUT_NAME;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.address.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.address.testutil.application.ApplicationItemFieldsUtil.EXPECTED_DATE_JUNE_2021;
import static seedu.address.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;
import static seedu.address.testutil.application.ApplicationItemFieldsUtil.STATUS_REJECTED;
import static seedu.address.testutil.application.SampleApplicationItems.LAZADA_REJECTED;
import static seedu.address.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.address.testutil.internship.SampleInternshipItems.GOLDMAN_DA;
import static seedu.address.testutil.internship.SampleInternshipItems.SHOPEE_SWE;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import seedu.address.storage.application.JsonAdaptedApplicationItem;
import seedu.address.testutil.application.ApplicationItemBuilder;

public class ApplicationItemTest {

    @Test
    public void getItemName_equalityTest_success() {
        assertEquals(APPLICATION_NAME, SHOPEE_OFFERED.getItemName());
    }

    @Test
    public void getMapping_size_success() {
        LinkedHashMap<String, Object> mapping = SHOPEE_OFFERED.getMapping();
        assertEquals(NUMBER_SEVEN, mapping.size());
    }

    @Test
    public void getMapping_correctOrdering_success() {
        LinkedHashMap<String, Object> mapping = SHOPEE_OFFERED.getMapping();
        Iterator<Object> fields = mapping.values().iterator();
        assertEquals(SHOPEE_SWE.getJobTitle(), fields.next());
        assertEquals(SHOPEE_SWE.getCompanyName(), fields.next());
        assertEquals(SHOPEE_SWE.getPeriod(), fields.next());
        assertEquals(SHOPEE_SWE.getWage(), fields.next());
        assertEquals(SHOPEE_SWE.getRequirements(), fields.next());
        assertEquals(SHOPEE_OFFERED.getStatus(), fields.next());
        assertEquals(SHOPEE_OFFERED.getStatusDate(), fields.next());
    }

    @Test
    public void isSameItem_true_success() {
        // same object -> returns true
        assertTrue(SHOPEE_OFFERED.isSameItem(SHOPEE_OFFERED));

        ApplicationItem editedShopeeOffered;

        // different object, same values -> returns true
        editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED).build();
        assertTrue(SHOPEE_OFFERED.isSameItem(editedShopeeOffered));

        // different status, everything else the same -> returns true
        editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED).withStatus(STATUS_REJECTED).build();
        assertTrue(SHOPEE_OFFERED.isSameItem(editedShopeeOffered));

        // different status date, everything else the same -> returns true
        editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED).withStatusDate(STATUS_DATE_JUNE_2022).build();
        assertTrue(SHOPEE_OFFERED.isSameItem(editedShopeeOffered));
    }

    @Test
    public void isSameItem_defaultApplicationTest_success() {
        ApplicationItem defaultApplicationItem = new ApplicationItem(SHOPEE_SWE);
        assertTrue(SHOPEE_OFFERED.isSameItem(defaultApplicationItem));
    }

    @Test
    public void isSameItem_false_success() {
        // null -> returns false
        assertFalse(SHOPEE_OFFERED.isSameItem(null));

        // Two different applications -> returns false
        assertFalse(SHOPEE_OFFERED.isSameItem(LAZADA_REJECTED));
        assertFalse(SHOPEE_OFFERED.isSameItem(SHOPEE_SWE));

        // different internship -> returns false
        ApplicationItem editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED)
                .withInternshipItem(GOLDMAN_DA).build();
        assertFalse(SHOPEE_OFFERED.isSameItem(editedShopeeOffered));
    }

    @Test
    public void equals_true_success() {
        // same object -> returns true
        assertEquals(SHOPEE_OFFERED, SHOPEE_OFFERED);

        // different object, same values -> returns true
        ApplicationItem shopeeOfferedCopy = new ApplicationItemBuilder(SHOPEE_OFFERED).build();
        assertEquals(shopeeOfferedCopy, SHOPEE_OFFERED);
    }

    @Test
    public void equals_false_success() {
        // null -> returns false
        assertNotEquals(SHOPEE_OFFERED, null);

        // different type -> returns false
        assertNotEquals(SHOPEE_OFFERED, NUMBER_FIVE);

        // different items -> returns false
        assertNotEquals(SHOPEE_OFFERED, SHOPEE_SWE);

        // different applicationItem -> returns false
        assertNotEquals(LAZADA_REJECTED, SHOPEE_OFFERED);

        // different internshipItem -> returns false
        ApplicationItem editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED)
                .withInternshipItem(GOLDMAN_DA).build();
        assertNotEquals(editedShopeeOffered, SHOPEE_OFFERED);

        // different status -> returns false
        editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED).withStatus(STATUS_REJECTED).build();
        assertNotEquals(editedShopeeOffered, SHOPEE_OFFERED);

        // different status date -> returns false
        editedShopeeOffered = new ApplicationItemBuilder(SHOPEE_OFFERED).withStatusDate(STATUS_DATE_JUNE_2022).build();
        assertNotEquals(editedShopeeOffered, SHOPEE_OFFERED);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(SHOPEE_OFFERED.hashCode(), SHOPEE_OFFERED.hashCode());
        ApplicationItem shopeeOfferedCopy = new ApplicationItemBuilder(SHOPEE_OFFERED).build();
        assertEquals(SHOPEE_OFFERED.hashCode(), shopeeOfferedCopy.hashCode());
    }

    @Test
    public void toString_equalityTest_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(SHOPEE_OFFERED.getInternshipItem())
                .append(STATUS_OUTPUT_NAME)
                .append(OFFERED_KEYWORD)
                .append(COMMA_WITH_SPACE)
                .append(DATE_OUTPUT_NAME)
                .append(EXPECTED_DATE_JUNE_2021);
        assertEquals(builder.toString(), SHOPEE_OFFERED.toString());
    }

    @Test
    public void getJsonAdaptedItem_nonEqualityTest_success() {
        assertNotEquals(new JsonAdaptedApplicationItem(SHOPEE_OFFERED), SHOPEE_OFFERED.getJsonAdaptedItem());
    }

}
