package Clientpkg;

import both.Book;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Book_table extends AbstractTableModel {


    private final String[] columnNames = {"book_id", "title", "authors", "average_ratings", "isbn", "isbn13", "language_code", "num_pages", "ratings_count", "text_review_count", "quantity" };

    private final ArrayList<Object[]> bookData = new ArrayList<>();



    public Book_table() {

    }


    public void readData(ArrayList<Book> data) {
        for (Book datum : data) {

            int book_id = datum.getBookId();
            String title = datum.getTitle();
            String author = datum.getAuthors();
            int average_rating = datum.getAverage_rating();
            double isbn = datum.getIsbn();
            double isbn13 = datum.getIsbn13();
            String language_code = datum.getLanguage_code();
            int num_pages = datum.getNum_pages();
            int ratings_count = datum.getRating_count();
            int text_review_count = datum.getText_review_count();
            int quantity = datum.getQuantity();



            Object[] table_data = {book_id, title, author, average_rating, isbn, isbn13, language_code, num_pages, ratings_count,text_review_count,quantity};

            bookData.add(table_data);


        }


    }


    @Override
    public int getRowCount() {
        return bookData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return bookData.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        bookData.get(rowIndex)[columnIndex] = aValue.toString();
    }

    @Override
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        super.fireTableRowsDeleted(firstRow, lastRow);
    }


}
