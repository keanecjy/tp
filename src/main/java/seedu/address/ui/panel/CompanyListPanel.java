package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.cards.CompanyCard;

/**
 * A UI component that contains all the information of {@code CompanyItem} and displays it
 * as a scrollable list of cards.
 */
public class CompanyListPanel extends ListPanel<CompanyItem> {

    /**
     * Creates a scrollable list panel with information of {@code CompanyItem}.
     *
     * @param companyItemList List containing all the company item in the storage.
     */
    public CompanyListPanel(ObservableList<CompanyItem> companyItemList) {
        super(companyItemList);
        itemListView.setCellFactory(listView -> new CompanyListViewCell());
    }

    /**
     * Creates each cell in the list panel with information of {@code CompanyItem}.
     */
    class CompanyListViewCell extends ListCell<CompanyItem> {
        @Override
        protected void updateItem(CompanyItem companyItem, boolean empty) {
            super.updateItem(companyItem, empty);

            if (empty || companyItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompanyCard(companyItem, getIndex() + 1).getRoot());
            }
        }
    }
}
