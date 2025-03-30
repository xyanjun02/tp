package seedu.nextstep.parser;

import java.util.Scanner;
import seedu.nextstep.command.Command;
import seedu.nextstep.command.AddCommand;
import seedu.nextstep.command.DeleteCommand;
import seedu.nextstep.command.ListCommand;
import seedu.nextstep.command.HelpCommand;
import seedu.nextstep.command.FindSkillCommand;
import seedu.nextstep.command.FindCompanyCommand;
import seedu.nextstep.command.FindRoleCommand;
import seedu.nextstep.command.FilterCommand;
import seedu.nextstep.command.EditCommand;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.storage.Storage;

/**
 * Handles processing user input and executes the appropriate command.
 */
public class Parser {
    private final InternshipList internships;
    private final Storage storage;
    private final Scanner scanner; // Shared scanner from NextStep

    public Parser(InternshipList internships, Storage storage, Scanner scanner) {
        this.internships = internships;
        this.storage = storage;
        this.scanner = scanner;
    }

    public void processCommand(String input) {
        String[] words = input.split(" ", 2); // Split into max 2 parts
        try {
            Command command = createCommand(words[0], input);
            command.execute();
        } catch (EmptyInputException | InvalidInputFormatException | InvalidIndexException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            handleNumberFormatException(words[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown command");
        } finally {
            System.out.flush();
        }
    }

    private Command createCommand(String commandWord, String input) {
        switch (commandWord) {
            case "add":
                return new AddCommand(input, internships, storage);
            case "delete":
                return new DeleteCommand(input, internships, storage);
            case "edit":
                // Pass the shared scanner to commands that require additional input.
                return new EditCommand(input, internships, storage, scanner);
            case "list":
                return new ListCommand(internships);
            case "help":
                return new HelpCommand();
            case "find/s":
                return new FindSkillCommand(input, internships);
            case "find/r":
                return new FindRoleCommand(input, internships);
            case "find/c":
                return new FindCompanyCommand(input, internships);
            case "filter/a":
            case "filter/d":
                return new FilterCommand(input, internships);
            case "filter":
            case "find":
                Ui.printSimilarCommandError(commandWord);
                throw new IllegalArgumentException();
            default:
                Ui.printUnknownCommand();
                throw new IllegalArgumentException();
        }
    }

    private void handleNumberFormatException(String commandWord) {
        if (commandWord.equals("add")) {
            Ui.showError("Sorry! Allowance/Duration must be integers!");
        } else if (commandWord.equals("delete") || commandWord.equals("edit")) {
            Ui.showError("Index given has to be an integer!");
        } else if (commandWord.startsWith("filter")) {
            Ui.showError("Range given must be integers!");
        }
    }
}
