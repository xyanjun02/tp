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
            }
        }

//        System.out.println("Get Ready to begin your Next Step!!!");
//        System.out.println("----------->>>>>");
//        Internship internship = new Internship("Apple",
//                                            "Software Engineer",
//                                            6,
//                                            3000,
//                                            "Python",
//                                            "SQL",
//                                            "C++");
//        System.out.println(internship);
    }
}
