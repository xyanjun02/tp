package seedu.nextstep;

import seedu.nextstep.core.InternshipList;
import seedu.nextstep.parser.Parser;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

import java.util.Scanner;

public class NextStep {
    private final InternshipList internships;
    private final Storage storage;
    private final Parser parser;

    public NextStep() {
        this.storage = new Storage();
        this.internships = storage.load();
        this.parser = new Parser(internships, storage);
    }

    public void run() {
        boolean isRunning = true;
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            Ui.printEnterCommand();
            String line = sc.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                storage.save(internships);
                Ui.printExitMessage();
                isRunning = false;
                continue;
            }

            parser.processCommand(line);
        }
        sc.close();
    }

    public static void main(String[] args) {
        new NextStep().run();
    }
}
