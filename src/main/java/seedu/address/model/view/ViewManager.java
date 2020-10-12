package seedu.address.model.view;

import seedu.address.commons.core.index.Index;

/**
 * todo javadoc
 */
public class ViewManager implements View {

    private Index index;

    /**
     * Initializes ViewManger which controls the current {@code index}. By default, it is always of Index 1.
     */
    public ViewManager() {
        index = Index.fromOneBased(1); // default Index is 1 which is 0 ?
    }

    /**
     * Replaces {@code this.index} with {@code index}.
     */
    public void setViewIndex(Index index) {
        this.index = index;
    }

    /**
     * Retrieves the current {@code index}.
     */
    public Index getViewIndex() {
        return index;
    }

}
