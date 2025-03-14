package seedu.nextstep;

import java.util.ArrayList;
import java.util.Scanner;

public class NextStep {
    public static ArrayList<Internship> internships = new ArrayList<>();

    // add c/apple r/swe d/12 s/3000 pre/python sql c++
    public static void addInternship(String input) {
        String company = extractValue(input, "c/");
        String role = extractValue(input, "r/");
        String durationStr = extractValue(input, "d/");
        int duration = Integer.parseInt(durationStr);
        String salaryStr = extractValue(input, "s/");
        int salary = Integer.parseInt(salaryStr);
        String prerequisiteInput = extractValue(input, "pre/");
        String[] prereqs = prerequisiteInput.split(" ");
        String prereq1 = prereqs[0];
        String prereq2 = prereqs[1];
        String prereq3 = prereqs[2];

        Internship toAdd = new Internship(company, role, duration, salary, prereq1, prereq2, prereq3);
        internships.add(toAdd);
    }

    public static String extractValue(String input, String prefix) {
        int startIndex = input.indexOf(prefix) + prefix.length();
        int endIndex = input.indexOf(" ", startIndex);
        if (prefix.equals("pre/")) {
            return input.substring(startIndex);
        }
        return input.substring(startIndex, endIndex);
    }


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
                case "add":
                    addInternship(line);
                    System.out.println(internships.get(internships.size() - 1));
                    break;
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
