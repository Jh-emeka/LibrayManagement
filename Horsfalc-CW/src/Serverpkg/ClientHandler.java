package Serverpkg;

import both.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;


public class ClientHandler implements Runnable {

    private Socket newSocket;


    private static int connectionCount = 0;
    private int connectionNumber;

    public List<Book> books;
    public List<Person> person;
    public List<On_loan> onLoan;

    public ClientHandler(Socket clientSocket, List<Book> books,List<Person> person, List<On_loan> onLoan ) throws IOException {

        this.newSocket = clientSocket;
        this.books = books;
        this.person= person;
        this.onLoan = onLoan;

        connectionCount++;
        connectionNumber = connectionCount;
        threadSays("Connection " + connectionNumber + " established.");
    }

    @Override
    public void run() {
        try (

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(newSocket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(newSocket.getInputStream())
        ) {

            System.out.println("Server: Connection " + connectionCount + " established.");

            System.out.println("Tables sent");
            // Read and process names until an exception is thrown.
            System.out.println("Server: Waiting for data from client...");
            Parcel parcelRead;


            while ((parcelRead = (Parcel) objectInputStream.readObject()) != null) {

                System.out.println("Server: Read data from client: " + parcelRead + ".");



                if ((parcelRead.getCommand() == Command.SELECT) && (parcelRead.getTable() == Table.BOOK)) {

                    List<Book> reply;

                    reply = books;

                    objectOutputStream.writeObject(reply);

                }

                if((parcelRead.getCommand() == Command.SELECT) && (parcelRead.getTable() == Table.PERSON))
                {

                    List<Person> reply;

                    reply = person;

                    objectOutputStream.writeObject(reply);


                }

                if((parcelRead.getCommand() == Command.SELECT) && (parcelRead.getTable() == Table.ONLOAN))
                {

                    List<On_loan> reply;

                    reply = onLoan;

                    objectOutputStream.writeObject(reply);


                }




            }
        } catch (SocketException | EOFException ex) {
            System.out.println("Server: We have lost connection to client " + connectionCount + ".");
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println("Server: Class of a serialized object cannot be found.");
        }

    }



    private void threadSays(String s) {
        System.out.println("ClientHandlerThread" + connectionNumber + ": " + s);
    }


}