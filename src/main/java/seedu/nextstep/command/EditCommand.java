package seedu.nextstep.command;

import java.util.List;
import java.util.Scanner;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;

import static seedu.nextstep.ui.Ui.printEditSuccess;

public class EditCommand extends Command {
    private int index;
    private String[] fieldsToEdit;

    /**
     * Constructor for EditCommand.
     *
     * @param input The user input, which includes the index and the fields to edit.
     */
    public EditCommand(String input) {
        super(input);
    }

    @Override
    public void execute() throws EmptyInputException, InvalidIndexException, InvalidInputFormatException,
            NumberFormatException{
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("Input cannot be empty");
        }

        // Split input to get the index and fields to edit
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidInputFormatException("Invalid format. Usage: edit <index> <field1> <field2> ...");
        }

        try {
            index = Integer.parseInt(parts[1]) - 1;  // Convert index to zero-based
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException("Index must be a number.");
        }

        if (index < 0 || index > NextStep.internships.size()) {
            throw new InvalidIndexException("Invalid index number");
        }

        Internship internship = NextStep.internships.get(index);

        // Store the fields as a simple array of string
        fieldsToEdit = new String[parts.length - 2];
        System.arraycopy(parts, 2, fieldsToEdit, 0, parts.length - 2);

        Scanner scanner = new Scanner(System.in);
        for (String field : fieldsToEdit) {
            switch (field.toLowerCase()) {
                case "company":
                    System.out.print("Updated Company: ");
                    String newCompany = scanner.nextLine();
                    internship.setCompany(newCompany);
                    break;
                case "role":
                    System.out.print("Updated Role: ");
                    String newRole = scanner.nextLine();
                    internship.setRole(newRole);
                    break;
                case "duration":
                    System.out.print("Updated Duration (in months): ");
                    int newDuration = Integer.parseInt(scanner.nextLine());
                    internship.setDuration(newDuration);
                    break;
                case "allowance":
                    System.out.print("Updated Allowance: ($)");
                    int newAllowance = Integer.parseInt(scanner.nextLine());
                    internship.setAllowance(newAllowance);
                    break;
                case "skills":
                    System.out.print("Updated Skills (comma-seperated): ");
                    String newSkills = scanner.nextLine();
                    internship.setSkills(newSkills.split(",\\s*"));
                    break;
                default:
                    throw new InvalidInputFormatException(field + "is an invalid field to edit");
            }
        }
        printEditSuccess(internship);
    }
}
