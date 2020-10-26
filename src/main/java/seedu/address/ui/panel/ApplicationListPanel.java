package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.application.ApplicationItem;
import seedu.address.ui.cards.ApplicationCard;

/**
 * A UI component that contains all the information of {@code ApplicationItem} and displays it
 * as a scrollable list of cards.
 */
public class ApplicationListPanel extends ListPanel<ApplicationItem> {

    /**
     * Creates a scrollable list panel with information of {@code ApplicationItem}.
     *
     * @param applicationItemList List containing all the application item in the storage.
     */
    public ApplicationListPanel(ObservableList<ApplicationItem> applicationItemList) {
        super(applicationItemList);
        itemListView.setCellFactory(listView -> new ApplicationListViewCell());
    }

    /**
     * Creates each cell in the list panel with information of {@code ApplicationItem}.
     */
    static class ApplicationListViewCell extends ListCell<ApplicationItem> {
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
