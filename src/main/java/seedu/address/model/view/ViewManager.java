package seedu.address.model.view;

import seedu.address.commons.core.index.Index;

/**
 * todo javadoc
 */
public class ViewManager implements View {

    private Index companyIndex;
    private Index applicationIndex;
    private Index profileIndex;

    /**
     * Initializes ViewManger which controls the current {@code index}. By default, it is always of Index 1.
     */
    public ViewManager() {
        companyIndex = Index.fromOneBased(1); // default Index is 1 which is 0
        applicationIndex = Index.fromOneBased(1); // default Index is 1 which is 0
        profileIndex = Index.fromOneBased(1); // default Index is 1 which is 0
    }

    /**
     * Replaces {@code companyIndex} with {@code index}.
     */
    public void setCompanyViewIndex(Index index) {
        companyIndex = index;
    }

    /**
     * Replaces {@code companyIndex} with {@code index}.
     */
    public void setApplicationViewIndex(Index index) {
        applicationIndex = index;
    }

    /**
     * Replaces {@code companyIndex} with {@code index}.
     */
    public void setProfileViewIndex(Index index) {
        profileIndex = index;
    }

    /**
     * Retrieves the current {@code index}.
     */
    public Index getCompanyViewIndex() {
        return companyIndex;
    }

    /**
     * Retrieves the current {@code index}.
     */
    public Index getApplicationViewIndex() {
        return applicationIndex;
    }

    /**
     * Retrieves the current {@code index}.
     */
    public Index getProfileViewIndex() {
        return profileIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ViewManager)) {
            return false;
        }

        ViewManager other = (ViewManager) obj;
        return companyIndex.equals(other.companyIndex)
                && applicationIndex.equals(other.applicationIndex)
                && profileIndex.equals(other.profileIndex);
    }
}
