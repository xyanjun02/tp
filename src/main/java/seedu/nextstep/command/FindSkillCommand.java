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
                System.out.println("Error: Please specify a skillset after '/s'.");
                return;
            }

            String skillset = parts[1].trim();
            System.out.println("Searching for internships with skillset: " + skillset);
            boolean found = false;

            for (Internship internship : NextStep.internships) {
                for (String skill : internship.getSkills()) {
                    if (skill.equalsIgnoreCase(skillset)) {
                        System.out.println(internship);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("No internships found with skill: " + skillset);
            }

        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
