package seedu.address.ui.panel;


import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.application.ApplicationItem;
import seedu.address.ui.cards.ApplicationCard;

/**
 * todo Javadocs
 */
public class ApplicationListPanel extends ListPanel<ApplicationItem> {

    /**
     * todo Javadocs
     */
    public ApplicationListPanel(ObservableList<ApplicationItem> applicationItemList) {
        super(applicationItemList);
        itemListView.setCellFactory(listView -> new ApplicationListViewCell());
    }

    /**
     * todo Javadocs
     */
    class ApplicationListViewCell extends ListCell<ApplicationItem> {
        @Override
        protected void updateItem(ApplicationItem applicationItem, boolean empty) {
            super.updateItem(applicationItem, empty);

            if (empty || applicationItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicationCard(applicationItem, getIndex() + 1).getRoot());
            }
        }
    }
}
