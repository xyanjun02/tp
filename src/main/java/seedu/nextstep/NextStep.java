package seedu.nextstep;

import seedu.nextstep.command.*;
import seedu.nextstep.core.Internship;
import java.util.ArrayList;
import java.util.Scanner;

public class NextStep {
    public static ArrayList<Internship> internships = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Get Ready to begin your Next Step!!!");
        System.out.println("----------->>>>>");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Hope you get an internship dude...");
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
                case "find /s":
                    new FindSkillCommand(line).execute();
                    break;
                default:
                    System.out.println("Unknown command. Use 'delete <index>', or 'list'.");
            }
        }
        sc.close();
    }
}
