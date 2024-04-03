package bookstore.window;

/**
 * Book class - immutable class, containing a constructor and methods to set and retrieve information about Book objects: the book ID, title, year published and value.
 * @author Robert Petecki
 * @version 1.0 Date created: 16/10/2023
 */
final class Book {

    //Attributes of a Book object
    /**
     * String variable storing the book ID.
     */
    private final String id;
    /**
     * String variable storing the book title.
     */
    private final String title;
    /**
     * int variable storing the book year published.
     */
    private final int yearPublished;
    /**
     * double variable storing the book value.
     */
    private double value;

    /**
     * Constructs a new Book object with all its attributes passed as parameters.
     * @param id String book ID.
     * @param title String book title.
     * @param yearPublished int book year published.
     * @param value double book value.
     */
    public Book(String id, String title, int yearPublished, double value){
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
        this.value = value;

        /*optional code below in this comment would confirm in the console that new book object was created
        System.out.println("New book was added!"); */
    }

    //Getter methods for retrieving private attributes of a Book object
    /**
     * Returns book ID.
     * @return String book ID, attribute of the Book object.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns book title.
     * @return String book title, attribute of the Book object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns book year published.
     * @return in book year published, attribute of the Book object.
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Returns book value.
     * @return double book value, attribute of the Book object.
     */
    public double getValue() {
        return value;
    }

    //Setter methods for setting mutable private attributes of a Book object.
    //Assumption: id, title, and year published are immutable after creating the Book object.
    /**
     * Sets book value passed in the parameter.
     * @param value double new book value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Returns string representation of a Book object.
     * @return String in the format: Book ID: id, Title: title, Year Published: year, Value: £value.
     */
    @Override
    public String toString() {
      return "Book ID: " + getId() +
               ", Title: " + getTitle() +
               ", Year Published: " + getYearPublished() +
               ", Value: £" + getValue();
    }
}