/****************************************SC**********************************
 *****************
 * Name: Suresh Chitkara *
 * Course: Software Development I CEN-3024C-24667 *
 * Date: 3/22/2024 *
 * Description: The LibraryManagementSystem class does the functions for the books such as adding a book,
 * removing a book, and checking in and checking out a book.
 *****************************************SC**********************************
 ******************/
import java.util.ArrayList; /* SC The import java.util.ArrayList; statement is used to import the ArrayList
class from the java.util package. */
import java.util.Calendar; /* SC The import java.util.Calendar; statement is used to import the Calendar class from the
java.util package. */
import java.util.Date; /* SC The import java.util.Date; statement is used to import the Date class from the java.util
package. */
import java.util.List; /* SC The import java.util.List; statement is used to import the List interface from the
java.util package. */

public class LibraryManagementSystem { /* SC Start of class LibraryManagementSystem */

    private List<Book> books; /* SC This line of code declares a private field named books of type List<Book>. */

    public LibraryManagementSystem() { /* SC This code snippet defines a constructor for the LibraryManagementSystem class.
     Inside the constructor, it initializes the books field with a new instance of an ArrayList<Book>. */
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) { /* SC
This method, addBook(Book book), is a public method defined within the LibraryManagementSystem class.
It allows adding a Book object to the list of books maintained by the library management system. */
        books.add(book);
    }

    public boolean removeBookByBarcode(int barcode) { /* SC This method, removeBookByBarcode(int barcode), is a public
     method defined within the LibraryManagementSystem class. It aims to remove a book from the list of books maintained
      by the library management system based on the barcode. */
        for (Book book : books) {
            if (book.getBarcode() == barcode) {
                books.remove(book);
                return true;
            }
        }
        System.out.println("Book with barcode " + barcode + " not found.");
        return false; /* SC Book not found */
    }

    public boolean removeBookByTitle(String title) { /* SC This method, removeBookByTitle(String title), is a public
    method defined within the LibraryManagementSystem class. It aims to remove a book from the list of books maintained
    by the library management system based on the title. */
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                return true; /* SC Book has been successfully removed */
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
        return false; /* SC Book has not been found. */
    }

    public boolean checkOutBook(String title) { /* SC This line aims to handle the process of checking out a book from
     the library management system based on its title. */
        for (Book book : books) { /* SC The loop iterates over each Book object in the books list. */
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isCheckedOut()) {
                    /* SC Calculate due date as 4 weeks from the current date */
                    Calendar dueDate = Calendar.getInstance();
                    dueDate.add(Calendar.WEEK_OF_YEAR, 4);
                    Date dueDateObject = dueDate.getTime(); /* SC Due date as Date object */

                    /* SC Set the due date of the book */
                    book.setDueDate(dueDateObject);

                    /* SC Set the book status to checked out. */
                    book.setCheckedOut(true);

                    /* SC This part of the code handles the output messages for the checkout process based on whether the
                    book is successfully checked out or if it's already checked out. */

                    System.out.println("Book '" + title + "' checked out successfully.");
                    return true;
                } else {
                    System.out.println("Book '" + title + "' is already checked out.");
                    return false;
                }
            }
        }
        System.out.println("Book '" + title + "' not found in the library.");
        return false; /* SC This message provides feedback to the user indicating that the requested book was not found
         in the library, allowing them to understand why the checkout process was unsuccessful. */
    }

    public boolean checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (book.isCheckedOut()) {
                    book.setCheckedOut(false);
                    book.setDueDate(null); // Set due date to null when checking in
                    System.out.println("Book '" + title + "' checked in successfully.");
                    return true;
                } else {
                    System.out.println("Book '" + title + "' is already checked in or not found.");
                    return false;
                }
            }
        }
        System.out.println("Book '" + title + "' not found in the library.");
        return false;
    } /* SC This method provides functionality to check in a book to the library management system based
        on its title. The specific implementation ensures that the book is only checked in if it is currently checked out. */

    public List<Book> getBooks() {
        return books; /* SC Returns the list of books maintained by the library management system. */
    }
}
