package seedu.address.ui.cards;

import static seedu.address.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;

import javafx.scene.image.Image;
import javafx.scene.transform.Translate;
import seedu.address.model.profile.ProfileItem;

/**
 * todo Javadocs
 */
public class ProfileCard extends Card<ProfileItem> {

    //Image Link
    private static final String ACHIEVEMENT_IMAGE_LINK = "/images/achievement.png";
    private static final String EXPERIENCE_IMAGE_LINK = "/images/experience.png";
    private static final String SKILLS_IMAGE_LINK = "/images/skills.png";

    //FXML properties
    private static final int IMAGE_HEIGHT_WIDTH = 60;
    private static final int IMAGE_TRANSLATE_Y = -25;

    /**
     * todo Javadocs
     */
    public ProfileCard(ProfileItem profileItem, int displayedIndex) {
        super(profileItem, displayedIndex);
        initializeUserCardGui();
    }

    /**
     * todo Javadocs
     */
    private void initializeUserCardGui() {
        setId(displayedIndex);
        setName();
        setImageType();
    }

    /**
     * todo Javadocs
     */
    protected void setName() {
        Object title = mapping.get(TITLE_DISPLAY_NAME);
        setName(title.toString());
    }

    /**
     * todo Javadocs
     */
    protected void setImageType() {
        Image image;
        switch (getItem().getCategory()) {
        case ACHIEVEMENT:
            image = new Image(this.getClass().getResourceAsStream(ACHIEVEMENT_IMAGE_LINK));
            break;
        case EXPERIENCE:
            image = new Image(this.getClass().getResourceAsStream(EXPERIENCE_IMAGE_LINK));
            break;
        case SKILL:
            image = new Image(this.getClass().getResourceAsStream(SKILLS_IMAGE_LINK));
            break;
        default:
            image = null;
            assert false : "NO SUCH TYPE EXISTS";
            break;
        }
        setImageStyling();
        imageView.setImage(image);
    }

    /**
     * todo Javadocs
     */
    private void setImageStyling() {
        imageView.setFitHeight(IMAGE_HEIGHT_WIDTH);
        imageView.setFitWidth(IMAGE_HEIGHT_WIDTH);
        Translate translate = new Translate();
        translate.setY(IMAGE_TRANSLATE_Y);
        imageView.getTransforms().addAll(translate);
    }
}
