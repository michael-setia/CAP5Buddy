package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("te/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ZOOM_LINK = new Prefix("l/");
    public static final Prefix PREFIX_EDIT_NAME = new Prefix("e/");
    public static final Prefix PREFIX_ADD_NEW_ASSIGNMENT = new Prefix("a/");
    public static final Prefix PREFIX_PERCENTAGE_ASSIGNMENT = new Prefix("%/");
    public static final Prefix PREFIX_RESULT_ASSIGNMENT = new Prefix("r/");
    public static final Prefix PREFIX_MODULAR_CREDITS = new Prefix("mc/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");
}
