package seedu.address.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.address.model.company.CompanyItem;
import seedu.address.ui.cards.CompanyCard;

/**
 * todo Javadocs
 */
public class CompanyListPanel extends ListPanel<CompanyItem> {

    /**
     * todo Javadocs
     */
    public CompanyListPanel(ObservableList<CompanyItem> companyItemList) {
        super(companyItemList);
        itemListView.setCellFactory(listView -> new CompanyListViewCell());
    }

    /**
     * todo Javadocs
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
