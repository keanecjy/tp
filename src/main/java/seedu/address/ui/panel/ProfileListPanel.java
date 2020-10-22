package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.cards.ProfileCard;

/**
 * A UI component that contains all the information of {@code ProfileItem} and displays it
 * as a scrollable list of cards.
 */
public class ProfileListPanel extends ListPanel<ProfileItem> {

    /**
     * Creates a scrollable list panel with information of {@code ProfileItem}.
     *
     * @param profileItemsList List containing all the profile item in the storage.
     */
    public ProfileListPanel(ObservableList<ProfileItem> profileItemsList) {
        super(profileItemsList);
        itemListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * Creates each cell in the list panel with information of {@code ProfileItem}.
     */
    class ProfileListViewCell extends ListCell<ProfileItem> {

        protected void updateItem(ProfileItem profileItem, boolean empty) {
            super.updateItem(profileItem, empty);

            if (empty || profileItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileCard(profileItem, getIndex() + 1).getRoot());
            }
        }
    }
}
