package seedu.nextstep.ui;

import seedu.nextstep.core.Internship;

public class Ui {
    private static final String LINEBREAK = "____________________________________";

    public static void printLinebreak() {
        System.out.println(LINEBREAK);
    }

    /**
     * Prints welcome message when NextStep starts.
     */
    public static void printWelcomeMessage() {
        printLinebreak();
        System.out.println("Welcome to NextStep, your personal internship tracking assistant! (0_0)");
        System.out.println("What would you like to do today? [Type 'help' to see commands]");
        printLinebreak();
    }

    /**
     * Prints message to prompt user input
     */
    public static void printEnterCommand() {
        System.out.print("Enter command: ");
    }

    public static void printUnknownCommand() {
        System.out.println("Unknown command. Type help for more info. (o.o)");
        printLinebreak();
    }

    /**
     * Prints exit message when NextStep exits.
     */
    public static void printExitMessage() {
        printLinebreak();
        System.out.println("Thank you for using NextStep, good luck on your search! (0_0)");
        printLinebreak();
    }

    public static void showError(String message) {
        System.out.println(message);
        printLinebreak();
    }

    /**
     * Prints a message if user types filter/find without the tags.
     * @param input The wrong command.
     */
    public static void printSimilarCommandError(String input) {
        if (input.equals("filter")) {
            System.out.println("No command found.! Perhaps you mean filter/a or filter/d?");
        } else if (input.equals("find")) {
            System.out.println("No command found. Perhaps you mean find/c, find/r, or find/s?");
        }
        printLinebreak();
    }

    /**
     * Prints a message when an internship is successfully added.
     * @param internship Internship to be added.
     */
    public static void printAddingMessage(Internship internship) {
        printLinebreak();
        System.out.println("New internship added below! (0_0)");
        System.out.println(internship);
    }

    /**
     * Prints a message when an internship is successfully deleted.
     * @param internship Internship to be deleted.
     */
    public static void printDeleteSuccess(Internship internship) {
        printLinebreak();
        System.out.println("The Following Internship is Deleted:");
        System.out.println(internship);
    }

    /**
     * Prints a message to ask for the internship to be edited.
     */
    public static void printEditMessage() {
        printLinebreak();
        System.out.print("What would you like to edit? (separated by commas): ");
    }

    /**
     * Prints a message when an internship is successfully edited.
     * @param internship Internship to be edited.
     */
    public static void printEditSuccess(Internship internship) {
        printLinebreak();
        System.out.println("Successfully edited the following Internship:");
        System.out.println(internship);
    }

    public static void printSearchingForSkill(String skill) {
        printLinebreak();
        System.out.println("Searching for internships with skill: " + skill);
    }

    public static void printNoInternshipFound(String skill) {
        printLinebreak();
        System.out.println("No internships found with skill: " + skill);
        printLinebreak();
    }

    public static void printInternship(Internship internship) {
        System.out.println(internship);
    }

    public static void printSearchingForRole(String role) {
        printLinebreak();
        System.out.println("Searching for internships with role: " + role);
    }

    public static void printNoInternshipFoundForRole(String role) {
        printLinebreak();
        System.out.println("No internships found with role: " + role);
        printLinebreak();
    }

    public static void printSearchingForCompany(String company) {
        printLinebreak();
        System.out.println("Searching for internships with company: " + company);
    }

    public static void printNoInternshipFoundForCompany(String company) {
        printLinebreak();
        System.out.println("No internships found with company: " + company);
        printLinebreak();
    }

    public static void printNoFilteredInternshipFound() {
        System.out.println("No internships found here!");
        Ui.printLinebreak();
    }

    public static void printMessage(String string) {
        System.out.println(string);
    }
}

