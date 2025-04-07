package seedu.nextstep.storage;

import seedu.nextstep.core.InternshipList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InvalidClassException;

public class Storage {
    private static final String FILE_PATH = "./data/nextstep.txt";
    private static final String DIRECTORY_PATH = "./data";

    public Storage() {
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Use mkdirs() to create parent directories
            if (!created) {
                System.err.println("Failed to create data directory");
            }
        }
    }

    public void save(InternshipList internships) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(internships);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public InternshipList load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new InternshipList();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            return (InternshipList) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Data format is incompatible. Starting with fresh data.");
            return new InternshipList();
        } catch (InvalidClassException e) {
            System.err.println("Warning: Data format has changed. Starting with fresh data.");
            backupCorruptedFile();
            return new InternshipList();
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage() + "." + "Starting with fresh data.");
            backupCorruptedFile();
            return new InternshipList();
        }
    }

    private void backupCorruptedFile() {
        File original = new File(FILE_PATH);
        File backup = new File(FILE_PATH + ".corrupted");
        if (original.exists()) {
            original.renameTo(backup);
        }
    }
}
