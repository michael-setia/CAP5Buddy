package seedu.address.logic.commands.todolistcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deletetodo";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
