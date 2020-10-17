package seedu.address.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.edit.EditCompanyCommand.EditCompanyDescriptor;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.util.GeneralParserUtil.getIndexInPreamble;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditCommandAbstract;
import seedu.address.logic.commands.edit.EditCompanyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.CompanyParserUtil;
import seedu.address.model.company.Industry;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCompanyCommandParser implements Parser<EditCommandAbstract> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_INDUSTRY);

        Index index = getIndexInPreamble(argMultimap, EditCompanyCommand.MESSAGE_USAGE);

        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editCompanyDescriptor.setName(CompanyParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCompanyDescriptor.setPhone(CompanyParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCompanyDescriptor.setEmail(CompanyParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editCompanyDescriptor.setAddress(CompanyParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS)
                    .get()));
        }
        parseIndustriesForEdit(argMultimap.getAllValues(PREFIX_INDUSTRY))
                .ifPresent(editCompanyDescriptor::setIndustries);

        if (!editCompanyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCompanyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCompanyCommand(index, editCompanyDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Industry>> parseIndustriesForEdit(Collection<String> industries) throws ParseException {
        assert industries != null;

        if (industries.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> industrySet = industries.size() == 1 && industries.contains("")
                ? Collections.emptySet()
                : industries;
        return Optional.of(CompanyParserUtil.parseIndustries(industrySet));
    }

}
