package seedu.nextstep.parser;

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
import seedu.nextstep.exception.SimilarCommandException;
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

    public void processCommand(String input) {
        String[] words = input.split(" ");
        try {
            Command command = createCommand(words[0], input);
            command.execute();
        } catch (EmptyInputException | InvalidInputFormatException | InvalidIndexException e) {
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

    private Command createCommand(String commandWord, String input) throws SimilarCommandException {
        switch (commandWord) {
            case "add":
                return new AddCommand(input, internships, storage);
            case "delete":
                return new DeleteCommand(input, internships, storage);
            case "edit":
                return new EditCommand(input, internships, storage);
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
                throw new SimilarCommandException();
            default:
                throw new IllegalArgumentException();
        }
    }

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
