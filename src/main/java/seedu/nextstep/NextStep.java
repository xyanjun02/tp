package seedu.nextstep;

import seedu.nextstep.core.Internship;
import seedu.nextstep.parser.Parser;
import seedu.nextstep.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class NextStep {
    public static ArrayList<Internship> internships = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            Ui.printEnterCommand();
            System.out.flush(); // Ensure prompt is shown immediately
            String line = sc.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                Ui.printExitMessage();
                isRunning = false;
                continue;
            }

            Parser.processCommand(line);
        }
        sc.close();
    }
}
