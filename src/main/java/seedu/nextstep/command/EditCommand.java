package seedu.nextstep.command;

import java.util.Scanner;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

/**
 * Represents a command to edit an existing internship entry.
 */
public class EditCommand extends Command {
    private final Scanner scanner;
    private final Storage storage;

    /**
     * Constructs an EditCommand object.
     *
     * @param input       The user input, which includes the index of the internship to edit.
     * @param internships The list of internships.
     * @param storage     The storage object.
     */
    public EditCommand(String input, InternshipList internships, Storage storage) {
        super(input, internships);
        this.scanner = new Scanner(System.in);
        this.storage = storage;
    }

    /**
     * Executes the edit command by prompting the user for the fields they wish to edit,
     * collecting updated values, and applying the changes to the internship.
     *
     * @throws EmptyInputException         if the user provides an empty input.
     * @throws InvalidIndexException       if the provided index is out of bounds.
     * @throws InvalidInputFormatException if the user input format is incorrect.
     * @throws NumberFormatException       if numeric values are not properly formatted.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidIndexException, InvalidInputFormatException {
        Internship internship = getInternshipToEdit();

        // Prompt user for fields to edit
        Ui.printEditMessage();
        String fieldsInput = scanner.nextLine().trim();
        if (fieldsInput.isEmpty()) {
            throw new EmptyInputException("Error: Fields to edit cannot be empty.");
        }

        processFieldEdits(internship, fieldsInput.split(",\\s*"));
        storage.save(internships);
        Ui.printEditSuccess(internship);
    }

    private Internship getInternshipToEdit() throws InvalidIndexException, InvalidInputFormatException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidInputFormatException("Error: Invalid format. Usage: edit <index>.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= internships.size()) {
            throw new InvalidIndexException("Error: Invalid index. Please enter a valid internship number.");
        }
        return internships.getInternship(index);
    }

    private void processFieldEdits(Internship internship, String[] fieldsToEdit)
            throws EmptyInputException, InvalidInputFormatException {
        for (String field : fieldsToEdit) {
            switch (field.toLowerCase()) {
            case "company":
                System.out.print("Updated Company: ");
                String newCompany = scanner.nextLine().trim();
                if (newCompany.isEmpty()) {
                    throw new EmptyInputException("Error: Company cannot be empty.");
                }
                internship.setCompany(newCompany);
                break;
            case "role":
                System.out.print("Updated Role: ");
                String newRole = scanner.nextLine().trim();
                if (newRole.isEmpty()) {
                    throw new EmptyInputException("Error: Role cannot be empty.");
                }
                internship.setRole(newRole);
                break;
            case "duration":
                System.out.print("Updated Duration (months): ");
                String durationInput = scanner.nextLine().trim();
                if (durationInput.isEmpty()) {
                    throw new EmptyInputException("Error: Duration cannot be empty.");
                }
                try {
                    internship.setDuration(Integer.parseInt(durationInput));
                } catch (NumberFormatException e) {
                    throw new InvalidInputFormatException("Error: Duration must be a number.");
                }
                break;
            case "allowance":
                System.out.print("Updated Allowance ($): ");
                String allowanceInput = scanner.nextLine().trim();
                if (allowanceInput.isEmpty()) {
                    throw new EmptyInputException("Error: Allowance cannot be empty.");
                }
                try {
                    internship.setAllowance(Integer.parseInt(allowanceInput));
                } catch (NumberFormatException e) {
                    throw new InvalidInputFormatException("Error: Allowance must be a number.");
                }
                break;
            case "skills":
                System.out.print("Updated Skills (comma-separated): ");
                String skillsInput = scanner.nextLine().trim();
                if (skillsInput.isEmpty()) {
                    throw new EmptyInputException("Error: Skills cannot be empty.");
                }
                internship.setSkills(skillsInput.split(",\\s*"));
                break;
            case "status":
                System.out.print("Updated Status: ");
                String newStatus = scanner.nextLine().trim();
                if (newStatus.isEmpty()) {
                    throw new EmptyInputException("Error: Status cannot be empty.");
                }
                internship.setStatus(newStatus);
                break;
            default:
                throw new InvalidInputFormatException("Error: " + field + " is an invalid field.");
            }
        }
    }
}
