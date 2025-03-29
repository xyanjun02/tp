package seedu.nextstep.parser;

import seedu.nextstep.command.*;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.ui.Ui;

import java.text.NumberFormat;

/**
 * Handles processing user input and executes the appropriate command.
 */
public class Parser {

    public static void processCommand(String input) {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "add":
            try {
                new AddCommand(input).execute();
            } catch (EmptyInputException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Sorry! Allowance/Duration must be integers!");
            }
            break;
        case "delete":
            try {
                new DeleteCommand(input).execute();
            } catch (EmptyInputException | InvalidIndexException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Index given has to be an integer!");
            }
            break;
        case "edit":
            try {
                new EditCommand(input).execute();
            } catch (EmptyInputException | InvalidIndexException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Index given has to be an integer!");
            }
            break;
        case "list":
            new ListCommand().execute();
            break;
        case "help":
            new HelpCommand().execute();
            break;
        case "find/s":
            try {
                new FindSkillCommand(input).execute();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "find/r":
            try {
                new FindRoleCommand(input).execute();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "find/c":
            try {
                new FindCompanyCommand(input).execute();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "filter/a":
        case "filter/d":
            try {
                new FilterCommand(input).execute();
            } catch (EmptyInputException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Range given must be integers!");
            }
            break;
        case "filter":
        case "find":
            Ui.printSimilarCommandError(words[0]);
            break;
        default:
            Ui.printUnknownCommand();
            break;
        }
    }
}
