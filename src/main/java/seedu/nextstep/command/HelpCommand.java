package seedu.nextstep.command;

public class HelpCommand {

    public void execute() {
        // Show the help instructions for the commands
        System.out.println("Do help yourself to these commands:");
        System.out.println("add [n/COMPANY_NAME] [r/ROLE] [s/SKILLSETS] [l/LOCATION] [d/DEADLINE] - Adds a new internship");
        System.out.println("delete [INDEX] - Deletes an internship at the specified index");
        System.out.println("list - Lists all available internships");
        System.out.println("find /s [SKILLSET] - Finds internships with specific skillset");
        System.out.println("bye - Exit the application");
    }
}
