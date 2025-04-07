package seedu.nextstep.command;

import java.util.Scanner;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;
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
     * @throws InvalidIntegerException     if allowance is negative or duration is non-positive.
     */
    @Override
    public void execute()
            throws EmptyInputException, InvalidIndexException, InvalidInputFormatException, InvalidIntegerException {
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
        String[] parts = input.trim().split("\\s+");
        if (parts.length < 2) {
            throw new InvalidInputFormatException("Error: Invalid format. Usage: edit <index>.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= internships.size()) {
            throw new InvalidIndexException("Error: Invalid index. Please enter a valid internship number.");
        }
        return internships.getInternship(index);
    }

    private void processFieldEdits(Internship internship, String[] fieldsToEdit) throws EmptyInputException {
        boolean isAnyFieldUpdated = false;
        StringBuilder warningMessages = new StringBuilder();

        for (String field : fieldsToEdit) {
            boolean valid = false;

            while (!valid) {
                try {
                    switch (field.toLowerCase()) {
                    case "company":
                        System.out.print("Updated Company: ");
                        String newCompany = scanner.nextLine().trim();
                        if (newCompany.isEmpty()) {
                            throw new EmptyInputException("Company cannot be empty.");
                        }
                        internship.setCompany(newCompany);
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    case "role":
                        System.out.print("Updated Role: ");
                        String newRole = scanner.nextLine().trim();
                        if (newRole.isEmpty()) {
                            throw new EmptyInputException("Role cannot be empty.");
                        }
                        internship.setRole(newRole);
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    case "duration":
                        System.out.print("Updated Duration (months): ");
                        String durationInput = scanner.nextLine().trim();
                        if (durationInput.isEmpty()) {
                            throw new EmptyInputException("Duration cannot be empty.");
                        }
                        int durationInteger = Integer.parseInt(durationInput);
                        if (durationInteger <= 0) {
                            throw new InvalidIntegerException("Duration must be greater than 0.");
                        }
                        internship.setDuration(durationInteger);
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    case "allowance":
                        System.out.print("Updated Allowance ($): ");
                        String allowanceInput = scanner.nextLine().trim();
                        if (allowanceInput.isEmpty()) {
                            throw new EmptyInputException("Allowance cannot be empty.");
                        }
                        int allowanceInteger = Integer.parseInt(allowanceInput);
                        if (allowanceInteger < 0) {
                            throw new InvalidIntegerException("Allowance cannot be negative.");
                        }
                        internship.setAllowance(allowanceInteger);
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    case "skills":
                        System.out.print("Updated Skills (comma-separated): ");
                        String skillsInput = scanner.nextLine().trim();
                        if (skillsInput.isEmpty()) {
                            throw new EmptyInputException("Skills cannot be empty.");
                        }
                        internship.setSkills(skillsInput.split(",\\s*"));
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    case "status":
                        System.out.print("Updated Status: ");
                        String newStatus = scanner.nextLine().trim().toUpperCase();
                        if (newStatus.isEmpty()) {
                            throw new EmptyInputException("Status cannot be empty.");
                        }
                        if (!checkIsValidStatus(newStatus)) {
                            throw new InvalidInputFormatException("Status must be 'A', 'P', 'R' or '-'");
                        }
                        internship.setStatus(newStatus);
                        valid = true;
                        isAnyFieldUpdated = true;
                        break;

                    default:
                        warningMessages.append("Warning: '").append(field).append("' is not a valid field.\n");
                        valid = true; // Skip invalid field without retry
                        break;
                    }
                } catch (Exception e) {
                    Ui.printLinebreak();
                    Ui.printMessage("Invalid input for field '" + field + "'. " + e.getMessage() +
                            " Please try again.");
                    Ui.printLinebreak();
                }
            }
        }

        if (!isAnyFieldUpdated) {
            throw new EmptyInputException("Error: Invalid fields.");
        }

        if (!warningMessages.isEmpty()) {
            Ui.printLinebreak();
            Ui.printMessage(warningMessages.toString());
        }
    }


    /**
     * Checks if status provided is valid.
     * @param status The status to be checked.
     * @return true is status is valid, false otherwise.
     */
    private boolean checkIsValidStatus(String status) {
        return status.equals("A") || status.equals("P") || status.equals("R") || status.equals("-");
    }
}
