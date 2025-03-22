package seedu.nextstep;

import seedu.nextstep.command.AddCommand;
import seedu.nextstep.command.DeleteCommand;
import seedu.nextstep.command.ListCommand;
import seedu.nextstep.command.HelpCommand;
import seedu.nextstep.command.FindSkillCommand;
import seedu.nextstep.core.Internship;
import seedu.nextstep.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class NextStep {
    public static ArrayList<Internship> internships = new ArrayList<>();

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            System.out.flush(); // Ensure prompt is shown immediately
            String line = sc.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                Ui.printExitMessage();
                break;
            }

            String[] words = line.split(" ");
            switch (words[0]) {
            case "add":
                new AddCommand(line).execute();
                break;
            case "delete":
                new DeleteCommand(line).execute();
                break;
            case "list":
                new ListCommand().execute();
                break;
            case "help":
                new HelpCommand().execute();
                break;
            case "find/s":
                new FindSkillCommand(line).execute();
                break;
            default:
                System.out.println("Unknown command. Type help for more information.");
                break;
            }
        }
        sc.close();
    }
}
