package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
// import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonModuleListStorage implements ModuleListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleListStorage.class);

    private Path filePath;

    public JsonModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException {
        return readModuleList(filePath);
    }

    /**
     * Similar to {@link #readModuleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleList> jsonModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleList.class);
        if (!jsonModuleList.isPresent()) {
            return Optional.empty();
        }
        //temp only until return null error is resolved
        return Optional.empty();
        //return null;
        // try {
        //    return Optional.of(jsonAddressBook.get().toModelType());
        // } catch (IllegalValueException ive) {
        //     logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
        //    throw new DataConversionException(ive);
        // }
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    /**
     * Similar to {@link #saveModuleList(ReadOnlyModuleList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(moduleList), filePath);
    }

}
