package seedu.nextstep.parser;

import seedu.nextstep.command.Command;
import seedu.nextstep.command.AddCommand;
import seedu.nextstep.command.DeleteCommand;
import seedu.nextstep.command.ListCommand;
import seedu.nextstep.command.HelpCommand;
import seedu.nextstep.command.FindSkillCommand;
import seedu.nextstep.command.FindRoleCommand;
import seedu.nextstep.command.FindCompanyCommand;
import seedu.nextstep.command.FilterCommand;
import seedu.nextstep.command.EditCommand;

import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIntegerException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.SimilarCommandException;

import seedu.nextstep.core.InternshipList;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.storage.Storage;

/**
 * Handles processing user input and executes the appropriate command.
 */
public class Parser {
    private final InternshipList internships;
    private final Storage storage;

    public Parser(InternshipList internships, Storage storage) {
        this.internships = internships;
        this.storage = storage;
    }

    /**
     * Takes in user input, creates and executes the appropriate command.
     * @param input User input
     */
    public void processCommand(String input) {
        //@@author jiajun2002
        input = input.trim();
        if (input.isEmpty()) {
            System.out.println("Error: No command entered.");
            Ui.printLinebreak();
            return;
        }
        //@@author
        String[] words = input.split("\\s+",2 );
        String commandWord = words[0].toLowerCase();
        String remaining = words.length > 1 ? words[1] : "";
        String normalizedInput = commandWord + (remaining.isEmpty() ? "" :" " + remaining);
        try {
            Command command = createCommand(commandWord, normalizedInput);
            command.execute();
        } catch (EmptyInputException | InvalidInputFormatException |
                 InvalidIndexException | InvalidIntegerException e) {
            System.out.println(e.getMessage());
            Ui.printLinebreak();
        } catch (SimilarCommandException e) {
            Ui.printSimilarCommandError(words[0]);
        } catch (NumberFormatException e) {
            handleNumberFormatException(words[0]);
        } catch (IllegalArgumentException e) {
            Ui.printUnknownCommand();
        }
    }

    /**
     * Creates the correct command based on the commandWord.
     * @param commandWord The commandWord.
     * @param input User input.
     * @return The created command.
     * @throws SimilarCommandException if the commandWord does not exist but is similar to an existing command.
     */
    protected Command createCommand(String commandWord, String input) throws SimilarCommandException {
        return switch (commandWord) {
        case "add" -> new AddCommand(input, internships, storage);
        case "delete" -> new DeleteCommand(input, internships, storage);
        case "edit" -> new EditCommand(input, internships, storage);
        case "list" -> new ListCommand(internships);
        case "help" -> new HelpCommand();
        case "find/s" -> new FindSkillCommand(input, internships);
        case "find/r" -> new FindRoleCommand(input, internships);
        case "find/c" -> new FindCompanyCommand(input, internships);
        case "filter/a", "filter/d" -> new FilterCommand(input, internships);
        case "filter", "find" -> throw new SimilarCommandException();
        default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Handles NumberFormatExceptions thrown by various command executions.
     * @param commandWord
     */
    private void handleNumberFormatException(String commandWord) {
        if (commandWord.equals("add")) {
            Ui.showError("Error: Allowance/duration have to be integers.");
        } else if (commandWord.equals("delete") || commandWord.equals("edit")) {
            Ui.showError("Error: Index given must be an integer.");
        } else if (commandWord.startsWith("filter")) {
            Ui.showError("Error: Range given must be integers.");
        }
    }
}
