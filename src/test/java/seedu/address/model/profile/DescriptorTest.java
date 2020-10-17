package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DescriptorTest {
    static final String BLANK_DESCRIPTOR = "";
    static final String VALID_DESCRIPTOR = "Implement a automated tool to speed up testing by 20%";
    static final String INVALID_DESCRIPTOR = "Wrote documentation (with User Guide included)";
    static final String INVALID_DESCRIPTOR_TWO = "Deploy a bot @ telegram";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Descriptor(null));
    }

    @Test
    public void constructor_invalidDescriptor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(INVALID_DESCRIPTOR));
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(INVALID_DESCRIPTOR_TWO));
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(BLANK_DESCRIPTOR));
    }

    @Test
    public void toString_validFormats_success() {
        Descriptor descriptor = new Descriptor(VALID_DESCRIPTOR);
        assertEquals(VALID_DESCRIPTOR, descriptor.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        Descriptor descriptorOne = new Descriptor(VALID_DESCRIPTOR);
        Descriptor descriptorTwo = new Descriptor(VALID_DESCRIPTOR);
        assertEquals(descriptorOne, descriptorTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        Descriptor descriptorOne = new Descriptor(VALID_DESCRIPTOR);
        Descriptor descriptorTwo = new Descriptor(VALID_DESCRIPTOR);
        assertEquals(descriptorOne.hashCode(), descriptorTwo.hashCode());
    }
}
