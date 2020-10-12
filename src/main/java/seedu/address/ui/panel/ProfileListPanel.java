package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.cards.ProfileCard;

/**
 * todo Javadocs
 */
public class ProfileListPanel extends ListPanel<ProfileItem> {

    /**
     * todo Javadocs
     */
    public ProfileListPanel(ObservableList<ProfileItem> categoryList) {
        super(categoryList);
        itemListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * todo Javadocs
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
