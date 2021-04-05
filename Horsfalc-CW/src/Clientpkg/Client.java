package Clientpkg;

import both.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private Book_table myTableModel;
    private Person_table PersonTableModel;
    private Onloan_table onLoanTableModel;
    private JTable bookTable;
    private JTable personTable;
    private JTable onLoanTable;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JPanel tab1;
    private JPanel tab2;
    private JPanel tab3;
    private JPanel tab4;
    private JPanel tab5;
    private JTextField textFieldBookId;
    private JTextField textFieldTitle;
    private JTextField textFieldAuthors;
    private JTextField textFieldAverageRatings;
    private JTextField textFieldIsbn;
    private JTextField textFieldIsbn13;
    private JTextField textFieldLanguageCode;
    private JTextField textFieldNumPages;
    private JTextField textFieldRatingsCount;
    private JTextField textFieldTextReviewCount;
    private JTextField textFieldQuantity;
    private JLabel bookId;
    private JLabel title;
    private JLabel authors;
    private JLabel average_ratings;
    private JLabel isbn;
    private JLabel isbn13;
    private JLabel language_code;
    private JLabel num_pages;
    private JLabel ratings_count;
    private JLabel text_review_count;
    private JLabel quantity;





    public Client() {

        initGUI();

    }

    private void initGUI() {

        final JFrame f = new JFrame("Library Management System"); // final means fr cannot point to a another Jframe.


        JButton connect = new JButton("Login");
        connect.setBounds(4, 50, 200, 50);

        JButton print = new JButton("Print");
        print.setBounds(4, 170, 200, 50);

        JTextField search = new JTextField();
        search.setBounds(1000, 270, 200, 30);

        JButton searchbutton = new JButton("Search");
        searchbutton.setBounds(1210, 270, 100, 30);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(5,300,1500,700);

        tab1 = new JPanel();
        tabbedPane.addTab("Books", tab1);
        tab1.setLayout(null);
        tab1.setBounds(0,0,1000,500);
        myTableModel = new Book_table();
        bookTable = new JTable(myTableModel);
        bookTable.setBounds(0,0,1000,500);
        scrollPane1 = new JScrollPane(bookTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(bookTable.getBounds());


        tab2 = new JPanel();
        tabbedPane.addTab("Person", tab2);
        tab2.setLayout(null);
        tab2.setBounds(0,0,1000,500);
        PersonTableModel = new Person_table();
        personTable = new JTable(PersonTableModel);
        personTable.setBounds(0,0,1000,500);
        scrollPane2 = new JScrollPane(personTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(personTable.getBounds());
        tab2.add(scrollPane2);



        tab3 = new JPanel();
        tabbedPane.addTab("Books On loan", tab3);
        tab3.setLayout(null);
        tab3.setBounds(0,0,1000,500);
        onLoanTableModel = new Onloan_table();
        onLoanTable = new JTable(onLoanTableModel);
        onLoanTable.setBounds(0,0,1000,500);
        scrollPane3 = new JScrollPane(onLoanTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setBounds(onLoanTable.getBounds());
        tab3.add(scrollPane3);






        tab4 = new JPanel();
        tabbedPane.addTab("Add new book", tab4);
        tab4.setLayout(null);


        tab5 = new JPanel();
        tabbedPane.addTab("Loan a book", tab5);
        JButton loan = new JButton(" Create New loan");
        loan.setBounds(500, 270, 200, 30);
        tab5.add(loan);

        bookId = new JLabel("book ID:");
        bookId.setBounds(1120,20,200,20);
        textFieldBookId = new JTextField();
        textFieldBookId.setBounds(1235,20,200,20);
        textFieldBookId.setEditable(false);
        tab1.add(bookId);
        tab1.add(textFieldBookId);


        title= new JLabel("Title:");
        title.setBounds(1120,50, 200,20);
        textFieldTitle = new JTextField();
        textFieldTitle.setBounds(1235,50, 200,20);
        tab1.add(title);
        tab1.add(textFieldTitle);

        authors = new JLabel("Authors:");
        authors.setBounds(1120,80, 200,20);
        textFieldAuthors = new JTextField();
        textFieldAuthors.setBounds(1235,80, 200,20);
        tab1.add(authors);
        tab1.add(textFieldAuthors);

        average_ratings = new JLabel("Avg_ratings:");
        average_ratings.setBounds(1120,110, 200,20);
        textFieldAverageRatings = new JTextField();
        textFieldAverageRatings.setBounds(1235,110, 200,20);
        tab1.add(average_ratings);
        tab1.add(textFieldAverageRatings);

        isbn = new JLabel("Isbn:");
        isbn.setBounds(1120,140, 200,20);
        textFieldIsbn = new JTextField();
        textFieldIsbn.setBounds(1235,140, 200,20);
        tab1.add(isbn);
        tab1.add(textFieldIsbn);


        isbn13 = new JLabel("Isbn13:");
        isbn13.setBounds(1120,170, 200,20);
        textFieldIsbn13 = new JTextField();
        textFieldIsbn13.setBounds(1235,170, 200,20);
        tab1.add(isbn13);
        tab1.add(textFieldIsbn13);

        language_code = new JLabel("Language_code:");
        language_code.setBounds(1120,200, 200,20);
        textFieldLanguageCode = new JTextField();
        textFieldLanguageCode.setBounds(1235,200, 200,20);
        tab1.add(language_code);
        tab1.add(textFieldLanguageCode);

        num_pages = new JLabel("Num_pages:");
        num_pages.setBounds(1120,230, 200,20);
        textFieldNumPages = new JTextField();
        textFieldNumPages.setBounds(1235,230, 200,20);
        tab1.add(num_pages);
        tab1.add(textFieldNumPages);

        ratings_count = new JLabel("Ratings_count:");
        ratings_count.setBounds(1120,260, 200,20);
        textFieldRatingsCount = new JTextField();
        textFieldRatingsCount.setBounds(1235,260, 200,20);
        tab1.add(ratings_count);
        tab1.add(textFieldRatingsCount);

        text_review_count = new JLabel("Text_review_count:");
        text_review_count.setBounds(1120,290, 200,20);
        textFieldTextReviewCount = new JTextField();
        textFieldTextReviewCount.setBounds(1235,290, 200,20);
        tab1.add(text_review_count);
        tab1.add(textFieldTextReviewCount);

        quantity = new JLabel("Quantity:");
        quantity.setBounds(1120,320, 200,20);
        textFieldQuantity = new JTextField();
        textFieldQuantity.setBounds(1235,320, 200,20);
        tab1.add(quantity);
        tab1.add(textFieldQuantity);


        JButton add_book = new JButton("Add");
        add_book.setBounds(1190, 500, 100, 20);
        tab1.add(add_book);

        JButton update = new JButton("Update");
        update.setBounds(1290, 500, 100, 20);
        tab1.add(update);

        JButton delete = new JButton("Delete");
        delete.setBounds(1390,500,100,20);
        tab1.add(delete);


        f.add(tabbedPane);
        f.add(print);
        f.add(connect);
        f.add(search);
        f.add(searchbutton);



        f.setLayout(null);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // this means that the gui can be closed by clicking the close icon


        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reconnectToServer();
                getBookTable();
                getPersonTable();
                getOnLoanTable();



            }
        });


        bookTable.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


                bookTable = (JTable)e.getSource();
                int row = bookTable.rowAtPoint( e.getPoint() );



                textFieldBookId.setText(String.valueOf(bookTable.getModel().getValueAt(row, 0)));

                textFieldTitle.setText(String.valueOf(bookTable.getModel().getValueAt(row, 1)));

                textFieldAuthors.setText(String.valueOf(bookTable.getModel().getValueAt(row, 2)));

                textFieldAverageRatings.setText(String.valueOf(bookTable.getModel().getValueAt(row, 3)));

                textFieldIsbn.setText(String.valueOf(bookTable.getModel().getValueAt(row, 4)));

                textFieldIsbn13.setText(String.valueOf(bookTable.getModel().getValueAt(row, 5)));

                textFieldLanguageCode.setText(String.valueOf(bookTable.getModel().getValueAt(row, 6)));

                textFieldNumPages.setText(String.valueOf(bookTable.getModel().getValueAt(row, 7)));

                textFieldRatingsCount.setText(String.valueOf(bookTable.getModel().getValueAt(row, 8)));

                textFieldTextReviewCount.setText(String.valueOf(bookTable.getModel().getValueAt(row, 8)));

                textFieldQuantity.setText(String.valueOf(bookTable.getModel().getValueAt(row, 8)));


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });


    }


    private void closeConnection() {
        if (socket != null) {
            System.out.println("Status: closing connection");
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                socket = null;
            }
        }
    }


    private void getBookTable(){
        if (objectOutputStream != null && objectInputStream != null) {

            // 1. read data from textfield

            String getTables = "get tables";

            // 2. send data to server

            // Parcel envelope = null;
            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.BOOK, getTables));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            // 3. receive reply from server

            ArrayList<Book> reply = new ArrayList<>();

            System.out.println("Status: waiting for reply from server");
            try {
                reply = (ArrayList<Book>) objectInputStream.readObject();


                System.out.println("Status: received reply from server");
                // objectOutputStream.reset();

            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException " + ex);
            }

            // 4. display message on textarea
            if (reply != null) {

                try {
                    // reply.forEach((track)-> System.out.println(track));


                    myTableModel.readData(reply);
                    tab1.add(scrollPane1);


                } catch (NullPointerException ex) {
                    //labelStatus.setText("NullPointerException " + ex);
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("You must connect to the server first!!");
        }


    }

    private void getPersonTable()
    {
        if (objectOutputStream != null && objectInputStream != null) {

            // 1. read data from textfield

            String getTables = "get tables";

            // 2. send data to server

            // Parcel envelope = null;
            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.PERSON, getTables));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            // 3. receive reply from server

            ArrayList<Person> reply = new ArrayList<>();

            System.out.println("Status: waiting for reply from server");
            try {
                reply = (ArrayList<Person>) objectInputStream.readObject();

                System.out.println("Status: received reply from server");
                // objectOutputStream.reset();

            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException " + ex);
            }

            // 4. display message on textarea
            if (reply != null) {

                try {
                    // reply.forEach((track)-> System.out.println(track));


                    PersonTableModel.readData(reply);
                    tab2.add(scrollPane2);


                } catch (NullPointerException ex) {
                    //labelStatus.setText("NullPointerException " + ex);
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("You must connect to the server first!!");
        }


    }


    private void getOnLoanTable()
    {
        if (objectOutputStream != null && objectInputStream != null) {

            // 1. read data from textfield

            String getTables = "get tables";

            // 2. send data to server

            // Parcel envelope = null;
            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.ONLOAN, getTables));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            // 3. receive reply from server

            ArrayList<On_loan> reply = new ArrayList<>();

            System.out.println("Status: waiting for reply from server");
            try {
                reply = (ArrayList<On_loan>) objectInputStream.readObject();

                System.out.println("Status: received reply from server");
                // objectOutputStream.reset();

            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException " + ex);
            }

            // 4. display message on textarea
            if (reply != null) {

                try {
                    // reply.forEach((track)-> System.out.println(track));


                    onLoanTableModel.readData(reply);
                    tab3.add(scrollPane3);


                } catch (NullPointerException ex) {
                    //labelStatus.setText("NullPointerException " + ex);
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("You must connect to the server first!!");
        }


    }






    private void reconnectToServer() {

        closeConnection();
        System.out.println("Status: Attempting connection to server");
        try {
            socket = new Socket("127.0.0.1", 3000);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println("Status: Connected to server");


        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString()); // connection failed
        }
    }




    public static void main(String[] args) {
        Client simpleClient = new Client();

    }


}
