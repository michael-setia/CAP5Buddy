package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.commands.AddGradeCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddZoomLinkCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewModuleCommand;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.parser.contactlistparsers.AddContactParser;
import seedu.address.logic.parser.contactlistparsers.DeleteContactParser;
import seedu.address.logic.parser.contactlistparsers.EditContactParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ModuleListParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleParser().parse(arguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddZoomLinkCommand.COMMAND_WORD:
            return new AddZoomLinkParser().parse(arguments);

        case ViewModuleCommand.COMMAND_WORD:
            return new ViewModuleParser().parse(arguments);

        case AddAssignmentCommand.COMMAND_WORD:
            return new AddAssignmentParser().parse(arguments);

        case FindContactCommand.COMMAND_WORD:
            return new FindContactParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactParser().parse(arguments);

        case AddContactCommand.COMMAND_WORD:
            return new AddContactParser().parse(arguments);

        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
