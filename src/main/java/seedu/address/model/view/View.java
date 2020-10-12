package seedu.address.model.view;

import seedu.address.commons.core.index.Index;

/**
 * The API of the Tab component.
 */
public interface View {

    /**
     * Replaces the current {@code Index} name with {@code index}.
     */
    void setViewIndex(Index index);

    /**
     * Retrieves the current Index.
     */
    Index getViewIndex();

}
