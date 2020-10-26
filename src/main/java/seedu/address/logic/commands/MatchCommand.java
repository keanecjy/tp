package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemCategory;

public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String SHOWING_MATCH_COMMAND_MESSAGE = "Displaying list of matching internships.";
    public static final String NO_MATCHING_INTERNSHIPS_MESSAGE = "There are no internships that matches your "
            + "current skill set";

    @Override
    public CommandResult execute(Model model) {
        List<ProfileItem> profileItemList = model.getProfileItemList();
        List<CompanyItem> companyItemList = model.getCompanyItemList();
        List<String> currentSkillList = getSkillList(profileItemList);
        List<InternshipItem> fullInternshipList = getInternshipList(companyItemList);
        List<InternshipItem> matchingInternships = getMatchingInternships(fullInternshipList, currentSkillList);
        return getMatchingInternshipsCommandResult(matchingInternships);
    }

    private List<String> getSkillList(List<ProfileItem> profileItemList) {
        return profileItemList.stream()
                .filter(profileItem -> profileItem.getCategory() == ProfileItemCategory.SKILL)
                .map(ProfileItem::getTitleValue)
                .collect(Collectors.toList());
    }

    private List<InternshipItem> getInternshipList(List<CompanyItem> companyItemList) {
        List<InternshipItem> internshipItems = new ArrayList<>();
        companyItemList.stream().map(CompanyItem::getInternships).forEach(internshipItems::addAll);
        return internshipItems;
    }

    private List<InternshipItem> getMatchingInternships(List<InternshipItem> internshipItemList,
            List<String> skillList) {
        return internshipItemList
                .stream()
                .filter(internshipItem -> internshipItem.matches(skillList))
                .collect(Collectors.toList());
    }

    private CommandResult getMatchingInternshipsCommandResult(List<InternshipItem> matchingInternships) {
        if (matchingInternships.isEmpty()) {
            return new CommandResult(NO_MATCHING_INTERNSHIPS_MESSAGE);
        }
        CommandResult commandResult = new CommandResult(SHOWING_MATCH_COMMAND_MESSAGE);
        commandResult.setMatchingInternships(FXCollections.observableArrayList(matchingInternships));
        return commandResult;
    }

}
