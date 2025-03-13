package seedu.nextstep;

import java.util.Scanner;

public class NextStep {
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);
        System.out.println("Get Ready to begin your Next Step!!!");
        System.out.println("----------->>>>>");
        Internship internship = new Internship("Apple",
                                            "Software Engineer",
                                            6,
                                            3000,
                                            "Python",
                                            "SQL",
                                            "C++");
        System.out.println(internship);
    }
}
