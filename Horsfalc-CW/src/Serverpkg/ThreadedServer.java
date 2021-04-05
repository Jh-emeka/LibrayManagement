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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Serverpkg.ThreadedServer app = new Serverpkg.ThreadedServer();

        ThreadedServer simpleServer = new ThreadedServer();
        simpleServer.connectToClients();


    }



}
