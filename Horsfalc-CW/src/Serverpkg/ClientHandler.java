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



    public ClientHandler(Socket clientSocket) throws IOException {

        this.newSocket = clientSocket;


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

                    reply = ThreadedServer.getBooks();

                    objectOutputStream.writeObject(reply);



                }

                if((parcelRead.getCommand() == Command.SELECT) && (parcelRead.getTable() == Table.PERSON))
                {

                    List<Person> reply;

                    reply = ThreadedServer.getPerson();

                    objectOutputStream.writeObject(reply);




                }

                if((parcelRead.getCommand() == Command.SELECT) && (parcelRead.getTable() == Table.ONLOAN))
                {

                    List<On_loan> reply;

                    reply = ThreadedServer.getOnLoan();

                    objectOutputStream.writeObject(reply);




                }

                if((parcelRead.getCommand() == Command.SELECT) &&(parcelRead.getTable() == Table.JOIN))
                {

                    List<LoanDetails>reply;

                    reply = ThreadedServer.initLoanDetails((On_loan) parcelRead.getNewData());

                    objectOutputStream.writeObject(reply);


                }

                else if((parcelRead.getCommand() == Command.ADD) || (parcelRead.getCommand() == Command.UPDATE) || (parcelRead.getCommand() == Command.DELETE)) {


                    if ((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.BOOK)) {

                        ThreadedServer.insertBook((Book) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.PERSON)) {


                        ThreadedServer.insertPerson((Person) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.ADD) && (parcelRead.getTable() == Table.ONLOAN)) {


                        ThreadedServer.insertLoan((On_loan) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.BOOK)) {

                        ThreadedServer.bookUpdate((Book) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.PERSON)) {

                        ThreadedServer.personUpdate((Person) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.UPDATE) && (parcelRead.getTable() == Table.ONLOAN)) {

                        ThreadedServer.loanUpdate((On_loan) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.BOOK)) {


                        ThreadedServer.deleteBook((Book) parcelRead.getNewData());




                    }

                    if ((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.PERSON)) {


                        ThreadedServer.deletePerson((Person) parcelRead.getNewData());


                    }

                    if ((parcelRead.getCommand() == Command.DELETE) && (parcelRead.getTable() == Table.ONLOAN)) {


                        ThreadedServer.deleteLoan((On_loan) parcelRead.getNewData());


                    }

                    objectOutputStream.writeObject(new Parcel(Command.SUCCESS));


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