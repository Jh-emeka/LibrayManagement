package Serverpkg;
import both.Book;


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
                books.add(Book.newTrackFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serverpkg.ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }



    /**
     * Wait until a client connects to the server on a port, then establish the
     * connection via a socket object and create a thread to handle requests.
     */
    private void connectToClients() {
        System.out.println("Server: Server starting.");

        try (ServerSocket serverSocket = new ServerSocket(2200)) {

            while (true) {
                System.out.println("Server: Waiting for connecting client...");

                try {
                    Socket socket = serverSocket.accept();

                    ClientHandler clientHandler = new ClientHandler(socket, initBooks() );
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
