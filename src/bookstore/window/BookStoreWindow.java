package bookstore.window;

import java.util.ArrayList;

/**
 * BookStoreWindow class - immutable class used to represent a book store window.
 * <p>Contains a constructor and methods used for:</p>
 * <ul>
 * <li>retrieving book store name</li>
 * <li>book list</li>
 * <li>adding books</li>
 * <li>generating basic statistics about the book items.</li>
 * </ul>
 * @author Robert Petecki
 * @version 1.0 Date created: 17/10/2023
 */
final class BookStoreWindow {
    //Fields
    /**
     * String variable storing the bookstore name.
     */
    private String bookStoreName;
    /**
     * Unsorted, static ArrayList storing the Book objects added to any book store window object.
     */
    private static ArrayList<Book> bookList = new ArrayList<>();


    //Constructor
    /**
     * Constructs a new BookStoreWindow object with a book store name specified in the parameter.
     * @param bookStoreName String specified name of the book store.
     */
    public BookStoreWindow(String bookStoreName){
       this.bookStoreName = bookStoreName;
    }


    //Getter methods for returning book store name and list of books
    /**
     * Returns the book store name.
     * @return String representation of the book store name.
     */
    public String getBookStoreName() {
        return bookStoreName;
    }

    /**
     * Returns the list of the book items added to any book store window object.
     * @return ArrayList of Book objects.
     */
    public ArrayList<Book> getBookList(){
        return bookList;
    }


    //Setter methods
    /**
     * Adds a book item to the ArrayList of books.
     * @param b Book object added to the list.
     */
    public void addBookItem(Book b) {
        bookList.add(b);
    }

    //Methods
    /**
     * Finds and returns the Book object with the highest value of the books stored in the list.
     * @return Book object that has the highest value.
     */
    public static Book bookHighestValue(){
        double maxValue = bookList.get(0).getValue();
        int maxValueIndex = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getValue() > maxValue) {
                maxValue = bookList.get(i).getValue();
                maxValueIndex = i;
            }
        }
        return bookList.get(maxValueIndex);
        }

    /**
     * Finds and returns the Book object that is the oldest by the year published of the books stored in the list.
     * @return Book object that has the lowest year published.
     */
    public static Book bookOldest(){
        double minValue = bookList.get(0).getYearPublished();
        int minValueIndex = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getYearPublished() < minValue) {
                minValue = bookList.get(i).getYearPublished();
                minValueIndex = i;
            }
        }
        return bookList.get(minValueIndex);
    }

    /**
     * Calculates and returns the average balue of all books in the list.
     * @return double average value of books.
     */
    public static double avgValue(){
        double sumValue = 0;
        double avgBookValue = 0;
        for (Book book : bookList) {
            sumValue += book.getValue();
            avgBookValue = sumValue / bookList.size();
        }
        return avgBookValue;
    }

    /**
     * Returns string representation of the book store window.
     * @return String in the format: Book Store Name: name, Book List: list of books.
     */
    @Override
    public String toString() {
        return "Book Store Name: " + getBookStoreName() +
                ", Book List: " + getBookList();
    }
}