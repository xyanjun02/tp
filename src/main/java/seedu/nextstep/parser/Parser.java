package seedu.nextstep.parser;

import seedu.nextstep.command.AddCommand;
import seedu.nextstep.command.DeleteCommand;
import seedu.nextstep.command.ListCommand;
import seedu.nextstep.command.HelpCommand;
import seedu.nextstep.command.FindSkillCommand;
import seedu.nextstep.command.FindCompanyCommand;
import seedu.nextstep.command.FindRoleCommand;
/**
 * Handles processing user input and executes the appropriate command.
 */
public class Parser {

    public static void processCommand(String input) {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "add":
            new AddCommand(input).execute();
            break;
        case "delete":
            new DeleteCommand(input).execute();
            break;
        case "list":
            new ListCommand().execute();
            break;
        case "help":
            new HelpCommand().execute();
            break;
        case "find/s":
            new FindSkillCommand(input).execute();
            break;
        case "find/r":
            new FindRoleCommand(input).execute();
            break;
        case "find/c":
            new FindCompanyCommand(input).execute();
            break;
        default:
            System.out.println("Unknown command... Type help for more information :v");
            break;
        }
    }
}
