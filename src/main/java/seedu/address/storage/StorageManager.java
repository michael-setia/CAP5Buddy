package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleListStorage moduleListStorage;
    private ContactListStorage contactListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModuleListStorage},
     * {@code ContactListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleListStorage moduleListStorage, ContactListStorage contactListStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.moduleListStorage = moduleListStorage;
        this.contactListStorage = contactListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ModuleList methods ==============================

    @Override
    public Path getModuleListFilePath() {
        return moduleListStorage.getModuleListFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleListFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, moduleListStorage.getModuleListFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleListStorage.saveModuleList(moduleList, filePath);
    }

    // ================ ContactList methods ==============================
    @Override
    public Path getContactListFilePath() {
        return contactListStorage.getContactListFilePath();
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException {
        return readContactList(contactListStorage.getContactListFilePath());
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactListStorage.readContactList(filePath);
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList) throws IOException {
        saveContactList(contactList, contactListStorage.getContactListFilePath());
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactListStorage.saveContactList(contactList, filePath);
    }
}
