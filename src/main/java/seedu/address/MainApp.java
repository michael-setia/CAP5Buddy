package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.storage.ContactListStorage;
import seedu.address.storage.JsonContactListStorage;
import seedu.address.storage.JsonModuleListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.ModuleListStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ModuleList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ModuleListStorage moduleListStorage = new JsonModuleListStorage(userPrefs.getModuleListFilePath());
        ContactListStorage contactListStorage = new JsonContactListStorage(userPrefs.getContactListFilePath());
        storage = new StorageManager(moduleListStorage, contactListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s module list, contact list
     * and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyModuleList> moduleListOptional;
        ReadOnlyModuleList initialData;
        ReadOnlyContactList initialContactList = initializeContactList(storage);
        ReadOnlyTodoList initialTodoList = new TodoList();
        try {
            moduleListOptional = storage.readModuleList();
            if (!moduleListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ModuleList");
                initialData = new ModuleList();
            } else {
                initialData = moduleListOptional.get();
            }
            //to be used when sample modulelist is created
            //initialData = moduleListOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ModuleList");
            initialData = new ModuleList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ModuleList");
            initialData = new ModuleList();
        }
        return new ModelManager(initialData, initialContactList, initialTodoList, userPrefs);
    }

    /**
     * Returns a {@code ReadOnlyContactList} with the data from {@code storage}'s contact list.
     *
     * @param storage Storage object containing contact list data.
     * @return ReadOnlyContactList containing the contacts from the Storage argument.
     */
    private ReadOnlyContactList initializeContactList(Storage storage) {
        Optional<ReadOnlyContactList> contactListOptional;
        ReadOnlyContactList initialContactList;
        try {
            contactListOptional = storage.readContactList();
            if (!contactListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ContactList");
                initialContactList = new ContactList();
            } else {
                initialContactList = contactListOptional.get();
            }
        } catch (DataConversionException ex) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ContactList");
            initialContactList = new ContactList();
        } catch (IOException ex) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ModuleList");
            initialContactList = new ContactList();
        }
        return initialContactList;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
