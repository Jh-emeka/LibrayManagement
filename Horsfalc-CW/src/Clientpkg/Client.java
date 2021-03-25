package Clientpkg;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Client {

    public Client() {
        final JFrame f = new JFrame("Library Management System"); // final means fr cannot point to a another Jframe.
        //f.setLayout(new FlowLayout());
        //f.setLayout(new BorderLayout());

        JButton add, delete, print, save, connect, loan, retrn, update;

        add = new JButton("Add");
        add.setBounds(1610,600,200,50);


        delete = new JButton("Delete");
        delete.setBounds(1610,655,200,50);


        connect = new JButton("Connect to Server");
        connect.setBounds(4, 50, 200, 50);

        update = new JButton("Update");
        update.setBounds(4, 110, 200, 50);

        print = new JButton("Print");
        print.setBounds(4, 170, 200, 50);


        loan = new JButton(" Create New loan");
        loan.setBounds(500, 270, 200, 30);


        retrn = new JButton("Create New return");
        retrn.setBounds(710, 270, 200, 30);




        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(5,300,1500,700);

        JPanel tab1 = new JPanel();
        tabbedPane.addTab("Books", tab1);
//

        JPanel tab2 = new JPanel();
        tabbedPane.addTab("Person", tab2);
//
        JPanel tab3 = new JPanel();
        tabbedPane.addTab("Books On loan", tab3);
//


        f.add(tabbedPane); //BorderLayout.CENTER);
        f.add(add);
        f.add(delete);
        f.add(print);
        f.add(connect);
        f.add(update);
        f.add(retrn);
        f.add(loan);




        f.setLayout(null);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // this means that the gui can be closed by clicking the close icon
    }


    public static void main(String[] args) {

        Client mygui = new Client();

    }

}
