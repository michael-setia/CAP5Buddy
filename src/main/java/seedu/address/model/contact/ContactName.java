package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's name in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class ContactName {

    public static final String MESSAGE_CONSTRAINTS =
            "Contact Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the contact name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /** String describing the contact name. */
    private final String fullName;

    /**
     * Constructs a {@code ContactName}.
     *
     * @param name A valid contact name.
     */
    public ContactName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Determines if a given string is a valid contact name.
     *
     * @param test A given String to test.
     * @return True if the given string is a valid contact name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactName // instanceof handles nulls
                && fullName.equals(((ContactName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
