package bookstore.window;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * BookStoreWindowIO - driver class used for user interaction through terminal interface.
 * Terminal interface displayed to the user consists of numbered options that user can select via keyboard input, see: {@link #printMenu() printMenu() options}.
 * <p>File books.csv used in this package contains fictional entries inspired on dataset "Amazon Top 50 Bestselling Books 2009-2019.csv" found here:
 * <a href="https://github.com/aiplanethub/Datasets/blob/master/Amazon%20Top%2050%20Bestselling%20Books%202009%20-%202019.csv?plain=1">LINK</a></p>
 * @author Robert Petecki
 * @version 1.0 Date created: 17/10/2023
 */
public class BookStoreWindowIO {
    //Fields
    /**
     * Static variable representing the book store window.
     * @see BookStoreWindow
     */
    private static BookStoreWindow window;
    /**
     * Static boolean variable initialised to false, used to indicate if the .csv file has been read in.
     */
    private static boolean readFiles = false;


    //Main method
    /**
     * Presents the user with the options menu allowing for interaction via keyboard input in the terminal interface, running until user quits. See {@link #printMenu() printMenu()}
     * @param args N/A
     */
    public static void main(String[] args) {
        //initialise logic value to keep the program running until user quits
        boolean done = false;

        //Welcome message to be shown only when starting program
        System.out.println("Welcome to the book store window manager program.");
        printWelcomeASCII();
;

        //Scanner initialisation for user interaction with the menu
        Scanner input = new Scanner(System.in);

        //While loop to keep the program running until user quits (menu option 5.)
        while(!done) {

            printMenu(); //print menu of options at the start of each iteration

            String userResponse = input.next(); //user interaction via keyboard input in terminal

            //switch statement for different user responses, and corresponding actions
            switch (userResponse){
                case "1": //1. Enter the name of the book store
                    setBookStoreName();
                    break;
                case "2": //2. Read in information on the books from a books.csv file
                    readInFile(); //if successful readFiles variable is changed to true
                    break;
                case "3": //3. Print a summary of the book store (name and list of all books with their value and the year published)
                    //name of the book store, set using option 1.
                    if (window != null) //conditional to check the book store window exists
                    {
                        System.out.println("Book shop name: " + window.getBookStoreName()); //prints to terminal interface
                    }
                    else
                    {
                        System.out.println("You haven's set the name of the book store yet. Use option 1 to set the name." ); //no name
                    }
                    //list of all books with their value and the year published
                    printBookList();
                    System.out.println(); //empty line
                    break;
                case "4": //4. Print statistics on books (highest value book, oldest book, average value of books)
                    BookStats();
                    break;
                case "5": //5. Quit
                    System.out.println("Books away, see you later!"); //exit message
                    printExitASCII();
                    done = true; //stops the while loop
                    break;
                default: //invalid option
                    System.out.println("This option doesn't exist.\n");
                    break;
            }
        }
    }

    /**
     * Prints the menu to the user. The menu has 5 options that can be chosen via keyboard input of the corresponding option number:
     * <ul>
     *     <li>1. Enter the name of the book store</li>
     *     <li>2. Read in information on the books from a books.csv file</li>
     *     <li>3. Print a summary of the book store (name and list of all books with their value and the year published)</li>
     *     <li>4. Print statistics on books (highest value book, oldest book, average value of books)</li>
     *     <li>5. Quit </li>
     * </ul>
     */
    private static void printMenu(){
        System.out.println("Choose one of the options below:");
        System.out.println("1. Enter the name of the book store");
        System.out.println("2. Read in information on the books from a books.csv file");
        System.out.println("3. Print a summary of the book store (name and list of all books with their value and the year published)");
        System.out.println("4. Print statistics on books (highest value book, oldest book, average value of books)");
        System.out.println("5. Quit");
    }

    /**
     * Sets the name of the book store. Used by option 1., see: {@link #printMenu() printMenu()}
     */
    private static void setBookStoreName()
    {
        String storeName; //local variable for the name

        Scanner inputName = new Scanner(System.in); //user input scanner

        System.out.println("Please enter the name of the bookstore:"); //message to the user

        storeName = inputName.nextLine(); //user input

        window = new BookStoreWindow(storeName); //new book store window object with the specified name passed to the constructor

        System.out.println("Success!\nThe name of the bookstore was set to '" + storeName + "'.\n"); //message confirming the name to the user
    }

    /**
     * Returns logic value upon reading in the "books.csv" file of book items from the current directory upon validating if it has already been done before.
     * Used by option 2., see: {@link #printMenu() printMenu()}
     * <p>The file books.csv must be structured as a comma-separated variable file as follows: bookID,title,year published,value</p>
     * <p>A bookID cannot include a comma, but other than this there is no specific constraint on the format or length of an bookID.</p>
     * <p>Example: A123,Dune,1965,45.00</p>
     * @return boolean, true if successful and false otherwise.
     */
    private static boolean readInFile(){
        if(!readFiles && window != null){ //validate if has been done before and book store window exists (option 1.)
            try {
                File input = new File("books.csv");
                Scanner line = new Scanner(input);
                line.nextLine();

                while (line.hasNext()){
                    String[] split = line.nextLine().split(",");

                    Book b = new Book(split[0], split[1], Integer.parseInt(split[2]), Double.parseDouble(split[3]));

                    window.addBookItem(b);
                }
            }
            catch (FileNotFoundException e){ //File books.csv not present in the current directory
                System.out.println("File was not found. Try again.\n"); //message
                readFiles = false;
                return readFiles; //not read in, false
            }
        readFiles = true; //read in successfully, true
            System.out.println("Success!");
            System.out.println("Information on the books was read from the books.csv file located in the current directory.\n");
        }
        else if (!readFiles && window == null) //window must exist before reading in a file (option 1. must be done first)
        {
            System.out.println("You have to set the name of the book store first. Use option 1 to set the name.\n");
        }
        else if (readFiles)//print message that it's already read in
        {
            System.out.println("Information from the file has already been read in, choose a different option.\n");
        }
        return readFiles;
    }

    /**
     * Prints book list to the user interface. Used by option 3., see {@link #printMenu() printMenu()}
     */
    private static void printBookList(){
        if (readFiles) //conditional to check that the list of books has been read in before
        {
            for (Book book : window.getBookList()) {
                System.out.println(book);
            }
        }
        else //print message saying no file has been read in
        {
            System.out.println("There is no information on books yet. Try reading in information from a file using option 2.");
        }
    }

    /**
     * Prints basic statistics on books in the format:
     * <p>"Highest value book: book title (published year), £value</p>
     * <p>Oldest book: book title (published year)</p>
     * <p>Average value of books: £value to 2 decimal points"</p>
     * <p>Used by option 4., see {@link #printMenu() printMenu()}</p>
     */
    private static void BookStats(){
        if (readFiles && window != null) { //conditional to check that the book store window exists and book list has been read in before
            Book bMaxValue = BookStoreWindow.bookHighestValue();
            Book bOldest = BookStoreWindow.bookOldest();
            double bAvgValue = BookStoreWindow.avgValue();
            System.out.println("Highest value book: " + bMaxValue.getTitle() + " (published " + bMaxValue.getYearPublished() + "), £" + bMaxValue.getValue());
            System.out.println("Oldest book: " + bOldest.getTitle() + " (published " + bOldest.getYearPublished() + ")");
            System.out.println("Average value of books: £" + String.format("%.2f", bAvgValue) + "\n"); //printing out the average value rounded to 2 decimal points,
        }
        else
        {
            System.out.println("There is no information on books yet. Read in information from a file using option 2.\n");
        }
    }

    /**
     * Prints welcome ASCII Art to the terminal interface. ASCII art was found here: <a href="https://www.asciiart.eu/books/books">LINK</a>.
     */
    private static void printWelcomeASCII()
    {
        System.out.println(
                "  ||_______________________||__________________________|\n" +
                "  | _____________________  ||      __   __  _  __    _ |\n" +
                "  ||=|=|=|=|=|=|=|=|=|=|=| __..\\/ |  |_|  ||#||==|  / /|\n" +
                "  || | | | | | | | | | | |/\\ \\  \\\\|++|=|  || ||==| / / |\n" +
                "  ||_|_|_|_|_|_|_|_|_|_|_/_/\\_.___\\__|_|__||_||__|/_/__|\n" +
                "  |____________________ /\\~()/()~//\\ __________________|\n" +
                "  | __   __    _  _     \\_  (_ .  _/ _    ___     _____|\n" +
                "  ||~~|_|..|__| || |_ _   \\ //\\\\ /  |=|__|~|~|___| | | |\n" +
                "  ||--|+|^^|==|1||2| | |__/\\ __ /\\__| |==|x|x|+|+|=|=|=|\n" +
                "  ||__|_|__|__|_||_|_| /  \\ \\  / /  \\_|__|_|_|_|_|_|_|_|\n" +
                "  |_________________ _/    \\/\\/\\/    \\_ _______________|\n" +
                "  | _____   _   __  |/      \\../      \\|  __   __   ___|\n" +
                "  ||_____|_| |_|##|_||   |   \\/ __|   ||_|==|_|++|_|-|||\n" +
                "  ||______||=|#|--| |\\   \\   o    /   /| |  |~|  | | |||\n" +
                "  ||______||_|_|__|_|_\\   \\  o   /   /_|_|__|_|__|_|_|||\n" +
                "  |_________ __________\\___\\____/___/___________ ______|\n" +
                "  |__    _  /    ________     ______           /| _ _ _|\n" +
                "  |\\ \\  |=|/   //    /| //   /  /  / |        / ||%|%|%|\n" +
                "  | \\/\\ |*/  .//____//.//   /__/__/ (_)      /  ||=|=|=|\n" +
                "__|  \\/\\|/   /(____|/ //                    /  /||~|~|~|__\n" +
                "  |___\\_/   /________//   ________         /  / ||_|_|_|\n" +
                "  |___ /   (|________/   |\\_______\\       /  /| |______|\n" +
                "      /                  \\|________)     /  / | |\n");
    }

    /**
     * Prints exit ASCII Art to the terminal interface. ASCII art was found here: <a href="https://www.asciiart.eu/books/books">LINK</a>.
     */
    private static void printExitASCII()
    {
        System.out.println(
                "       .--.                   .---.\n" +
                        "   .---|__|           .-.     |~~~|\n" +
                        ".--|===|--|_          |_|     |~~~|--.\n" +
                        "|  |===|  |'\\     .---!~|  .--|   |--|\n" +
                        "|%%|   |  |.'\\    |===| |--|%%|   |  |\n" +
                        "|%%|   |  |\\.'\\   |   | |__|  |   |  |\n" +
                        "|  |   |  | \\  \\  |===| |==|  |   |  |\n" +
                        "|  |   |__|  \\.'\\ |   |_|__|  |~~~|__|\n" +
                        "|  |===|--|   \\.'\\|===|~|--|%%|~~~|--|\n" +
                        "^--^---'--^    `-'`---^-^--^--^---'--'");
    }
}

