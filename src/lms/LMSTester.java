package lms;

public class LMSTester {
    public static void main(String[] args) {
        LMS iliauniLibrary = new LMS();
        String stateFilePath = "libraryState.csv";

        // Load state when the program starts
        iliauniLibrary.loadState(stateFilePath);

        // Add books and perform operations
        Book hich = new Book("Lord of the rings", "tolkien");
        Book lor = new Book("Lord of the rings", "tolkien");
        Book oop = new Book("OOP", "paata gogisvhili");
        iliauniLibrary.addBook(lor);
        iliauniLibrary.addBook(oop);

        Student gocha = new Student("Gocha", "Gegeshidze", "dfasdf");
        iliauniLibrary.borrowBook(lor, gocha);

        Student maka = new Student("Maka", "Lobjanidze", "3421325");
        iliauniLibrary.borrowBook(oop, maka);

        // Print current library records
        iliauniLibrary.printLibraryRecords();

        // Save state before clearing the library
        iliauniLibrary.saveState(stateFilePath);

        // Clear the library state
        iliauniLibrary.clearState();
        iliauniLibrary.printLibraryRecords();

        // Reload the saved state
        iliauniLibrary.loadState(stateFilePath);
        iliauniLibrary.printLibraryRecords();

        // Return a book and save state again
        iliauniLibrary.returnBook(lor);
        iliauniLibrary.printLibraryRecords();
        iliauniLibrary.saveState(stateFilePath);
    }
}
