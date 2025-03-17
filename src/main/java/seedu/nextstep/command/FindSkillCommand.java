package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;

public class FindSkillCommand {
    private final String input;

    public FindSkillCommand(String input) {
        this.input = input;
    }

    public void execute() {
        try {
            // Split input correctly and trim to handle extra spaces
            String[] parts = input.split("find/s");

            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                System.out.println("Error: Please specify a skill after '/s'.");
                return;
            }

            String skill = parts[1].trim();
            System.out.println("Searching for internships with skill: " + skill);
            boolean found = false;

            for (Internship internship : NextStep.internships) {
                for (String s : internship.getSkills()) {
                    if (s.equalsIgnoreCase(skill)) {
                        System.out.println(internship);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("No internships found with skill: " + skill);
            }

        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
