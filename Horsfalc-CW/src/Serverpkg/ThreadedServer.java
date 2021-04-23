package Serverpkg;
import both.Book;
import both.On_loan;
import both.Person;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Demonstrates how to make a connection to the SQLite database and then just
 * outputs the data to the console.
 *
 * @author Horsfall Chukwuemeka
 */
public class ThreadedServer {


    /**
     * Constructor will just initialise the HashMap lookup table on the Server.
     */
    public ThreadedServer() {

        //initTracks();

    }

    /**
     * Let's just hard-code a simple HashMap<Keys, Values> to act as a lookup
     * table for the data to send.
     */
    public synchronized List<Book> initBooks() {

        ArrayList<Book> books = new ArrayList<>();
        String selectSQL = "SELECT * FROM books "; // lets just get the first 10 records for testing


        try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
             PreparedStatement prep = conn.prepareStatement(selectSQL)) {

            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {

                books.add(Book.newBookFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serverpkg.ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public synchronized List<Person> initPerson() {

        ArrayList<Person> person = new ArrayList<>();
        String selectSQL = "SELECT * FROM person "; // lets just get the first 10 records for testing


        try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
             PreparedStatement prep = conn.prepareStatement(selectSQL)) {

            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {
                person.add(Person.newPersonFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serverpkg.ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }

    public synchronized List<On_loan> initOnLoan() {

        ArrayList<On_loan> onLoan = new ArrayList<>();
        String selectSQL = "SELECT * FROM on_loan "; // lets just get the first 10 records for testing


        try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
             PreparedStatement prep = conn.prepareStatement(selectSQL)) {

            ResultSet resultSet = prep.executeQuery();

            while (resultSet.next()) {

                onLoan.add(On_loan.newOnLoanFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serverpkg.ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return onLoan;
    }

    public synchronized static void insertBook(Book newBook)
   {
      String insertSQL = "INSERT INTO books (title, authors, average_rating, isbn, isbn13, language_code, `#num_pages`, ratings_count, text_reviews_count, quantity) "

                          + "VALUES (?,?,?,?,?,?,?,?,?,?)";

      try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
           PreparedStatement prep = conn.prepareStatement(insertSQL)) {

        prep.setString(1, newBook.getTitle());
        prep.setString(2, newBook.getAuthors());
        prep.setFloat(3, newBook.getAverage_rating());
        prep.setDouble(4, newBook.getIsbn());
        prep.setDouble(5, newBook.getIsbn13());
        prep.setString(6, newBook.getLanguage_code());
        prep.setInt(7, newBook.getNum_pages());
        prep.setInt(8, newBook.getRating_count());
        prep.setInt(9, newBook.getText_review_count());
        prep.setInt(10, newBook.getQuantity());

        prep.execute();

      } catch (SQLException ex) {
          Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
      }



   }

   public synchronized static void insertPerson(Person newPerson){

        String insertSQL = "INSERT INTO person (first_name, last_name, library_card) " + "VALUES (?,?,?)";


       try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(insertSQL)) {

          prep.setString(1,newPerson.getFirst_name());
          prep.setString(2, newPerson.getLast_name());
          prep.setDouble(3,newPerson.getLibraryCard());

          prep.execute();

       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }


   }

   public synchronized static void insertLoan(On_loan newLoan){

        String insertSQL = "INSERT INTO on_loan (book_id, person_id, loan_period, loan_start, loan_end, returned_date, return_status )"

                + "VALUES (?,?,?,?,?,?,?)";




       try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(insertSQL)) {

          prep.setInt(1,newLoan.getBook_Id());
          prep.setInt(2,newLoan.getPerson_Id());
          prep.setInt(3,newLoan.getLoan_Period());
          prep.setString(4,newLoan.getLoan_Start());
          prep.setString(5,newLoan.getLoan_End());
          prep.setString(6, newLoan.getReturned_Date());
          prep.setString(7, newLoan.getReturn_Status());

          prep.execute();


       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }
   }



   public synchronized static void bookUpdate(Book updateBook){


        String updateSQL = "UPDATE books SET title = ?, authors = ?, average_rating = ?,isbn = ?, isbn13 = ?, language_code = ?, `#num_pages` = ?, ratings_count = ?, text_reviews_count = ?, quantity = ? "

                + "WHERE book_id = ? "  ;


        try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(updateSQL)) {


           prep.setString(1, updateBook.getTitle());
           prep.setString(2, updateBook.getAuthors());
           prep.setFloat(3,  updateBook.getAverage_rating());
           prep.setDouble(4, updateBook.getIsbn());
           prep.setDouble(5, updateBook.getIsbn13());
           prep.setString(6, updateBook.getLanguage_code());
           prep.setInt(7, updateBook.getNum_pages());
           prep.setInt(8, updateBook.getRating_count());
           prep.setInt(9, updateBook.getText_review_count());
           prep.setInt(10,updateBook.getQuantity());
            prep.setInt(11, updateBook.getBookId());

           prep.execute();

           System.out.println("updated");

       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }

   }


   public synchronized static void personUpdate(Person updatePerson){

        String updateSQL = " UPDATE person SET first_name = ?, last_name = ?, library_card = ?" + " WHERE person_id = ?";

       try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(updateSQL)) {


         prep.setString(1, updatePerson.getFirst_name());

         prep.setString(2, updatePerson.getLast_name());

         prep.setDouble(3, updatePerson.getLibraryCard());

         prep.setInt(4, updatePerson.getPersonId());

          prep.execute();

       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }
   }



   public synchronized static void loanUpdate( On_loan updateLoan){


        String updateSQL = " UPDATE on_loan SET book_id = ?, person_id = ?, loan_period = ?, loan_start = ?, loan_end = ?, returned_date = ?, return_status = ?"
                + " WHERE loan_id = ?";


       try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(updateSQL)) {


           prep.setInt(1, updateLoan.getBook_Id());
           prep.setInt(2, updateLoan.getPerson_Id());
           prep.setInt(3, updateLoan.getLoan_Period());
           prep.setString(4, updateLoan.getLoan_Start());
           prep.setString(5, updateLoan.getLoan_End());
           prep.setString(6, updateLoan.getReturned_Date());
           prep.setString(7, updateLoan.getReturn_Status());
           prep.setInt(8, updateLoan.getLoan_Id());

           prep.execute();

       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }

   }

   public synchronized static void deleteBook(Book delete)
   {

         String deleteSQL = "DELETE FROM books WHERE book_id = ?";

       try (Connection conn = ConnectionFactory.getConnection(); // auto close the connection object after try
            PreparedStatement prep = conn.prepareStatement(deleteSQL)) {

         prep.setInt(1,delete.getBookId());

         prep.execute();

       } catch (SQLException ex) {
           Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
       }


   }



    /**
     * Wait until a client connects to the server on a port, then establish the
     * connection via a socket object and create a thread to handle requests.
     */
    private void connectToClients() {
        System.out.println("Server: Server starting.");

        try (ServerSocket serverSocket = new ServerSocket(4000)) {

            while (true) {
                System.out.println("Server: Waiting for connecting client...");

                try {
                    Socket socket = serverSocket.accept();

                    ClientHandler clientHandler = new ClientHandler(socket,initBooks(),initPerson(),initOnLoan());
                    Thread connectionThread = new Thread(clientHandler);
                    connectionThread.start(); // thread goes into a ready state
                } catch (IOException ex) {
                    System.out.println("Server: Could not start connection to a client.");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server: Closed down");
        }
    }





    public static void main(String[] args) {

        ThreadedServer simpleServer = new ThreadedServer();
        simpleServer.connectToClients();


    }



}
