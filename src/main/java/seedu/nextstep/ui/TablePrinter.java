package seedu.nextstep.ui;

import seedu.nextstep.core.Internship;

import java.util.List;

import static seedu.nextstep.ui.Ui.printLinebreak;

public class TablePrinter {
    // Prevents instantiation
    private TablePrinter() {}

    /**
     * Prints a formatted table of internships.
     *
     * @param internships The list of internships to print.
     */
    public static void printTable(List<Internship> internships) {
        printLinebreak();
        System.out.println("Here is your list! (•ᴗ•)");

        String[] headers = {"No.", "Company", "Role", "Duration", "Allowance", "Skills"};
        int[] columnWidths = computeColumnWidths(internships, headers);

        printSeparator(columnWidths);
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        for (int i = 0; i < internships.size(); i++) {
            Internship internship = internships.get(i);
            String[] row = {
                    String.valueOf(i + 1),
                    internship.getCompany(),
                    internship.getRole(),
                    internship.getDuration() + " months",
                    "$" + internship.getAllowance(),
                    String.join(", ", internship.getSkills())
            };
            printRow(row, columnWidths);
        }
        printSeparator(columnWidths);
    }

    /**
     * Computes the column widths based on internship data.
     *
     * @param internships The list of internships.
     * @param headers The table headers.
     * @return An array of column widths.
     */
    public static int[] computeColumnWidths(List<Internship> internships, String[] headers) {
        int numColumns = headers.length;
        int[] widths = new int[numColumns];

        for (int i = 0; i < numColumns; i++) {
            widths[i] = headers[i].length();
        }

        for (Internship internship : internships) {
            widths[0] = Math.max(widths[0], String.valueOf(internships.size()).length());  // No.
            widths[1] = Math.max(widths[1], internship.getCompany().length());             // Company
            widths[2] = Math.max(widths[2], internship.getRole().length());                // Role
            widths[3] = Math.max(widths[3], String.valueOf(internship.getDuration()).length());  // Duration
            widths[4] = Math.max(widths[4], ("$" + internship.getAllowance()).length());  // Allowance
            widths[5] = Math.max(widths[5], String.join(", ", internship.getSkills()).length()); // Skills
        }

        return widths;
    }

    /**
     * Prints a separator line based on column widths.
     *
     * @param columnWidths The width of each column.
     */
    public static void printSeparator(int[] columnWidths) {
        StringBuilder separator = new StringBuilder("+");
        for (int width : columnWidths) {
            separator.append("-".repeat(width + 2)).append("+");
        }
        System.out.println(separator);
    }

    /**
     * Prints a row of data.
     *
     * @param row The row data.
     * @param columnWidths The width of each column.
     */
    public static void printRow(String[] row, int[] columnWidths) {
        StringBuilder format = new StringBuilder("|");
        for (int width : columnWidths) {
            format.append(" %-").append(width).append("s |");
        }
        System.out.printf(format + "%n", (Object[]) row);
    }
}
