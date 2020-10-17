package seedu.address.testutil.profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.item.ItemList;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemCategory;

/**
 * A utility class containing a list of {@code ProfileItem} objects to be used in tests.
 */
public abstract class SampleProfileItems {

    public static final ProfileItem HTML_SKILL = new ProfileItemBuilder()
            .withTitle("HTML")
            .withCategory(ProfileItemCategory.SKILL)
            .withDescriptors("Learn to create divs.", "learn to create tables.")
            .build();

    public static final ProfileItem HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle("Won second place in Code Jam")
            .withCategory(ProfileItemCategory.ACHIEVEMENT)
            .withDescriptors("Solved the Board Meeting Question.", "Solved the Jungle Struggle question.")
            .build();

    public static final ProfileItem GOVTECH_EXPERIENCE = new ProfileItemBuilder()
            .withTitle("Internship with Govtech")
            .withCategory(ProfileItemCategory.EXPERIENCE)
            .withDescriptors("Implemented DevOps solution to speed up automated testing by 50%")
            .withDescriptors("Developed dashboard to keep track of key performance metrics")
            .withDescriptors("Build internal tooling for syncing data with remote servers")
            .build();

    public static final ProfileItem GRAPHQL_SKILL = new ProfileItemBuilder()
            .withTitle("GraphQL")
            .withCategory(ProfileItemCategory.SKILL)
            .build();

    public static final ProfileItem NUS_MODS_EXPERIENCE = new ProfileItemBuilder()
            .withTitle("Contribute to NUSMODS")
            .withCategory(ProfileItemCategory.EXPERIENCE)
            .withDescriptors("Fix UI bugs in Modules View")
            .build();

    public static final ProfileItem MS_HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle("Participation in MS OpenHack")
            .withCategory(ProfileItemCategory.ACHIEVEMENT)
            .withDescriptors("Solve the Agriculture challenge with satellite imagery")
            .build();

    /**
     * Returns an {@code AddressBook} with all the sample profile items.
     */
    public static ItemList<ProfileItem> getSampleProfileItemList() {
        ItemList<ProfileItem> profileItemList = new ItemList<>();
        for (ProfileItem profileItem : getProfileItems()) {
            profileItemList.addItem(profileItem);
        }
        return profileItemList;
    }

    private static List<ProfileItem> getProfileItems() {
        return new ArrayList<>(Arrays.asList(HTML_SKILL, HACKATHON_ACHIEVEMENT, GOVTECH_EXPERIENCE,
                GRAPHQL_SKILL, NUS_MODS_EXPERIENCE, MS_HACKATHON_ACHIEVEMENT));
    }
}
