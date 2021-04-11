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
 * @author Chris Bass
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









    /**
     * Wait until a client connects to the server on a port, then establish the
     * connection via a socket object and create a thread to handle requests.
     */
    private void connectToClients() {
        System.out.println("Server: Server starting.");

        try (ServerSocket serverSocket = new ServerSocket(3000)) {

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
