package com.tss.test;

import com.tss.model.*;

import java.io.*;
import java.util.*;

public class BookTest {
    private static final String FILE_NAME = "output.txt";

    public static void main(String[] args) {
        List<Book> books = loadBooks();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                printMenu();
                while (!scanner.hasNextInt()) {
                    System.out.print("Enter a valid number: ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> addBook(books, scanner);
                    case 2 -> issueBook(books, scanner);
                    case 3 -> displayBooks(books, false);
                    case 4 -> displayBooks(books, true);
                    case 5 -> returnBook(books, scanner);
                    case 6 -> sortBooks(books, scanner);
                    case 7 -> {
                        saveBooks(books);
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 7);
        }
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add a new book");
        System.out.println("2. Issue a book by ID");
        System.out.println("3. Display all available books");
        System.out.println("4. Display all issued books");
        System.out.println("5. Return a book by ID");
        System.out.println("6. Sort Books");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private static void issueBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter book ID to issue: ");
        int id = scanner.nextInt();

        for (Book b : books) {
            if (b.getBook_id() == id && !b.isIssued()) {
                b.setIssued(true);
                System.out.println("Book issued successfully.");
                return;
            }
        }
        System.out.println("Book not found or already issued.");
    }

    private static void returnBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();

        for (Book b : books) {
            if (b.getBook_id() == id && b.isIssued()) {
                b.setIssued(false);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }

    private static void displayBooks(List<Book> books, boolean issuedOnly) {
        boolean found = false;
        System.out.printf("\n%-10s %-25s %-25s %-10s\n", "Book ID", "Title", "Author", "Issued");
        System.out.println("--------------------------------------------------------------------------");
        for (Book b : books) {
            if (b.isIssued() == issuedOnly) {
                System.out.printf("%-10d %-25s %-25s %-10s\n",
                        b.getBook_id(), b.getName(), b.getAuthor(), b.isIssued() ? "Yes" : "No");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No " + (issuedOnly ? "issued" : "available") + " books found.");
        }
    }

    private static void sortBooks(List<Book> books, Scanner scanner) {
        System.out.println("Sort by:\n1. Author\n2. Title");
        int sortChoice = scanner.nextInt();
        switch (sortChoice) {
            case 1 -> {
                books.sort(new BookAuthorComparator());
                System.out.println("\nBooks sorted by author:");
                displayAllBooksTable(books);
            }
            case 2 -> {
                books.sort(new BookNameComparator());
                System.out.println("\nBooks sorted by title:");
                displayAllBooksTable(books);
            }
            default -> System.out.println("Invalid sort choice.");
        }
    }

    private static void displayAllBooksTable(List<Book> books) {
        System.out.printf("\n%-10s %-25s %-25s %-10s\n", "Book ID", "Title", "Author", "Issued");
        System.out.println("--------------------------------------------------------------------------");
        for (Book b : books) {
            System.out.printf("%-10d %-25s %-25s %-10s\n",
                    b.getBook_id(), b.getName(), b.getAuthor(), b.isIssued() ? "Yes" : "No");
        }
    }

    private static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Book> loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books. Starting with empty list.");
            return new ArrayList<>();
        }
    }
}
