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
    private final Scanner sharedScanner;

    public NextStep() {
        this.storage = new Storage();
        this.internships = storage.load();
        // Create one shared Scanner instance for the entire application.
        this.sharedScanner = new Scanner(System.in);
        this.parser = new Parser(internships, storage, sharedScanner);
    }

    public void run() {
        boolean isRunning = true;
        Ui.printWelcomeMessage();
        while (isRunning) {
            try {
                // Display the prompt and flush to ensure it appears immediately
                Ui.printEnterCommand();
                System.out.flush();

                // Wait for user input
                String input = sharedScanner.nextLine().trim();
                System.out.println("[DEBUG] Received input: " + input);

                if (input.equalsIgnoreCase("bye")) {
                    storage.save(internships);
                    Ui.printExitMessage();
                    isRunning = false;
                    continue;
                }
                // Process the command (exceptions are caught in Parser)
                parser.processCommand(input);
            } catch (Exception e) {
                // Catch any unexpected exceptions so the program doesn't halt
                System.err.println("Unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
            // Optionally, a debug message here can reassure that the loop is ready for a new command.
            System.out.println("[DEBUG] Ready for next command.");
        }
        // Close the scanner only when exiting
        sharedScanner.close();
    }

    public static void main(String[] args) {
        new NextStep().run();
    }
}
