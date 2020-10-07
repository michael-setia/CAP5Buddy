package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddContactCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class AddContactParser extends Parser {

    /**
     * Parses a user input for an AddContactCommand.
     *
     * @param userInput User input.
     * @return AddContactCommand.
     * @throws ParseException If the user input could not be parsed.
     */
    public AddContactCommand parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(userInput, PrefixList.CONTACT_NAME_PREFIX, PrefixList.CONTACT_EMAIL_PREFIX);
        String[] parsedArguments = tokenizer.tokenize();
        try {
            String contactName = parsedArguments[0];
            String contactEmail = parsedArguments[1];
            return new AddContactCommand(contactName, contactEmail);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Missing arguments";
            throw new ParseException(error);
        }
    }
}