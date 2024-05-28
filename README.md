Functionality that you can use in the tester class:

Indicate file path for saving the state of the library:
    String stateFilePath = "fileName.csv"
    make sure that you have created ths file in the same package as the tester class
Creating a library:
    LMS libraryName = new LMS();

    example:
        LMS iliauniLibrary = new LMS();

Creating books:
    Book bookName = new Book("Title of the book", "Book author");
    example:
        Book hit = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams");

Creating students:
    Student studentName = new Student("Name", "Surname", "studentID");
    example:
        Student tornike = new Student("Tornike", "Kadeishvili", "12345");

Library functionality:
    save the state of the library:
        libraryName.saveState(stateFilePath);
        this saves the library state with the changes you made to the .csv file that you created
    load the library state:
        libraryName.loadState(stateFilePath);
    clear the library state:
        libraryName.clearState();
        when clearing a library state if you want this change to persist after loading a library,
        be sure to save the library state after running this command.
    showing library records:
        libraryName.printLibraryRecords();
    adding books to the library:
        libraryName.addBook(bookName);
    removing books from the library:
        libraryName.removeBook(bookName);
        if this book is not available no changes will be made (you will still get a corresponding message printed)
    borrowing a book:
        libraryName.borrowBook(bookName, studentName);

