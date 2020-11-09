package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonContactListStorage;
import seedu.address.storage.JsonEventListStorage;
import seedu.address.storage.JsonModuleListStorage;
import seedu.address.storage.JsonTodoListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ModuleBuilder;



public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonModuleListStorage moduleListStorage =
                new JsonModuleListStorage(temporaryFolder.resolve("moduleList.json"));
        JsonModuleListStorage archivedModuleListStorage =
                new JsonModuleListStorage(temporaryFolder.resolve("archivedModuleList.json"));
        JsonContactListStorage contactListStorage =
                new JsonContactListStorage(temporaryFolder.resolve("contactList.json"));
        JsonTodoListStorage todoListStorage =
                new JsonTodoListStorage(temporaryFolder.resolve("todoList.json"));
        JsonEventListStorage eventListStorage =
                new JsonEventListStorage(temporaryFolder.resolve("eventList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(moduleListStorage, archivedModuleListStorage, contactListStorage,
                todoListStorage, eventListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "deletemodule 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListModuleCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListModuleCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        //Setup LogicManager with JsonModuleListIoExceptionThrowingStub
        JsonModuleListStorage moduleListStorage =
                new JsonModuleListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionModuleList.json"));
        JsonModuleListStorage archivedModuleListStorage =
                new JsonModuleListIoExceptionThrowingStub(temporaryFolder
                        .resolve("ioExceptionArchivedModuleList.json"));
        JsonContactListStorage contactListStorage =
                new JsonContactListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionContactList.json"));
        JsonTodoListStorage todoListStorage =
                new JsonTodoListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTodoList.json"));
        JsonEventListStorage eventListStorage =
                new JsonEventListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionEventList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(moduleListStorage, archivedModuleListStorage,
                contactListStorage, todoListStorage, eventListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addModuleCommand = AddModuleCommand.COMMAND_WORD + NAME_DESC_CS2103T;
        Module expectedModule = new ModuleBuilder(CS2103T).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addModule(expectedModule);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addModuleCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredModuleList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method for JsonModuleListStorage is called.
     */
    private static class JsonModuleListIoExceptionThrowingStub extends JsonModuleListStorage {
        private JsonModuleListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method for JsonContactListStorage is called.
     */
    private static class JsonContactListIoExceptionThrowingStub extends JsonContactListStorage {
        private JsonContactListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method for JsonTodoListStorage is called.
     */
    private static class JsonTodoListIoExceptionThrowingStub extends JsonTodoListStorage {
        private JsonTodoListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method for JsonEventListStorage is called.
     */
    private static class JsonEventListIoExceptionThrowingStub extends JsonEventListStorage {
        private JsonEventListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveEventList(ReadOnlyEventList eventList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
