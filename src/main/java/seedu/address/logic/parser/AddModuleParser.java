package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddModuleParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        Module module;
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_ZOOM_LINK, PREFIX_TAG,
                PREFIX_MODULAR_CREDITS);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }
        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        ModularCredits modularCredits;
        if (arePrefixesPresent(argMultimap, PREFIX_MODULAR_CREDITS)
                && argMultimap.getPreamble().isEmpty()) {
            modularCredits = ParserUtil.parseModularCredits(argMultimap.getValue(PREFIX_MODULAR_CREDITS).get());
        } else {
            modularCredits = new ModularCredits();
        }
        if (argMultimap.getValue(PREFIX_ZOOM_LINK).isPresent()) {
            ZoomLink zoomLink = ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_ZOOM_LINK).get());
            module = new Module(moduleName, zoomLink, tagList, modularCredits);
        } else {
            module = new Module(moduleName, tagList, modularCredits);
        }


        // return new AddCommand(person);
        return new AddModuleCommand(module);

        //Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        //Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        //Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        //Person person = new Person(name, email, tagList);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
