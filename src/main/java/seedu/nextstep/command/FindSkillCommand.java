package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;

public class FindSkillCommand {
    private String input;

    public FindSkillCommand(String input) {
        this.input = input;
    }

    public void execute() {
        String skillset = input.split(" /s ")[1];
        System.out.println("Searching for internships with skillset: " + skillset);
        for (Internship internship : NextStep.internships) {
            for (String skill : internship.getSkills()) {
                if (skill.equalsIgnoreCase(skillset)) {
                    System.out.println(internship);
                    break;
                }
            }
        }
    }
}
