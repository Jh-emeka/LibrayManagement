package Serverpkg;

import both.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


public class ClientHandler implements Runnable {

    private final Socket newSocket;


    private static int connectionCount = 0;
    private final int connectionNumber;

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


                 Parcel ack; // Acknowledge to indicate success or fail

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

                if((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.BOOK))
                {

                  ThreadedServer.insertBook((Book) parcelRead.getNewData());

                  ack = new Parcel(Command.SUCCESS);

                   objectOutputStream.writeObject(ack);

                }

                if((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.PERSON)){



                    ThreadedServer.insertPerson((Person) parcelRead.getNewData());
                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);

                }

                if((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.ONLOAN)){


                    ThreadedServer.insertLoan((On_loan) parcelRead.getNewData());
                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);


                }

                if((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.BOOK)){

                    ThreadedServer.bookUpdate((Book) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);

                }

                if((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.PERSON)){

                    ThreadedServer.personUpdate((Person) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);



                }

                if((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.ONLOAN)){

                    ThreadedServer.loanUpdate((On_loan) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);

                }

                if((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.BOOK)){


                    ThreadedServer.deleteBook((Book) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);


                }

                if((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.PERSON)){


                    ThreadedServer.deletePerson((Person) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);


                }

                if((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.ONLOAN)){


                    ThreadedServer.deleteLoan((On_loan) parcelRead.getNewData());

                    ack = new Parcel(Command.SUCCESS);

                    objectOutputStream.writeObject(ack);


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