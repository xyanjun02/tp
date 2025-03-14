package seedu.nextstep;
import java.util.ArrayList;
import java.util.Scanner;

public class NextStep {
    public static ArrayList<Internship> internships = new ArrayList<>();

    public static void deleteInternship(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; // Convert to 0-based index
            if (index < 0 || index >= internships.size()) {
                System.out.println("Invalid index. Please enter a valid internship number.");
                return;
            }
            Internship removed = internships.remove(index);
            System.out.println("Deleted Internship: " + removed);
        } catch (Exception e) {
            System.out.println("Invalid input. Use: delete <index>");
        }
    }

    public static void listInternships() {
        if (internships.isEmpty()) {
            System.out.println("No internships available.");
            return;
        }
        System.out.println("Internship List:");
        for (int i = 0; i < internships.size(); i++) {
            System.out.println((i + 1) + ". " + internships.get(i));
        }
    }

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
                case "delete":
                    deleteInternship(line);
                    break;
                case "list":
                    listInternships();
                    break;
                default:
                    System.out.println("Unknown command. Use  'delete <index>', or 'list'.");
            }
        }
        sc.close();
    }
}

