package Serverpkg;

import both.Command;
import both.Parcel;
import both.Book;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


public class ClientHandler implements Runnable {

    private Socket newSocket;


    private static int connectionCount = 0;
    private int connectionNumber;

    public List<Book> tracks;

    public ClientHandler(Socket clientSocket, List<Book> tracks) throws IOException {

        this.newSocket = clientSocket;
        this.tracks = tracks;

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

                List<Book> reply = null;

                if (parcelRead.getCommand() == Command.SELECT) {

                    reply = tracks;

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