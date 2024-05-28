package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LMS {

    List<Book> books = new ArrayList<>();
    List<BorrowRecord> borrowRecords = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle() + " by " + book.getAuthor());
    }

    public boolean removeBook(Book book) {
        boolean removed = books.remove(book);
        if (removed) {
            System.out.println("Removed book: " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Failed to remove book: " + book.getTitle() + " by " + book.getAuthor());
        }
        return removed;
    }

    public void borrowBook(Book book, Student student) {
        if (books.contains(book)) {
            BorrowRecord record = new BorrowRecord(book, student);
            borrowRecords.add(record);
            books.remove(book);
            System.out.println(student.getName() + " " + student.getSurname() + " borrowed book: " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Book not available: " + book.getTitle() + " by " + book.getAuthor());
        }
    }

    public void returnBook(Book book) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBook().equals(book)) {
                borrowRecords.remove(record);
                books.add(book);
                System.out.println(record.getStudent().getName() + " " + record.getStudent().getSurname() + " returned book: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("Book not borrowed: " + book.getTitle() + " by " + book.getAuthor());
    }

    public void saveState(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write("BOOK," + book.getTitle() + "," + book.getAuthor());
                writer.newLine();
            }
            for (BorrowRecord record : borrowRecords) {
                Book book = record.getBook();
                Student student = record.getStudent();
                writer.write("BORROWED," + book.getTitle() + "," + book.getAuthor() + "," + student.getName() + "," + student.getSurname() + "," + student.getPersonalNumber());
                writer.newLine();
            }
            System.out.println("Library state saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadState(String filePath) {
        books.clear();
        borrowRecords.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("BOOK")) {
                    books.add(new Book(parts[1], parts[2]));
                } else if (parts[0].equals("BORROWED")) {
                    Book book = new Book(parts[1], parts[2]);
                    Student student = new Student(parts[3], parts[4], parts[5]);
                    borrowRecords.add(new BorrowRecord(book, student));
                }
            }
            System.out.println("Library state loaded from " + filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clearState() {
        books.clear();
        borrowRecords.clear();
        System.out.println("Library state cleared.");
    }

    public void printLibraryRecords() {
        System.out.println("Library Records:");
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(" - " + book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("Borrowed Books:");
        for (BorrowRecord record : borrowRecords) {
            Book book = record.getBook();
            Student student = record.getStudent();
            System.out.println(" - " + book.getTitle() + " by " + book.getAuthor() + " borrowed by " + student.getName() + " " + student.getSurname());
        }
    }

    private static class BorrowRecord {
        private Book book;
        private Student student;

        public BorrowRecord(Book book, Student student) {
            this.book = book;
            this.student = student;
        }

        public Book getBook() {
            return book;
        }

        public Student getStudent() {
            return student;
        }
    }
}
