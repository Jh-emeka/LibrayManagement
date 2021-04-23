package Clientpkg;

import both.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private JTextField textFieldPersonId;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldLibraryCard;

    private JTextField textFieldLoanId;
    private JTextField textFieldBookId2;
    private JTextField textFieldPersonId2;
    private JTextField textFieldLoanPeriod;
    private JTextField textFieldLoanStart;
    private JTextField textFieldLoanEnd;
    private JTextField textFieldReturnedDate;
    private JComboBox<String> status;


    public Client() {

        initGUI();

    }

    public void dateFormatter(JTextField input)
    {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        input.setText(formatDate.format(d));

    }

    private void initGUI() {

        final JFrame f = new JFrame("Library Management System"); // final means fr cannot point to a another Jframe.


        JButton connect = new JButton("Refresh");
        connect.setBounds(4, 50, 200, 50);

        JButton print = new JButton("Print");
        print.setBounds(4, 110, 200, 50);

        JTextField searchBook = new JTextField();
        searchBook.setBounds(300, 630, 200, 30);

        JTextField searchPerson = new JTextField();
        searchPerson.setBounds(300, 630, 200, 30);

        JTextField searchOnLoan = new JTextField();
        searchOnLoan.setBounds(300, 630, 200, 30);


        JButton searchButtonTab1 = new JButton("Search");
        searchButtonTab1.setBounds(500, 630, 100, 30);

        JButton searchButtonTab2 = new JButton("Search");
        searchButtonTab2.setBounds(500, 630, 100, 30);

        JButton searchButtonTab3 = new JButton("Search");
        searchButtonTab3.setBounds(500, 630, 100, 30);




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



        tab3 = new JPanel();
        tabbedPane.addTab("Books On loan", tab3);
        tab3.setLayout(null);
        tab3.setBounds(0,0,1000,500);
        onLoanTableModel = new Onloan_table();
        onLoanTable = new JTable(onLoanTableModel);
        onLoanTable.setBounds(0,0,1000,500);
        scrollPane3 = new JScrollPane(onLoanTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setBounds(onLoanTable.getBounds());


        JLabel bookId = new JLabel("book ID:");
        bookId.setBounds(1120,20,200,20);
        textFieldBookId = new JTextField();
        textFieldBookId.setBounds(1235,20,200,20);
        textFieldBookId.setEditable(false);
        tab1.add(bookId);
        tab1.add(textFieldBookId);


        JLabel title = new JLabel("Title:");
        title.setBounds(1120,50, 200,20);
        textFieldTitle = new JTextField();
        textFieldTitle.setBounds(1235,50, 200,20);
        tab1.add(title);
        tab1.add(textFieldTitle);

        JLabel authors = new JLabel("Authors:");
        authors.setBounds(1120,80, 200,20);
        textFieldAuthors = new JTextField();
        textFieldAuthors.setBounds(1235,80, 200,20);
        tab1.add(authors);
        tab1.add(textFieldAuthors);

        JLabel average_ratings = new JLabel("Avg_ratings:");
        average_ratings.setBounds(1120,110, 200,20);
        textFieldAverageRatings = new JTextField();
        textFieldAverageRatings.setBounds(1235,110, 200,20);
        tab1.add(average_ratings);
        tab1.add(textFieldAverageRatings);

        JLabel isbn = new JLabel("Isbn:");
        isbn.setBounds(1120,140, 200,20);
        textFieldIsbn = new JTextField();
        textFieldIsbn.setBounds(1235,140, 200,20);
        tab1.add(isbn);
        tab1.add(textFieldIsbn);


        JLabel isbn13 = new JLabel("Isbn13:");
        isbn13.setBounds(1120,170, 200,20);
        textFieldIsbn13 = new JTextField();
        textFieldIsbn13.setBounds(1235,170, 200,20);
        tab1.add(isbn13);
        tab1.add(textFieldIsbn13);

        JLabel language_code = new JLabel("Language_code:");
        language_code.setBounds(1120,200, 200,20);
        textFieldLanguageCode = new JTextField();
        textFieldLanguageCode.setBounds(1235,200, 200,20);
        tab1.add(language_code);
        tab1.add(textFieldLanguageCode);

        JLabel num_pages = new JLabel("Num_pages:");
        num_pages.setBounds(1120,230, 200,20);
        textFieldNumPages = new JTextField();
        textFieldNumPages.setBounds(1235,230, 200,20);
        tab1.add(num_pages);
        tab1.add(textFieldNumPages);

        JLabel ratings_count = new JLabel("Ratings_count:");
        ratings_count.setBounds(1120,260, 200,20);
        textFieldRatingsCount = new JTextField();
        textFieldRatingsCount.setBounds(1235,260, 200,20);
        tab1.add(ratings_count);
        tab1.add(textFieldRatingsCount);

        JLabel text_review_count = new JLabel("Text_review_count:");
        text_review_count.setBounds(1120,290, 200,20);
        textFieldTextReviewCount = new JTextField();
        textFieldTextReviewCount.setBounds(1235,290, 200,20);
        tab1.add(text_review_count);
        tab1.add(textFieldTextReviewCount);

        JLabel quantity = new JLabel("Quantity:");
        quantity.setBounds(1120,320, 200,20);
        textFieldQuantity = new JTextField();
        textFieldQuantity.setBounds(1235,320, 200,20);
        tab1.add(quantity);
        tab1.add(textFieldQuantity);
        //--------------------------------------------------------------------------------------------------------------

        JLabel personId = new JLabel("Person ID:");
        personId.setBounds(1120,20,200,20);
        textFieldPersonId = new JTextField();
        textFieldPersonId.setBounds(1235,20,200,20);
        textFieldPersonId.setEditable(false);
        tab2.add(personId);
        tab2.add(textFieldPersonId);


        JLabel firstName = new JLabel("First Name:");
        firstName.setBounds(1120,50, 200,20);
        textFieldFirstName = new JTextField();
        textFieldFirstName.setBounds(1235,50, 200,20);
        tab2.add(firstName);
        tab2.add(textFieldFirstName);

        JLabel lastName = new JLabel("Last Name:");
        lastName.setBounds(1120,80, 200,20);
        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(1235,80, 200,20);
        tab2.add(lastName);
        tab2.add(textFieldLastName);

        JLabel libraryCard = new JLabel("LibraryCard:");
        libraryCard.setBounds(1120,110, 200,20);
        textFieldLibraryCard = new JTextField();
        textFieldLibraryCard.setBounds(1235,110, 200,20);
        tab2.add(libraryCard);
        tab2.add(textFieldLibraryCard);

        //--------------------------------------------------------------------------------------------------------------

        JLabel loanId = new JLabel("Loan ID:");
        loanId.setBounds(1120,20,200,20);
        textFieldLoanId = new JTextField();
        textFieldLoanId.setBounds(1235,20,200,20);
        textFieldLoanId.setEditable(false);
        tab3.add(loanId);
        tab3.add(textFieldLoanId);

        JLabel bookId2 = new JLabel("Book Id:");
        bookId2.setBounds(1120,50, 200,20);
        textFieldBookId2 = new JTextField();
        textFieldBookId2.setBounds(1235,50, 200,20);
        tab3.add(bookId2);
        tab3.add(textFieldBookId2);

        JLabel personId2 = new JLabel("Person Id:");
        personId2.setBounds(1120,80, 200,20);
        textFieldPersonId2 = new JTextField();
        textFieldPersonId2.setBounds(1235,80, 200,20);
        tab3.add(personId2);
        tab3.add(textFieldPersonId2);

        JLabel loanPeriod = new JLabel("Loan Period:");
        loanPeriod.setBounds(1120,110, 200,20);
        textFieldLoanPeriod = new JTextField();
        textFieldLoanPeriod.setBounds(1235,110, 200,20);
        tab3.add(loanPeriod);
        tab3.add(textFieldLoanPeriod);

        JLabel loanStart = new JLabel("Loan Start:");
        loanStart.setBounds(1120,140, 200,20);
        textFieldLoanStart = new JTextField();
        dateFormatter(textFieldLoanStart);
        textFieldLoanStart.setBounds(1235,140, 200,20);
        tab3.add(loanStart);
        tab3.add(textFieldLoanStart);

        JLabel loanEnd = new JLabel("Loan End:");
        loanEnd.setBounds(1120,170, 200,20);


        textFieldLoanEnd = new JTextField();
        textFieldLoanEnd.setBounds(1235,170, 200,20);
        tab3.add(loanEnd);
        tab3.add(textFieldLoanEnd);

        JLabel returnedDate = new JLabel("Returned Date:");
        returnedDate.setBounds(1120,200, 200,20);
        textFieldReturnedDate = new JTextField();
        textFieldReturnedDate.setBounds(1235,200, 200,20);
        tab3.add(returnedDate);
        tab3.add(textFieldReturnedDate);


        String[] options ={"On loan","Returned","Overdue"};
        status = new JComboBox<>(options);
        status.setEditable(true);
        status.setBounds(1235, 230,200,20);

        JLabel returnStatus = new JLabel("Return Status:");
        returnStatus.setBounds(1120,230, 200,20);
        tab3.add(returnStatus);
        tab3.add(status);



        JButton add_book = new JButton("Add");
        add_book.setBounds(1190, 400, 100, 20);
        tab1.add(add_book);

        JButton add_person = new JButton("Add");
        add_person.setBounds(1190, 150, 100, 20);
        tab2.add(add_person);

        JButton add_loan = new JButton("Add");
        add_loan.setBounds(1190, 300, 100, 20);
        tab3.add(add_loan);


        JButton update_Book = new JButton("Update");
        update_Book.setBounds(1290, 400, 100, 20);
        tab1.add(update_Book);

        JButton update_Person = new JButton("Update");
        update_Person.setBounds(1290, 150, 100, 20);
        tab2.add(update_Person);

        JButton update_loan = new JButton("Update");
        update_loan.setBounds(1290, 300, 100, 20);
        tab3.add(update_loan);



        JButton delete_Book = new JButton("Delete");
        delete_Book.setBounds(1390,400,100,20);
        tab1.add(delete_Book);

        JButton delete_Person = new JButton("Delete");
        delete_Person.setBounds(1390,150,100,20);
        tab2.add(delete_Person);


        JButton delete_loan = new JButton("Delete");
        delete_loan.setBounds(1390,300,100,20);
        tab3.add(delete_loan);


        tab1.add(searchBook);
        tab1.add(searchButtonTab1);

        tab2.add(searchPerson);
        tab2.add(searchButtonTab2);

        tab3.add(searchOnLoan);
        tab3.add(searchButtonTab3);

        f.add(tabbedPane);
        f.add(print);
        f.add(connect);




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

                textFieldTextReviewCount.setText(String.valueOf(bookTable.getModel().getValueAt(row, 9)));

                textFieldQuantity.setText(String.valueOf(bookTable.getModel().getValueAt(row, 10)));


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


         personTable.addMouseListener(new MouseInputListener() {
             @Override
             public void mouseClicked(MouseEvent e) {

                 personTable = (JTable)e.getSource();
                 int row = personTable.rowAtPoint( e.getPoint() );

                 textFieldPersonId.setText(String.valueOf(personTable.getModel().getValueAt(row, 0)));

                 textFieldFirstName.setText(String.valueOf(personTable.getModel().getValueAt(row, 1)));

                 textFieldLastName.setText(String.valueOf(personTable.getModel().getValueAt(row, 2)));

                 textFieldLibraryCard.setText(String.valueOf(personTable.getModel().getValueAt(row, 3)));

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


         onLoanTable.addMouseListener(new MouseInputListener() {
             @Override
             public void mouseClicked(MouseEvent e) {

                 onLoanTable = (JTable)e.getSource();
                 int row = onLoanTable.rowAtPoint( e.getPoint() );



                 textFieldLoanId.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 0)));

                 textFieldBookId2.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 1)));

                 textFieldPersonId2.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 2)));

                 textFieldLoanPeriod.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 3)));

                 textFieldLoanStart.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 4)));

                 textFieldLoanEnd.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 5)));

                 textFieldReturnedDate.setText(String.valueOf(onLoanTable.getModel().getValueAt(row, 6)));

                 status.getEditor().setItem(String.valueOf(onLoanTable.getModel().getValueAt(row, 7)));

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

         add_book.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 addBook();

             }
         });

         add_person.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 addPerson();
             }
         });

         add_loan.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 addLoan();

             }
         });

         update_Book.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 updateBook();

             }
         });

        update_Person.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updatePerson();
            }
        });

        update_loan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateLoan();

            }
        });

        delete_Book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deleteBook();

            }
        });

        delete_Person.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deletePerson();
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


    private void receiveReply(){

        Parcel reply = null;

        System.out.println("Status: waiting for reply from server");
        try {
            reply = (Parcel) objectInputStream.readObject();


            System.out.println("Status: received reply from server");


        } catch (IOException ex) {
            System.out.println("IOException " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException " + ex);
        }


        if (reply != null) {

            try {

                System.out.println(reply.getCommand());

            } catch (NullPointerException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    private void getBookTable(){
        if (objectOutputStream != null && objectInputStream != null) {


            Book newBook = new Book();

            // send data to server

            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.BOOK,newBook));
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


    private Object fillBookObject()
    {

        Book newBook = new Book();
        newBook.setBookId(Integer.parseInt(textFieldBookId.getText()));
        newBook.setTitle(textFieldTitle.getText());
        newBook.setAuthors(textFieldAuthors.getText());
        newBook.setAverage_rating(Float.parseFloat(textFieldAverageRatings.getText()));
        newBook.setIsbn(Double.parseDouble(textFieldIsbn.getText()));
        newBook.setIsbn13(Double.parseDouble(textFieldIsbn13.getText()));
        newBook.setLanguage_code(textFieldLanguageCode.getText());
        newBook.setNum_pages(Integer.parseInt(textFieldNumPages.getText()));
        newBook.setRating_count(Integer.parseInt(textFieldRatingsCount.getText()));
        newBook.setText_review_count(Integer.parseInt(textFieldTextReviewCount.getText()));
        newBook.setQuantity(Integer.parseInt(textFieldQuantity.getText()));

        return newBook;

    }


    private void addBook()
    {
        if (objectOutputStream != null && objectInputStream != null) {
            try {

                objectOutputStream.writeObject(new Parcel(Command.ADD, Table.BOOK,fillBookObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            //receive reply from server
            receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }

    }

    private void updateBook()
    {
        if (objectOutputStream != null && objectInputStream != null) {
            try {

                objectOutputStream.writeObject(new Parcel(Command.UPDATE, Table.BOOK,fillBookObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            //receive reply from server
            receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }

    }

    private void deleteBook()
    {

        if (objectOutputStream != null && objectInputStream != null) {

            Book newBook = new Book();

            newBook.setBookId(Integer.parseInt(textFieldBookId.getText()));

            try {

                objectOutputStream.writeObject(new Parcel(Command.DELETE, Table.BOOK,newBook));

            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            //receive reply from server
            receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }



    }



    private void getPersonTable()
    {
        if (objectOutputStream != null && objectInputStream != null) {


            Person newPerson = new Person();

            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.PERSON,newPerson));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }



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

    private Object fillPersonObject() {
        Person newPerson = new Person();
        newPerson.setPersonId(Integer.parseInt(textFieldPersonId.getText()));
        newPerson.setFirst_name(textFieldFirstName.getText());
        newPerson.setLast_name(textFieldLastName.getText());
        newPerson.setLibrary_card(Double.parseDouble(textFieldLibraryCard.getText()));

        return newPerson;
    }



    private void addPerson()
    {
        if (objectOutputStream != null && objectInputStream != null){

            try {

                objectOutputStream.writeObject(new Parcel(Command.ADD, Table.PERSON,fillPersonObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }

    }

    private void updatePerson()
    {


        if (objectOutputStream != null && objectInputStream != null){

            try {

                objectOutputStream.writeObject(new Parcel(Command.UPDATE, Table.PERSON,fillPersonObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

               receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }



    }

    private void deletePerson()
    {
        Person personObj = new Person();
        personObj.setPersonId(Integer.parseInt(textFieldPersonId.getText()));


        if (objectOutputStream != null && objectInputStream != null){

            try {

                objectOutputStream.writeObject(new Parcel(Command.DELETE, Table.PERSON,fillPersonObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            receiveReply();

        } else {
            System.out.println("You must connect to the server first!!");
        }



    }





    private void getOnLoanTable()
    {
        if (objectOutputStream != null && objectInputStream != null) {

            On_loan newLoan = new On_loan();

            try {

                objectOutputStream.writeObject(new Parcel(Command.SELECT, Table.ONLOAN,newLoan));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

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


            if (reply != null) {

                try {

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


    private Object fillLoanObject()
    {

        On_loan newLoan = new On_loan();
        newLoan.setLoan_Id(Integer.parseInt(textFieldLoanId.getText()));
        newLoan.setBook_Id(Integer.parseInt(textFieldBookId2.getText()));
        newLoan.setPerson_Id(Integer.parseInt(textFieldPersonId2.getText()));
        newLoan.setLoan_Period(Integer.parseInt(textFieldLoanPeriod.getText()));
        newLoan.setLoan_Start(textFieldLoanStart.getText());
        newLoan.setLoan_End(textFieldLoanEnd.getText());
        newLoan.setReturned_Date(textFieldReturnedDate.getText());
        newLoan.setReturn_Status(status.getEditor().getItem().toString());

        return newLoan;

    }



    private void addLoan(){

        if (objectOutputStream != null && objectInputStream != null){

            try {
                objectOutputStream.writeObject(new Parcel(Command.ADD, Table.ONLOAN,fillLoanObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            // receive reply from server
           receiveReply();
        } else {
            System.out.println("You must connect to the server first!!");
        }

    }

    private void updateLoan(){

        if (objectOutputStream != null && objectInputStream != null){

            try {
                objectOutputStream.writeObject(new Parcel(Command.UPDATE, Table.ONLOAN,fillLoanObject()));
            } catch (IOException ex) {
                System.out.println("IOException " + ex);
            }

            // receive reply from server

            receiveReply();
        } else {
            System.out.println("You must connect to the server first!!");
        }

    }



    private void reconnectToServer() {

        closeConnection();
        System.out.println("Status: Attempting connection to server");
        try {
            socket = new Socket("127.0.0.1", 4000);

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
